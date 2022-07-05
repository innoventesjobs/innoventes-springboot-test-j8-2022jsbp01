package com.innoventes.test.app.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class I18nConfigurations {

	public static final String UTF8_ENCODING = "UTF-8";
	public static final String ERROR_MESSAGES = "i18n.errorMessages";
	public static final String APPLICATION_MESSAGES = "i18n.applicationMessages";

	private static String[] baseNames = { ERROR_MESSAGES, APPLICATION_MESSAGES };

	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	/*
	 * The same can be done using application.properties file also e.g
	 * spring.messages.basename=i18n.errorMessages
	 * 
	 * If Java code is used to specify message source, ensure that the method name
	 * is messageSource()
	 */
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

		messageSource.setBasenames(baseNames);
		messageSource.setDefaultEncoding(UTF8_ENCODING);
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.setValidationMessageSource(messageSource());
		return localValidatorFactoryBean;
	}
}
