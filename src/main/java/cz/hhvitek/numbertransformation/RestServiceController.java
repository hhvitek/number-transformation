package cz.hhvitek.numbertransformation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.hhvitek.numbertransformation.service.NumberException;
import cz.hhvitek.numbertransformation.service.RestService;

import static cz.hhvitek.numbertransformation.RestServiceController.REST_SERVICE_URL;

@RestController
@RequestMapping(REST_SERVICE_URL)
public class RestServiceController {
	public static final String REST_SERVICE_URL = "/api/actions/number-transformation/";

	private final RestService service;

	public RestServiceController(RestService service) {
		this.service = service;
	}

	@GetMapping(value = "/{number}")
	public int transform(@PathVariable("number") int number) {
		if (number <= 0) { // let's make it simple
			throw new NumberException("Argument number must be greater than zero.");
		}

		return service.transform(number);
	}
}
