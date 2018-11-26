package com.apirest.faq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class QuestionMapper implements RowMapper<Question> {

	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		Question question = new Question();
		question.setAnswerId(rs.getInt("answer_id"));
		question.setQuestionId(rs.getInt("question_id"));
		question.setQuestionLabel(rs.getString("question_label"));
		return question;
	}

}
