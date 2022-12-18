package cz.hhvitek.numbertransformation.service;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ListByListRestService implements RestService {

	/**
	 * 1. všechny číslice menší 3 (včetně) posune o jednu pozici doprava. Např: 43256791 => 45326791
	 * 2. všechny číslice 8 a 9 vynásobí 2. Např.: 45326791 => 453267181
	 * 3. všechny číslice 7 smaže: Např: 453267181 => 45326181
	 * 4. ve výsledném čísle spočte počet sudých číslic a tímto počtem výsledné číslo vydělí a zaokrouhlí dolů na celá čísla. Např: 45326181 / 4 => 11331545
	 */
	@Override
	public int transform(int number) {
		LinkedList<Integer> digits = DigitUtils.toDigits(number);
		digits = digitsLessOrEqualThreeMoveToRight(digits);
		digitsEightAndNineMultiplyByTwo(digits);
		removeAllDigitsSeven(digits);
		return divideByNumberOfEvenDigits(digits);
	}

	/**
	 * 1.
	 */
	LinkedList<Integer> digitsLessOrEqualThreeMoveToRight(LinkedList<Integer> digits) {
		LinkedList<Integer> outputDigits = new LinkedList<>();

		LinkedList<Integer> tmpLessOrEqualThree = new LinkedList<>();
		for (Integer digit: digits) {
			if (digit > 3) {
				outputDigits.add(digit);
				outputDigits.addAll(tmpLessOrEqualThree);
				tmpLessOrEqualThree.clear();
			} else {
				tmpLessOrEqualThree.add(digit);
			}
		}
		outputDigits.addAll(tmpLessOrEqualThree); // if last digits are <= 3, such as 45321

		return outputDigits;
	}

	/**
	 * 2.
	 */
	void digitsEightAndNineMultiplyByTwo(LinkedList<Integer> digits) {
		ListIterator<Integer> digitsListIt = digits.listIterator();
		while (digitsListIt.hasNext()) {
			Integer digit = digitsListIt.next();
			if (digit == 8 || digit == 9) {
				int multipliedDigit = digit * 2;
				// replace current old value with a new multiplied one... (8 for 16 and 9 for 18)
				replaceCurrentDigitWith(digitsListIt, DigitUtils.toDigits(multipliedDigit));
			}
		}
	}

	private void replaceCurrentDigitWith(ListIterator<Integer> replaceWhat, List<Integer> replaceWith) {
		replaceWhat.remove();
		replaceWith.forEach(replaceWhat::add);
	}

	/**
	 * 3.
	 */
	void removeAllDigitsSeven(LinkedList<Integer> digits) {
		digits.removeIf(digit -> digit == 7);
	}

	/**
	 * 4.
	 */
	int divideByNumberOfEvenDigits(LinkedList<Integer> digits) {
		int evenDigitsCount = computeEvenDigits(digits);
		if (evenDigitsCount == 0) {
			throw new NumberException("Transformed input does not contain any even digit. This would lead to division by zero");
		}

		int number = toNumberThrowsIfFails(digits);
		return divideAndRoundDown(number, evenDigitsCount);
	}

	private int computeEvenDigits(LinkedList<Integer> digits) {
		return (int) digits.stream()
				.filter(digit -> digit % 2 == 0)
				.count();
	}

	private int toNumberThrowsIfFails(LinkedList<Integer> digits) {
		try {
			return DigitUtils.toNumber(digits);
		} catch (NumberFormatException ex) {
			throw new NumberException("Transformed input is too large to fit inside integer data type", ex);
		}
	}

	private int divideAndRoundDown(int number, int divider) {
		return (number / divider);
	}
}