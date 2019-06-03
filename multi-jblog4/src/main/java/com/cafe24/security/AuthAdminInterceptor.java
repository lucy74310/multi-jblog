package com.cafe24.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cafe24.jblog.vo.UserVo;

public class AuthAdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Map pvId = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		if (pvId == null ) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		
		HttpSession session = request.getSession();
		
		if(session == null ) { // 인증이 안되어 있음
			response.sendRedirect(request.getContextPath() +"/" + pvId);
			return false;
		}
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		if(authUser == null) { // 인증이 안되어 있음
			response.sendRedirect(request.getContextPath() + "/"+pvId);
			return false;
		}
		
		// authUser 있음
		if(pvId.get("id").toString().equals(authUser.getId())) {
			return true;
		}
		
		response.sendRedirect(request.getContextPath() + "/"+pvId);
		return false;
	}

	
}
