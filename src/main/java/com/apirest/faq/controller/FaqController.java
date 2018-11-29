package com.apirest.faq.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apirest.faq.service.FaqService;
import com.apirest.faq.service.QuestionAnswer;

@Component
@Path("/api")
public class FaqController {

	@Autowired
	private FaqService faqService;

	/**
	 * For all user as for a question (or part of it)
	 * and received the answer the tag(s) associated
	 * If part of question, can received a list of possible matching
	 * @param question
	 * @return
	 */
	@PermitAll
	@GET
	@Path("/faq/{question}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<QuestionAnswer> getQuestion(@PathParam("question") String question) {
		return faqService.getQuestion(question);
	}

	/**
	 * For Admin only
	 * @return all the question, answer and associated tag(s)
	 */
	@RolesAllowed("ADMIN")
	@GET
	@Path("/admin/question/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<QuestionAnswer> getAllQuestion() {
		return faqService.getAllQuestion();
	}
	
	/**
	 * For admin only 
	 * can insert question, with answer and associated tag(s)
	 * @param questionAnswer
	 * @return
	 */
	@RolesAllowed("ADMIN")
	@POST
	@Path("/admin/question/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertQuestion(QuestionAnswer questionAnswer) {
		String answer = "Record ";
		try {
			faqService.insertQuestion(questionAnswer);
			answer += "succed";
		} catch (Exception e) {
			e.printStackTrace();
			answer += "failed: " + e.toString();
		}
		return answer;
	}

}
