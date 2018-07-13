package com.audien.b2c.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/interceptorLogin")
public class LoginInterceptorController {
	
	@RequestMapping(value="login")
	@ResponseBody
	public String interLogin() {
		return "Login PAGE";
	}
	
	@RequestMapping(value="/board_write ")
	@ResponseBody
	public String board_write() {
		return "BOARD WRITE PAGE";
	}
	
	@RequestMapping(value="/setSession")
	public void loginCheck(HttpServletRequest request) {
		request.getSession().setAttribute("logininfo", true);
	}
}
