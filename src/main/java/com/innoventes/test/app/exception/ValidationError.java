package com.innoventes.test.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationError implements AbstractError {
	private String objectName;
	private String field;
	private Object invalidValue;
	private String message;

	public ValidationError(String objectName, String message) {
		this.objectName = objectName;
		this.message = message;
	}
}
