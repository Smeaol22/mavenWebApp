package com.apirest.faq.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.apirest.faq.dao.AdminDetail;

public interface FaqService {

	AdminDetail getAdminDetail();

	List<QuestionAnswer> getAllQuestion();

	List<QuestionAnswer> getQuestion(@NotNull String questionLabel);

	void insertQuestion(@Valid QuestionAnswer questionAnswer) throws Exception;

}
