AWS DynamoDB Postcode Store and Locator
---------------------------------------

Uses Spring Boot, REST and Spring Data using AWS DynamoDB Repository.

Add when running locally to pick up Profile Credentials Provider - this will use the credentials in $USER_HOME/.aws/credentials
-Duse.profile.credentials=true

Deployment to Elasticbeanstalk as a Docker Image.
-------------------------------------------------

Need to add the following Role Policy to the Elasticbeanstalk Image Role:
- AmazonDynamoDBFullAccess

Postcode Location URLs
----------------------
Save a Postcode and location:
http://localhost:8080/savepostcodelocation?postcode=ST7 2YB&latitude=1&longitude=2&population=100&households=30
http://localhost:8080/savepostcodelocation?postcode=SK4 2HD&latitude=8&longitude=9&population=0&households=0

Locate a Postcode:
http://localhost:8080/locatepostcode?postcode=ST7 2YB
http://localhost:8080/locatepostcode?postcode=SK4 2HD

Example URLs after deployment to AWS
------------------------------------
http://postcode.elasticbeanstalk.com/health

http://postcode.elasticbeanstalk.com/savepostcodelocation?postcode=ST7 2YB&latitude=1&longitude=2&population=100&households=30
http://postcode.elasticbeanstalk.com/savepostcodelocation?postcode=SK4 2HD&latitude=8&longitude=9&population=0&households=0

http://postcode.elasticbeanstalk.com/locatepostcode?postcode=ST7 2YB
http://postcode.elasticbeanstalk.com/locatepostcode?postcode=SK4 2HD
