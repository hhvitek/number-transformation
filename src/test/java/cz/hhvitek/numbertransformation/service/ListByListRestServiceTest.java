package cz.hhvitek.numbertransformation.service;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListByListRestServiceTest {
	private final ListByListRestService restService = new ListByListRestService();

	@Test
	public void lessEqualThreeMoveRightExample() {
		LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(4, 3, 2, 5, 6, 7, 9, 1));
		LinkedList<Integer> expectedList = new LinkedList<>(Arrays.asList(4, 5, 3, 2, 6, 7, 9, 1));

		LinkedList<Integer> actualList = restService.digitsLessOrEqualThreeMoveToRight(inputList);
		assertEquals(expectedList, actualList);
	}

	@Test
	public void lessEqualThreeMoveRightTogether() {
		LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(4, 2, 3, 1, 5));
		LinkedList<Integer> expectedList = new LinkedList<>(Arrays.asList(4, 5, 2, 3, 1)); // no not swap <= 3, swap them as whole

		LinkedList<Integer> actualList = restService.digitsLessOrEqualThreeMoveToRight(inputList);
		assertEquals(expectedList, actualList);
	}

	@Test
	public void lessEqualThreeMoveRightTogetherAnother() {
		LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(4, 2, 3, 1));
		LinkedList<Integer> expectedList = new LinkedList<>(Arrays.asList(4, 2, 3, 1));

		LinkedList<Integer> actualList = restService.digitsLessOrEqualThreeMoveToRight(inputList);
		assertEquals(expectedList, actualList);
	}

	@Test
	public void eightAndNineMultiplyByTwo() {
		LinkedList<Integer> inputOutputList = new LinkedList<>(Arrays.asList(4, 5, 3, 2,6, 7, 9, 1));
		LinkedList<Integer> expectedList = new LinkedList<>(Arrays.asList(4, 5, 3, 2, 6, 7, 1, 8,1));

		restService.digitsEightAndNineMultiplyByTwo(inputOutputList);

		assertEquals(expectedList, inputOutputList);
	}

	@Test
	public void removeDigitsSeven() {
		LinkedList<Integer> inputOutputList = new LinkedList<>(Arrays.asList(4, 5, 3, 2, 6, 7, 1, 8, 1));
		LinkedList<Integer> expectedList = new LinkedList<>(Arrays.asList(4, 5, 3, 2, 6, 1, 8, 1));

		restService.removeAllDigitsSeven(inputOutputList);

		assertEquals(expectedList, inputOutputList);
	}

	@Test
	public void divideBynumberOfEvenDigits() {
		LinkedList<Integer> inputOutputList = new LinkedList<>(Arrays.asList(4, 5, 3, 2, 6, 1, 8, 1));
		int expected = 11331545;

		int actual = restService.divideByNumberOfEvenDigits(inputOutputList);

		assertEquals(expected, actual);
	}
}