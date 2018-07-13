package com.audien.b2c.controller;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audien.common.utils.SessionStore;

@Controller
@RequestMapping("/account/*")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, @RequestParam(value = "userId", required = false) String userId) {

		if (StringUtils.defaultString(userId).trim().equals("")) {
		} else {
			// 로그인 성공
			HttpSession session = request.getSession();

			SessionStore sessionStore = SessionStore.getInstance();
			sessionStore.setSession(session, userId);

		}

		return "loginPage";
	}

	@RequestMapping("/otherLink")
	public String otherLink(HttpServletRequest request) {

		HttpSession session = request.getSession();
		SessionStore sessionStore = SessionStore.getInstance();
		String userId = (String) session.getAttribute("userId");
		logger.info("otherLink::userId[" + userId + "]sessionID[" + session.getId() + "]");
		boolean isEqual = sessionStore.isEqualSession(session.getId(), userId);

		if (isEqual) {
			return "otherLink";
		} else {
			logger.info("중복로그인 ");
			return "forward:/account/logout";
		}
	}
	
	@RequestMapping("/contextInfo")
	@ResponseBody
	public String contextInfo(HttpServletRequest request) {
		
		HttpSession session = request.getSession();

		ServletContext context = session.getServletContext();
		context.setAttribute("abc", "123");
		Enumeration<String> obj = context.getAttributeNames();
		

		System.out.println("---------- context getAttributeNames start--------------");
		while (obj.hasMoreElements()) {
			String contextName = (String) obj.nextElement();
			System.out.println("contextName["+contextName+"]contextValue["+context.getAttribute(contextName)+"]");
		}
		System.out.println("---------- context getAttributeNames end--------------");
		
		return "contextInfo";
	}
	

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {

		SessionStore sessionStore = SessionStore.getInstance();
		sessionStore.removeKey(request.getSession().getId());
		request.getSession().invalidate();
		return "redirect:/";
	}

}
