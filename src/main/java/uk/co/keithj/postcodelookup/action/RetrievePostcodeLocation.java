package uk.co.keithj.postcodelookup.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uk.co.keithj.postcodelookup.api.model.PostcodeLocation;
import uk.co.keithj.postcodelookup.infrastructure.repository.Postcode;
import uk.co.keithj.postcodelookup.infrastructure.repository.PostcodeRepository;

@RestController
public class RetrievePostcodeLocation {

	@Autowired
	private PostcodeRepository postcodeRepository;

	@RequestMapping(value = "/locatepostcode")
	public @ResponseBody PostcodeLocation locatePostcode(String postcode) {
		Postcode postcodeObject = postcodeRepository.findOne(postcode);

		PostcodeLocation postcodeLocation = new PostcodeLocation(postcodeObject.getPostcode(),
				postcodeObject.getLatitude(), postcodeObject.getLongitude());

		return postcodeLocation;
	}
}
