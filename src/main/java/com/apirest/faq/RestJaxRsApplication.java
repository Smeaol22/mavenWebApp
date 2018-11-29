package com.apirest.faq;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.apirest.faq.spring.AuthenticationFilter;

public class RestJaxRsApplication extends ResourceConfig {

	public RestJaxRsApplication() {

		packages("com.apirest.faq.controller");
		register(LoggingFilter.class);
		
		register(AuthenticationFilter.class);

	}
}
