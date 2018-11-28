package com.apirest.faq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.apirest.faq.dao.AdminDetail;
import com.apirest.faq.dao.FaqDao;
import com.apirest.faq.dao.Question;

@Component
public class DefaultFaqService implements FaqService {

	@Autowired
	@Qualifier("faqDao")
	private FaqDao faqDao;

	@Override
	public AdminDetail getAdminDetail() {
		AdminDetail adminDetail = faqDao.getAdminDetail();
		return adminDetail;
	}

	public DefaultFaqService() {
		super();
	}

	@Override
	public List<QuestionAnswer> getAllQuestion() {
		List<Question> questionList = faqDao.getAllQuestion();
		List<QuestionAnswer> questionAnswerList = retreiveQuestionAnswerList(questionList);
		return questionAnswerList;
	}

	@Override
	public List<QuestionAnswer> getQuestion(String questionLabel) {
		List<Question> questionList = faqDao.getQuestion(questionLabel);
		List<QuestionAnswer> questionAnswerList = retreiveQuestionAnswerList(questionList);
		return questionAnswerList;
	}

	@Override
	public void insertQuestion(QuestionAnswer questionAnswer) throws Exception {
		String questionLabel = questionAnswer.getQuestionLabel();
		if (faqDao.isQuestionAlreadyInBase(questionLabel)) {
			throw new Exception("Question already registered");
		}
		int answerId = getAnswerIdOrCreate(questionAnswer.getAnswerLabel());
		faqDao.insertQuestion(questionLabel, answerId);
		int questionId = faqDao.getQuestion(questionLabel).get(0).getQuestionId();
		List<String> tagsLabelList = questionAnswer.getTagsLabelList();
		if (tagsLabelList != null && !tagsLabelList.isEmpty()) {
			List<Integer> tagsId = getTagsIdOrCreate(tagsLabelList);
			if (!tagsId.isEmpty()) {
				for (int tagId : tagsId) {
					faqDao.addTagToQuestion(tagId, questionId);
				}
			}
		}
	}

	private List<QuestionAnswer> retreiveQuestionAnswerList(List<Question> questionList) {
		List<QuestionAnswer> questionAnswerList = new ArrayList<QuestionAnswer>();
		for (Question question : questionList) {
			QuestionAnswer questionAnswer = new QuestionAnswer();
			questionAnswer.setQuestionLabel(question.getQuestionLabel());
			questionAnswer.setAnswerLabel(faqDao.getAnswerLabel(question.getAnswerId()));
			questionAnswer.setTagsLabelList(faqDao.getTagsLabel(question.getQuestionId()));
			questionAnswerList.add(questionAnswer);
		}
		return questionAnswerList;
	}

	private List<Integer> getTagsIdOrCreate(List<String> tagsLabel) throws Exception {
		List<Integer> tagsId = new ArrayList<Integer>();
		if (tagsLabel != null) {
			int tagId;
			for (String tagLabel : tagsLabel) {
				tagId = faqDao.getTagId(tagLabel);
				if (tagId == 0) {
					faqDao.createNewTag(tagLabel);
					tagId = faqDao.getTagId(tagLabel);
				}
				tagsId.add(tagId);
			}
		}
		return tagsId;
	}

	private int getAnswerIdOrCreate(String answerLabel) throws Exception {
		int answerId = faqDao.getAnswerId(answerLabel);
		if (answerId == 0) {
			faqDao.createNewAnswer(answerLabel);
			answerId = faqDao.getAnswerId(answerLabel);
		}
		return answerId;
	}

}
