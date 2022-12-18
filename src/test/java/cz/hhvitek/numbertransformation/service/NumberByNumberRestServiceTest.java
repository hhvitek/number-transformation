package cz.hhvitek.numbertransformation.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberByNumberRestServiceTest {
	private final NumberByNumberRestService restService = new NumberByNumberRestService();

	@Test
	public void lessEqualThreeMoveRight() {
		int input = 43256791;
		int expected = 45326791;

		int actual = restService.digitsLessThanThreeMoveToRight(input);
		assertEquals(expected, actual);
	}

	@Test
	public void lessEqualThreeMoveRightAnotherAnother() {
		int input = 42315;
		int expected = 45231; // consecutive <= 3 digits are swapped together

		int actual = restService.digitsLessThanThreeMoveToRight(input);
		assertEquals(expected, actual);
	}

	@Test
	public void lessEqualThreeMoveRightAnother() {
		int input = 4231;
		int expected = 4231; // stable do not move if swapped would also be <= 3

		int actual = restService.digitsLessThanThreeMoveToRight(input);
		assertEquals(expected, actual);
	}

	@Test
	public void eightAndNineMultBy2() {
		int input =    45326791;
		int expected = 453267181;

		int actual = restService.digitsEightAndNineMultiplyByTwo(input);

		assertEquals(expected, actual);
	}

	@Test
	public void removeDigitsSeven() {
		int input =    453267181;
		int expected = 45326181;

		int actual = restService.removeAllDigitsSeven(input);

		assertEquals(expected, actual);
	}

	@Test
	public void divideBynumberOfEvenDigits() {
		int input =    45326181;
		int expected = 11331545;

		int actual = restService.divideByCountOfEvenDigits(input);

		assertEquals(expected, actual);
	}

}