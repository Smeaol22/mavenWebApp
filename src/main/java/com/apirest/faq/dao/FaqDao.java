package com.apirest.faq.dao;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.apirest.faq.dao.object.AdminDetail;
import com.apirest.faq.dao.object.Question;

public interface FaqDao {

	/**
	 * getAdminDetail
	 * 
	 * @return AdminDetail
	 */
	AdminDetail getAdminDetail();

	/**
	 * get answer for a specific answerId
	 * 
	 * @param int answerId
	 * @return answer
	 */
	String getAnswerLabel(int answerId);

	/**
	 * get list of tags for a specific questionId
	 * 
	 * @param int questionId
	 * @return List of Tags
	 */
	List<String> getTagsLabel(int questionId);

	/**
	 * get Question from question label
	 * 
	 * @param questionLabel can be partial
	 * @return List of Question
	 */
	List<Question> getQuestion(@NotNull String questionLabel);

	/**
	 * get all question
	 * 
	 * @param questionLabel can be partial
	 * @return List of Question
	 */
	List<Question> getAllQuestion();
	
	/**
	 * return if question exist in base
	 * 
	 * @param strict questionLabel 
	 * @return boolean
	 */
	boolean isQuestionAlreadyInBase(@NotNull String questionLabel);

	/**
	 * retrieve tagId if tag already exist exist otherwise 0
	 * 
	 * @param taglabel
	 * @return tagId
	 * @throws Exception
	 */
	int getTagId(@NotNull String taglabel) throws Exception;

	/**
	 * retrieve answerId if answer already exist otherwise 0
	 * 
	 * @param answerLabel
	 * @return
	 * @throws Exception
	 */
	int getAnswerId(@NotNull String answerLabel) throws Exception;

	/**
	 * associate tag to a question
	 * 
	 * @param tagId
	 * @param questionId
	 */
	void addTagToQuestion(int tagId, int questionId);

	/**
	 * add new tag in base
	 * 
	 * @param tagLabel
	 * @return tagId
	 * @throws Exception
	 */
	int createNewTag(@NotNull String tagLabel) throws Exception;

	/**
	 * add new answer in base
	 * 
	 * @param answerLabel
	 * @throws Exception
	 */
	void createNewAnswer(@NotNull String answerLabel) throws Exception;

	/**
	 * add new question
	 * 
	 * @param questionLabel
	 * @param answerId
	 */
	void insertQuestion(@NotNull String questionLabel, int answerId);

}
