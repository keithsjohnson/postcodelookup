package uk.co.cdl.keithj.postcodelookup.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uk.co.cdl.keithj.postcodelookup.infrastructure.repository.Postcode;
import uk.co.cdl.keithj.postcodelookup.infrastructure.repository.PostcodeRepository;

@RestController
public class SavePostcodeLocation {

	@Autowired
	private PostcodeRepository postcodeRepository;

	@RequestMapping(value = "/savepostcodelocation")
	public @ResponseBody String puPostcodeLocation(String postcode, String latitude, String longitude,
			String population, String households) {

		Postcode postcodeObject = new Postcode(postcode, latitude, longitude, population, households);

		Postcode postcodeSaved = postcodeRepository.save(postcodeObject);
		System.out.println(postcodeSaved.toString());
		return "OK";
	}
}
