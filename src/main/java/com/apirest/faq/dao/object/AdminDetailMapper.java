package com.apirest.faq.dao.object;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdminDetailMapper implements RowMapper<AdminDetail> {

	@Override
	public AdminDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminDetail adminDetail = new AdminDetail();
		adminDetail.setLogin(rs.getString("login_label"));
		adminDetail.setPassword(rs.getString("password_label"));
		return adminDetail;
	}
}
