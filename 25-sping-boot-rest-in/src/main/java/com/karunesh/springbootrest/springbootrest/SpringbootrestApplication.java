package com.karunesh.springbootrest.springbootrest;


import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SpringbootrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootrestApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
//	@Bean
//	public ResourceBundleMessageSource bundleMessageSource() {
//		ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
//		bundleMessageSource.setBasename("messages");
//		return bundleMessageSource;
//	}
	

}
