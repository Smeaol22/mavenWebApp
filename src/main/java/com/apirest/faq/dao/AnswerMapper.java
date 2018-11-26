package com.apirest.faq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AnswerMapper implements RowMapper<Answer> {

	@Override
	public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Answer answer = new Answer();
		answer.setLabel(rs.getString("label"));
		return answer;
	}

}
