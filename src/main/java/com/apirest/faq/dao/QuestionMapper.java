package com.apirest.faq.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

public class QuestionMapper implements RowMapper<Question> {

	@Override
	public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<Integer> answerIdList = new ArrayList<Integer>();
		List<String> tagList = new ArrayList<String>();
		Question question = new Question();
		question.setLabel(rs.getString("question_label"));
		while (rs.next()) {
			if (rs.getString("answer_id") != null) {
				answerIdList.add(rs.getInt("answer_id"));
			}

			if (rs.getString("question_tag") != null) {
				tagList.add(rs.getString("question_tag"));
			}
		}

		question.setAnswerId(answerIdList);
		question.setTags(tagList);
		return null;
	}

}
