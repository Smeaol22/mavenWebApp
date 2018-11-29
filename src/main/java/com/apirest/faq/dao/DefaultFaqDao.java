package com.apirest.faq.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.apirest.faq.dao.object.AdminDetail;
import com.apirest.faq.dao.object.AdminDetailMapper;
import com.apirest.faq.dao.object.Question;
import com.apirest.faq.dao.object.QuestionMapper;

@Repository("faqDao")
public class DefaultFaqDao implements FaqDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplateObject;

	String GETADMINDETAIL_SQL = "SELECT login_label, password_label FROM ADMINISTRATION ";

	String GETANSWERLABEL_SQL = "SELECT answer_label FROM ANSWER WHERE answer_id = '";

	String GETTAGSLABEL_SQL = "SELECT tags_label FROM TAGS WHERE tags_id IN (SELECT tags_id FROM QUESTIONTAGSRELATION WHERE question_id = '";

	String GETQUESTION_SQL = "SELECT * FROM QUESTION";

	String RESTRICTQUESTION_SQL = " WHERE question_label LIKE '%";

	String GETTAGID_SQL = "SELECT tags_id FROM TAGS WHERE tags_label = '";

	String ADDTAGTOQUESTION_SQL = "INSERT INTO QUESTIONTAGSRELATION(question_id, tags_id) VALUES('";

	String INSERTTAG_SQL = "INSERT INTO TAGS(tags_label) VALUES('";

	String GETANSWERID_SQL = "SELECT answer_id FROM ANSWER WHERE answer_label = '";

	String INSERTQUESTION_SQL = "INSERT INTO QUESTION(question_label, answer_id) VALUES ('";

	String INSERTANSWER_SQL = "INSERT INTO ANSWER(answer_label) VALUES('";

	String COUNTQUESTION_SQL = "SELECT COUNT(*) FROM QUESTION WHERE question_label = '";

	@Override
	public AdminDetail getAdminDetail() {
		AdminDetail adminDetail = jdbcTemplateObject.queryForObject(GETADMINDETAIL_SQL, new AdminDetailMapper());
		return adminDetail;
	}

	@Override
	public String getAnswerLabel(int answerId) {
		String answerLabel = jdbcTemplateObject.queryForObject(GETANSWERLABEL_SQL + answerId + "'", String.class);
		return answerLabel;
	}

	@Override
	public List<String> getTagsLabel(int questionId) {
		List<String> tagsLabel = jdbcTemplateObject.queryForList(GETTAGSLABEL_SQL + questionId + "')", String.class);
		return tagsLabel;
	}

	@Override
	public List<Question> getQuestion(String questionLabel) {
		List<Question> questions = jdbcTemplateObject.query(
				GETQUESTION_SQL + RESTRICTQUESTION_SQL + StringParser(questionLabel) + "%'", new QuestionMapper());
		return questions;
	}

	@Override
	public List<Question> getAllQuestion() {
		List<Question> questions = jdbcTemplateObject.query(GETQUESTION_SQL, new QuestionMapper());
		return questions;
	}

	@Override
	public boolean isQuestionAlreadyInBase(String questionLabel) {
		int countQuestion = jdbcTemplateObject.queryForObject(COUNTQUESTION_SQL + StringParser(questionLabel) + "'",
				Integer.class);
		return countQuestion != 0;
	}

	@Override
	public int getTagId(String taglabel) throws Exception {
		return retreiveTagId(taglabel);
	}

	@Override
	public void addTagToQuestion(int tagId, int questionId) {
		jdbcTemplateObject.execute(ADDTAGTOQUESTION_SQL + questionId + "','" + tagId + "')");

	}

	@Override
	public int createNewTag(String tagLabel) throws Exception {
		jdbcTemplateObject.execute(INSERTTAG_SQL + StringParser(tagLabel) + "')");
		return retreiveTagId(tagLabel);
	}

	@Override
	public int getAnswerId(String answerLabel) throws Exception {
		return retreiveAnswerId(StringParser(answerLabel));
	}

	@Override
	public void createNewAnswer(String answerLabel) throws Exception {
		jdbcTemplateObject.execute(INSERTANSWER_SQL + StringParser(answerLabel) + "')");
	}

	@Override
	public void insertQuestion(String questionLabel, int answerId) {
		jdbcTemplateObject.execute(INSERTQUESTION_SQL + questionLabel + "','" + answerId + "')");

	}

	private int retreiveTagId(String taglabel) throws Exception {
		int getTagId = -1;
		if (taglabel != null) {
			try {
				String answer = jdbcTemplateObject.queryForObject(GETTAGID_SQL + StringParser(taglabel) + "'",
						String.class);
				getTagId = Integer.parseInt(answer);
			} catch (EmptyResultDataAccessException e) {
				getTagId = 0;
			}
		} else {
			throw new Exception("Invalid taglabel");
		}
		return getTagId;
	}

	private int retreiveAnswerId(String answerLabel) throws Exception {
		int answerId = -1;
		if (answerLabel != null) {
			try {
				String answer = jdbcTemplateObject.queryForObject(GETANSWERID_SQL + StringParser(answerLabel) + "'",
						String.class);
				answerId = Integer.parseInt(answer);
			} catch (EmptyResultDataAccessException e) {
				answerId = 0;
			}
		} else {
			throw new Exception("Invalid answerLabel");
		}
		return answerId;
	}

	private String StringParser(String inputString) {
		List<String> forbidenCharacterList = Arrays.asList("*", "/", "\\", ";", "'", "[", "]", "\"");
		for (String forbidenCharacter : forbidenCharacterList) {
			inputString.replace(forbidenCharacter, "");
		}
		return inputString;
	};

}
