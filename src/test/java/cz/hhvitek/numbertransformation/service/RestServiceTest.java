package cz.hhvitek.numbertransformation.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RestServiceTest {
	private final RestService restService = new NumberByNumberRestService();

	@Test
	public void exampleOkTest() {
		int input =    43256791;
		int expected = 11331545;

		int actual = restService.transform(input);
		assertEquals(expected, actual);
	}

	@Test
	public void zeroAndNegativeInputFails() {
		int inputZero =    0;
		assertThrows(IllegalArgumentException.class, () -> restService.transform(inputZero));

		int inputNegative = -1;
		assertThrows(IllegalArgumentException.class, () -> restService.transform(inputNegative));
	}

	@Test
	public void zeroEvenDigitsWouldLeadToZeroDivisionFails() {
		int input =  1;

		assertThrows(NumberException.class, () -> restService.transform(input));
	}

	@Test
	public void onlySevensInsideInputsLeadToNoDigitsAtAllFails() {
		int input =  7777;

		assertThrows(NumberException.class, () -> restService.transform(input));
	}

	@Test
	public void inputNumberLeadToTooLargeNumberFails() {
		int input =  999999999;

		assertThrows(NumberException.class, () -> restService.transform(input));
	}
}
