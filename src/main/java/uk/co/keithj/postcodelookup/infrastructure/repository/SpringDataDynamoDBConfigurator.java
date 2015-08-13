/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.keithj.postcodelookup.infrastructure.repository;

import org.apache.commons.lang3.StringUtils;
import org.socialsignin.spring.data.dynamodb.core.DynamoDBOperations;
import org.socialsignin.spring.data.dynamodb.core.DynamoDBTemplate;
import org.socialsignin.spring.data.dynamodb.mapping.event.ValidatingDynamoDBEventListener;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

@Configuration
@EnableWebMvc
@EnableDynamoDBRepositories(basePackages = "uk.co.cdl.keithj.postcodelookup.infrastructure.repository", dynamoDBOperationsRef = "dynamoDBOperations")
public class SpringDataDynamoDBConfigurator {

	@Value("${amazon.dynamodb.endpoint}")
	private String amazonDynamoDBEndpoint;

	@Value("${use.profile.credentials:false}")
	private boolean useProfileCredentials = false;

	@Value("${amazon.region:eu-west-1}")
	private String amazonRegion;

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(amazonAWSCredentials());
		if (StringUtils.isNotEmpty(amazonDynamoDBEndpoint)) {
			amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
		}
		amazonDynamoDB.setRegion(amazonRegion());
		return amazonDynamoDB;
	}

	@Bean
	public DynamoDBOperations dynamoDBOperations() {
		return new DynamoDBTemplate(amazonDynamoDB());
	}

	/**
	 * The AWS Credentials are retrieved from $USER_HOME/.aws/credentials or
	 * from Instance Profile when on AWS.
	 * 
	 * @return the AWS Credentials
	 */
	@Bean
	public AWSCredentials amazonAWSCredentials() {
		if (useProfileCredentials) {
			return new ProfileCredentialsProvider().getCredentials();
		} else {
			return new InstanceProfileCredentialsProvider().getCredentials();
		}
	}

	@Bean
	public Region amazonRegion() {
		return Region.getRegion(Regions.fromName(amazonRegion));
	}

	/**
	 * The following validation-related beans are optional - only required if
	 * JSR 303 validation is desired. For validation to work,
	 * the @EnableDynamoDBRepositories must be configured with a reference to
	 * DynamoDBOperations bean, rather than with reference to AmazonDynamoDB
	 * client
	 */
	@Bean
	public LocalValidatorFactoryBean validator() {
		return new LocalValidatorFactoryBean();
	}

	@Bean
	public ValidatingDynamoDBEventListener validatingDynamoDBEventListener() {
		return new ValidatingDynamoDBEventListener(validator());
	}

}
