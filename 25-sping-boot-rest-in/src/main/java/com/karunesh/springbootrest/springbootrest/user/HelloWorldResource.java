package com.karunesh.springbootrest.springbootrest.user;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldResource {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/internalization")
	public String sayHelloInternalizarion() {
		return messageSource.getMessage("good.morning.message",
				null,LocaleContextHolder.getLocale());
	}

}
