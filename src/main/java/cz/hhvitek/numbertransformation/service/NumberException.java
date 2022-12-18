package cz.hhvitek.numbertransformation.service;

public class NumberException extends RuntimeException {
	public NumberException(String errorMessage) {
		super(errorMessage);
	}

	public NumberException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}
}
