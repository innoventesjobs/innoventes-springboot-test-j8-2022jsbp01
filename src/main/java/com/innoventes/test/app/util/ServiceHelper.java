package com.innoventes.test.app.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ServiceHelper {

	@Autowired
	private MessageSource messageSource;

	public String getLocalizedMessage(String errorCode) {
		return messageSource.getMessage(errorCode, null, LocaleContextHolder.getLocale());
	}
}
