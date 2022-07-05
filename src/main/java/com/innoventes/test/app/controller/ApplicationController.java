package com.innoventes.test.app.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.innoventes.test.app.meta.ApplicationMessages;

@RestController
public class ApplicationController {

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/")
	public ResponseEntity<String> welcome() {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.status(HttpStatus.OK).location(location)
				.body(getLocalizedMessage(ApplicationMessages.WELCOME_TEXT));
	}

	private String getLocalizedMessage(String messageCode) {
		return messageSource.getMessage(messageCode, null, LocaleContextHolder.getLocale());
	}
}
