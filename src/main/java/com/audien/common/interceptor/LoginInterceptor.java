package com.audien.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			if(request.getSession().getAttribute("logininfo") == null) {
				response.sendRedirect("/b2c/interceptorLogin/login");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
