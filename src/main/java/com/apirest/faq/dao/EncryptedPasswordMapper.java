package com.apirest.faq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EncryptedPasswordMapper implements RowMapper<EncryptedPassword>{

	@Override
	public EncryptedPassword mapRow(ResultSet rs, int rowNum) throws SQLException {
		EncryptedPassword encryptedPassword = new EncryptedPassword();
		encryptedPassword.setPassword(rs.getString("password"));
		return encryptedPassword;
	}

}
