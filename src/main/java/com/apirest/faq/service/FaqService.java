package com.apirest.faq.service;

import java.util.List;

import javax.validation.constraints.NotNull;

public interface FaqService {
	
	String getPassword();
	
	List<QuestionAnswer> getAllQuestion();
	
	List<QuestionAnswer> getQuestion(@NotNull String questionLabel);
	
	void insertQuestion(@NotNull String questionLabel, @NotNull String AnswerLabel, List<String> tagsLabel) throws Exception;

}
