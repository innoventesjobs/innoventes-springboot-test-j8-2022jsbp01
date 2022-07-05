package com.innoventes.test.app.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import lombok.Getter;

@Getter
public class ValidationExceptionDetails extends ExceptionDetails {

	private List<AbstractError> errors;

	public ValidationExceptionDetails(HttpStatus status, Throwable exception, String message) {
		super(status, exception, message);
		errors = new ArrayList<>();
	}

	public void addFieldValidationErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(this::addValidationError);
	}

	public void addGlobalValidationErrors(List<ObjectError> globalErrors) {
		globalErrors.forEach(this::addValidationError);
	}

	private void addValidationError(ObjectError objectError) {
		this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
	}

	private void addValidationError(String objectName, String message) {
		addError(new ValidationError(objectName, message));
	}
	
	private void addError(AbstractError error) {
		if (errors == null) {
			errors = new ArrayList<>();
		}
		errors.add(error);
	}
}