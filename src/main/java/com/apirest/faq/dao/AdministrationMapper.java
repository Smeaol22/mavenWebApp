package com.apirest.faq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdministrationMapper implements RowMapper<Administration>{

	@Override
	public Administration mapRow(ResultSet rs, int rowNum) throws SQLException {
		Administration administration = new Administration();
		administration.setLogin(rs.getString("login"));
		administration.setPassword(rs.getString("password"));
		return administration;
	}

}
