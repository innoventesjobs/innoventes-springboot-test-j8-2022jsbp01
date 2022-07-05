package com.innoventes.test.app.exception;

import java.time.LocalDateTime;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;

@Getter
public class ExceptionDetails {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	@JsonIgnore
	private HttpStatus httpStatusObj;
	private int status;
	private String error;
	private String message;
	private String trace;
	private String path;

	public ExceptionDetails(HttpStatus status, Throwable exception, String message) {
		this.timestamp = LocalDateTime.now();
		this.httpStatusObj = status;
		this.status = status.value();
		this.error = status.getReasonPhrase();
		this.message = message;
		this.trace = ExceptionUtils.getStackTrace(exception);
		this.path = ServletUriComponentsBuilder.fromCurrentRequest().build().getPath();
	}
}
