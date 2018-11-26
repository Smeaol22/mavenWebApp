package com.apirest.faq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.apirest.faq.dao.Administration;
import com.apirest.faq.dao.FaqDao;

@Service
public class DefaultFaqService implements FaqService {

	@Autowired
	
	@Qualifier("faqDao")
	private FaqDao faqDao;
	
	public String getVal() {
		Administration administration = faqDao.getAdministration();
		return administration.getLogin();
	}

}
