package com.innoventes.test.app.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public class ValidationException extends BindException {
	
	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = -3008190810654408281L;
	
	public ValidationException(BindingResult bindingResult) {
		super(bindingResult);
    }
}
