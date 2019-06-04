package com.cafe24.config.web;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cafe24.security.AuthAdminInterceptor;
import com.cafe24.security.AuthLoginInterceptor;
import com.cafe24.security.AuthLogoutInterceptor;

public class SecurityConfig extends WebMvcConfigurerAdapter {
	@Bean 
	public AuthLoginInterceptor authLoginInterceptor() {
		return new AuthLoginInterceptor();
	}
	
	@Bean 
	public AuthLogoutInterceptor authLogoutInterceptor() {
		return new AuthLogoutInterceptor();
	}
	
	@Bean 
	public AuthAdminInterceptor authAdminInterceptor() {
		return new AuthAdminInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
		.addInterceptor(authLoginInterceptor())
		.addPathPatterns("/user/auth");
		
		
		registry
		.addInterceptor(authLogoutInterceptor())
		.addPathPatterns("/user/logout");
		
		
		registry
		.addInterceptor(authAdminInterceptor())
		.addPathPatterns("/**/admin/category/**")
		.addPathPatterns("/**/admin/write/**")
		.addPathPatterns("/**/admin/basic/**");
		
		
		
		
	}	
	
	//
	// Interceptor
	//
	
	
	
	
	
	
}
