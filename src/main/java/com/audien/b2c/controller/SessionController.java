package com.audien.b2c.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.audien.b2c.domain.User;

@Controller
@RequestMapping("/user/*")
@SessionAttributes("user")
public class SessionController {
	
	private static final Logger logger = LoggerFactory.getLogger(SessionController.class);
	
	@RequestMapping("/step1")
	public String step1(Model model) {
		User user = new User();
		user.setUserid("test");
		model.addAttribute("user", user);
		return "/session/step1";
	}

	@RequestMapping("/step2")
	public String step2(@ModelAttribute("user") User user,Model model) {
		logger.info("SessionController.step2::"+user.toString());
		return "/session/step2";
	}
	
	@RequestMapping("/step3")
	public String step3(@ModelAttribute("user") User user,Model model,HttpSession httpSession, SessionStatus session) {
		logger.info("SessionController.step3::"+user.toString());
		
		logger.info("httpSession:["+((User)httpSession.getAttribute("user")).toString()+"]");
		
		session.setComplete();
		return "/session/step3";
	}
	
	@RequestMapping("/step4")
	public String step4(@ModelAttribute("user") User user,Model model, SessionStatus session) {
		logger.info("SessionController.step4::"+user.toString());
		return "/session/step4";
	}
}
