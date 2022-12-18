package cz.hhvitek.numbertransformation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import cz.hhvitek.numbertransformation.service.DigitUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DigitUtilsTest {

	@Test
	public void toListBase() {
		int input = 956879;
		LinkedList<Integer> expected = new LinkedList<>(Arrays.asList(9, 5, 6, 8, 7, 9));

		LinkedList<Integer> actual = DigitUtils.toDigits(input);
		assertEquals(expected, actual);
	}

	@Test
	public void toListZeroOrNegative() {
		int zero = 0;
		assertThrows(IllegalArgumentException.class, () -> DigitUtils.toDigits(zero));

		int negative = -1;
		assertThrows(IllegalArgumentException.class, () -> DigitUtils.toDigits(negative));
	}

	@Test
	public void toNumber() {
		LinkedList<Integer> input = new LinkedList<>(Arrays.asList(9, 5, 6, 8, 7, 9));
		int expected = 956879;

		int actual = DigitUtils.toNumber(input);
		assertEquals(expected, actual);
	}

	@Test
	public void toNumberMultipleDigits() {
		List<Integer> input = Arrays.asList(0, 123, 4, 56);
		int expected = 123456;

		int actual = DigitUtils.toNumber(input);
		assertEquals(expected, actual);
	}

	@Test
	public void toNumberSingleDigit() {
		List<Integer> input = Arrays.asList(0);
		int expected = 0;

		assertEquals(expected, DigitUtils.toNumber(input));
	}

	@Test
	public void toNumberNegativeNumberInside() {
		List<Integer> input = Arrays.asList(1, -1);
		assertThrows(NumberFormatException.class, () -> DigitUtils.toNumber(input));
	}
}