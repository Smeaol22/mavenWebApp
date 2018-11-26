package com.apirest.faq.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("faqDao")
public class DefaultFaqDao implements FaqDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplateObject;

	String SQL = "SELECT * FROM ADMINISTRATION";

	@Override
	public Administration getAdministration() {
		List<Administration> administrations = jdbcTemplateObject.query(SQL, new AdministrationMapper());
		return administrations.get(0);
	}

}
