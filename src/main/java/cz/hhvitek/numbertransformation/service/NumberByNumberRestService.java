package cz.hhvitek.numbertransformation.service;

import java.util.Arrays;
import java.util.LinkedList;

import org.springframework.stereotype.Service;

@Service
public class NumberByNumberRestService implements RestService {
	@Override
	public int transform(int number) {
		int afterFirstStep = digitsLessThanThreeMoveToRight(number);
		int afterSecondStep = digitsEightAndNineMultiplyByTwo(afterFirstStep);
		int afterThirdStep = removeAllDigitsSeven(afterSecondStep);
		return divideByCountOfEvenDigits(afterThirdStep);
	}

	int digitsLessThanThreeMoveToRight(int number) {
		Integer[] digitsArray = DigitUtils.toDigitsArray(number);

		for (int i = digitsArray.length - 1; i >= 0; i--) {
			Integer digit = digitsArray[i];
			boolean isNotLastDigit = i != digitsArray.length - 1; // last digit cannot be moved to the right
			if (digit <= 3 && isNotLastDigit) {
				Integer followingDigit = digitsArray[i + 1];
				if (followingDigit > 3) { // do not move if the following is also <= 3
					digitsArray[i + 1] = digit;
					digitsArray[i] = followingDigit;
				}
			}
		}

		return DigitUtils.toNumber(digitsArray);
	}

	int digitsEightAndNineMultiplyByTwo(int number) {
		Integer[] digitsArray = DigitUtils.toDigitsArray(number);

		for (int i = 0; i < digitsArray.length; i++) {
			Integer digit = digitsArray[i];
			if (digit == 8 || digit == 9) {
				digitsArray[i] *= 2; // now it is no longer a "digit" array
			}
		}

		try {
			return DigitUtils.toNumber(digitsArray);
		} catch (NumberFormatException ex) {
			throw new NumberException("Transformed input cannot be processed. It wont fit inside integer after all eights and nines are multiplied by 2.", ex);
		}
	}

	int removeAllDigitsSeven(int number) {
		LinkedList<Integer> digits = DigitUtils.toDigits(number);
		digits.removeIf(digit -> digit == 7);

		if (digits.isEmpty()) {
			throw new NumberException("No digit remaining after removal of all sevens digits");
		}
		return DigitUtils.toNumber(digits);
	}

	int divideByCountOfEvenDigits(int number) {
		Integer[] digits = DigitUtils.toDigitsArray(number);

		int evenDigitsCount = numberOfEvenDigits(digits);
		if (evenDigitsCount == 0) {
			throw new NumberException("Transformed input does not contain any even digit. This would lead to division by zero");
		}

		return divideAndRoundDown(number, evenDigitsCount);
	}

	int numberOfEvenDigits(Integer[] digits) {
		return (int) Arrays.stream(digits)
				.filter(digit -> digit % 2 == 0)
				.count();
	}

	int divideAndRoundDown(int number, int divider) {
		return number / divider;
	}
}
