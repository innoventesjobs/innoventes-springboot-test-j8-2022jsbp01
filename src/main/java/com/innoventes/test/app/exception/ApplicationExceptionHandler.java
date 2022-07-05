package com.innoventes.test.app.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.innoventes.test.app.error.ApplicationErrorCodes;

@RestController
@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException exception) {
		ExceptionDetails resourceNotFoundExceptionDetails = new ExceptionDetails(HttpStatus.NOT_FOUND, exception, exception.getMessage());
		return buildResponseEntity(resourceNotFoundExceptionDetails);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
		ExceptionDetails dataIntegrityViolationExceptionDetails = new ExceptionDetails(HttpStatus.UNPROCESSABLE_ENTITY,
				exception, getMessage(ApplicationErrorCodes.DATA_INTEGRITY_CONTRAINT_VIOLATION));
		return buildResponseEntity(dataIntegrityViolationExceptionDetails);
	}

	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<Object> handleInvalidDataAccessApiUsage(InvalidDataAccessApiUsageException exception) {
		ExceptionDetails invalidDataAccessApiUsageExceptionDetails = new ExceptionDetails(HttpStatus.BAD_REQUEST,
				exception, getMessage(ApplicationErrorCodes.DATA_ACCESS_VIOLATION));
		return buildResponseEntity(invalidDataAccessApiUsageExceptionDetails);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException exception) {
		ExceptionDetails messageNotReadableExceptionDetails = new ExceptionDetails(HttpStatus.UNPROCESSABLE_ENTITY, exception,
				getMessage(ApplicationErrorCodes.INVALID_JSON_REQUEST_ERROR));
		return buildResponseEntity(messageNotReadableExceptionDetails);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionDetails exceptionDetails = new ExceptionDetails(status, exception,
				getMessage(ApplicationErrorCodes.INVALID_JSON_REQUEST_ERROR));
		return buildResponseEntity(exceptionDetails);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ValidationExceptionDetails validationExceptionDetails = new ValidationExceptionDetails(status, exception,
				getMessage(ApplicationErrorCodes.VALIDATIONS_FAILED_ERROR));
		validationExceptionDetails.addFieldValidationErrors(exception.getBindingResult().getFieldErrors());
		validationExceptionDetails.addGlobalValidationErrors(exception.getBindingResult().getGlobalErrors());
		return buildResponseEntity(validationExceptionDetails);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ValidationExceptionDetails validationExceptionDetails = new ValidationExceptionDetails(status, exception,
				getMessage(ApplicationErrorCodes.VALIDATIONS_FAILED_ERROR));
		validationExceptionDetails.addFieldValidationErrors(exception.getBindingResult().getFieldErrors());
		validationExceptionDetails.addGlobalValidationErrors(exception.getBindingResult().getGlobalErrors());
		return buildResponseEntity(validationExceptionDetails);
	}
	
	public String getMessage(String exceptionCode) {
		return messageSource.getMessage(exceptionCode, null, LocaleContextHolder.getLocale());
	}
	
	private ResponseEntity<Object> buildResponseEntity(ExceptionDetails exceptionDetails) {
		return new ResponseEntity<>(exceptionDetails, exceptionDetails.getHttpStatusObj());
	}
}
