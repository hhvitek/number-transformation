package cz.hhvitek.numbertransformation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cz.hhvitek.numbertransformation.service.NumberException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerController {

	@ExceptionHandler(
			{NumberException.class, NumberFormatException.class, IllegalArgumentException.class}
	)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String unsupportedArgument(RuntimeException e) {
		return "Sorry, we cannot handle your input: " + e.getMessage();
	}
}
