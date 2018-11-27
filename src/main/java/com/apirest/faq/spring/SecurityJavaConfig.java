package com.apirest.faq.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.apirest.faq.dao.AdminDetail;
import com.apirest.faq.service.FaqService;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private FaqService faqService;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;

	private SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.csrf()
			.disable()
			.exceptionHandling()
			.authenticationEntryPoint(restAuthenticationEntryPoint)
			.and()
			.authorizeRequests()
			.antMatchers("/api/faq").permitAll()
			.antMatchers("/api/admin/**").hasRole("ADMIN")
			.and()
			.formLogin()
			.successHandler(mySuccessHandler)
			.failureHandler(myFailureHandler)
			.and()
			.logout();
		// @formatter:on

	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		AdminDetail adminDetail = faqService.getAdminDetail();
		auth.inMemoryAuthentication().withUser(adminDetail.getLogin()).password(adminDetail.getPassword())
				.roles("ADMIN");
	}

}
