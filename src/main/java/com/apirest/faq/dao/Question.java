package com.apirest.faq.dao;

import java.util.List;

public class Question {

	public Question() {}

	private String label;
	
	private List<String> tags;
	
	private List<Integer> answerId;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<Integer> getAnswerId() {
		return answerId;
	}

	public void setAnswerId(List<Integer> answerId) {
		this.answerId = answerId;
	}	
	
}
