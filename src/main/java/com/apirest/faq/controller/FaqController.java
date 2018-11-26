package com.apirest.faq.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.apirest.faq.service.FaqService;

@Path("/faq")
public class FaqController {

	@Autowired
	@Qualifier("faqservice")
	private FaqService faqService;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCountryById(@PathParam("id") int id) {
		String value = faqService.getVal();
		return value + id;
	}
}
