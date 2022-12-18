package cz.hhvitek.numbertransformation.service;

import java.util.Collection;
import java.util.LinkedList;

public final class DigitUtils {

	/**
	 * Converts input number to list of its digits. Only positive integers are supported.
	 *
	 * 123 % 10 = 3, 123 / 10 = 12
	 *  12 % 10 = 2,  12 / 10 = 1
	 *   1 % 10 = 1,   1 / 10 = 0
	 */
	public static LinkedList<Integer> toDigits(int number) {
		if (number <= 0) {
			throw new IllegalArgumentException("Only positive integers are supported");
		}

		LinkedList<Integer> digitsList = new LinkedList<>();

		int divisionResult = number;
		while (divisionResult > 0) {
			int lastDigit = divisionResult % 10;
			divisionResult = divisionResult / 10;
			digitsList.addFirst(lastDigit); // adding at the beginning (reversing order)
		}

		return digitsList;
	}

	/**
	 * concatenates number elements in collection, creating a single integer number in the process.
	 *
	 * @throws NumberFormatException if fails to create a number.
	 * For example too many digits (>10 in case of integer) or zero digits or would overflow integer
	 */
	public static int toNumber(Collection<Integer> numbers) {
		StringBuilder stringBuilder = new StringBuilder(numbers.size());
		numbers.forEach(stringBuilder::append);
		return Integer.parseInt(stringBuilder.toString());
	}
}
