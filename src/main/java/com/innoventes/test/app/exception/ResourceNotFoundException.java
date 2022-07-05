package com.innoventes.test.app.exception;

import lombok.Getter;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 763040204220054875L;

	@Getter
	private final String errorCode;
	    
	public ResourceNotFoundException(final String message, final String errorCode) {
		super(message);
		this.errorCode = errorCode;
    }
}

