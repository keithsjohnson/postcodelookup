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
	public @ResponseBody String savePostcodeLocation(Postcode postcode) {

		Postcode postcodeSaved = postcodeRepository.save(postcode);
		System.out.println(postcodeSaved.toString());
		return "OK";
	}
}
