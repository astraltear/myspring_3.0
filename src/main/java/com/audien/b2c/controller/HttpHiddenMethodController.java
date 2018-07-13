package com.audien.b2c.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("hiddenMethodTest/*")
public class HttpHiddenMethodController {
	private static final Logger logger = LoggerFactory.getLogger(HttpHiddenMethodController.class);
	
	@RequestMapping("init")
	public String init() {
		return "HttpHiddenMethodTest";
	}
	
	
	@RequestMapping(value="httpmethodTest", method=RequestMethod.POST)
	@ResponseBody
	public String postMethod() {
		return "call post method";
	}
	
	@RequestMapping(value="httpmethodTest", method=RequestMethod.PUT)
	@ResponseBody
	public String putMehtod() {
		return "call put method";
	}
	
	@RequestMapping(value="httpmethodTest", method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteMehtod() {
		return "call delete method";
	}
	
	
}
