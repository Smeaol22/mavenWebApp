package com.apirest.faq;

import org.glassfish.jersey.server.ResourceConfig;

public class RestJaxRsApplication extends ResourceConfig {

	public RestJaxRsApplication() {

		packages("com.apirest.faq");

	}
}
