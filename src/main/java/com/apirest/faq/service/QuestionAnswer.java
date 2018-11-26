package com.apirest.faq.service;

import java.util.List;

public class QuestionAnswer {

	public QuestionAnswer() {
		super();
		this.tagsLabelList = null;
	}

	private String questionLabel;

	private String answerLabel;

	private List<String> tagsLabelList;

	public String getQuestionLabel() {
		return questionLabel;
	}

	public void setQuestionLabel(String questionLabel) {
		this.questionLabel = questionLabel;
	}

	public String getAnswerLabel() {
		return answerLabel;
	}

	public void setAnswerLabel(String answerLabel) {
		this.answerLabel = answerLabel;
	}

	public List<String> getTagsLabelList() {
		return tagsLabelList;
	}

	public void setTagsLabelList(List<String> tagsLabelList) {
		this.tagsLabelList = tagsLabelList;
	}

}
