package com.neeraj.rest.webservices.restwebservices;

import java.util.Locale;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceSupport;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class RestWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWebservicesApplication.class, args);
	}

	@Bean
	public LocaleResolver myLocaleResolver() {
		SessionLocaleResolver sessionLocale = new SessionLocaleResolver();
		sessionLocale.setDefaultLocale(Locale.US);
		return sessionLocale;
	}

	@Bean
	public MessageSourceSupport messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
}
