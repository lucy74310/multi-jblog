package com.cafe24.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.vo.BlogVo;

public class BlogExistInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("blogExistInterceptor");
		
		if( handler instanceof HandlerMethod == false) {
			//이미지,자바스크립트,css파일 등 
			return true; 
		}
		
		Map pvId = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		System.out.println(pvId);
		if (pvId == null ) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		
		
		ApplicationContext ac = 
				WebApplicationContextUtils.
				getWebApplicationContext(request.getServletContext());
		
		BlogService blogService = ac.getBean(BlogService.class);
		
		BlogVo blogVo = blogService.get(pvId.get("id").toString());
		System.out.println("blogVo: " + blogVo);
		if(blogVo == null) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		System.out.println(pvId.get("id"));
		
		return true;
	}

}
