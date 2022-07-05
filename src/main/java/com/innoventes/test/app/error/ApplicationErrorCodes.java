package com.innoventes.test.app.error;

public class ApplicationErrorCodes {

	private ApplicationErrorCodes() {
		super();
	}

	public static final String VALIDATIONS_FAILED_ERROR = "validations.failed";
	public static final String INVALID_JSON_REQUEST_ERROR = "request.invalid";
	public static final String DATA_INTEGRITY_CONTRAINT_VIOLATION = "data.integrity.constraint.violation";
	public static final String DATA_ACCESS_VIOLATION = "data.access.violation";

	public static final String COMPANY_NOT_FOUND = "company.notfound";
}