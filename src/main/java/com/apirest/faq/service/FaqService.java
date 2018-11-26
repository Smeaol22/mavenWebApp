package com.apirest.faq.service;

import java.util.List;

public interface FaqService {
	
	String getPassword();
	
	List<QuestionAnswer> getAllQuestion();
	
	List<QuestionAnswer> getQuestion(String questionLabel);
	
	void insertQuestion(String questionLabel, String AnswerLabel, List<String> tagsLabel) throws Exception;

}
