package com.audien.b2c.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DotDoController {
	
/*
.do를 받는 방법 
 */
	
	private static final Logger logger = LoggerFactory.getLogger(DotDoController.class);
	
//	http://localhost:8080/b2c/dotDoSample.do?cmd=abcd&userId=astral&name=다현
	@RequestMapping("/dotDoSample.do")
	public String doRequest(HttpServletRequest request,@RequestParam Map<String, String> params) {
		logger.info("request.getRequestURI() {}",request.getRequestURI());
		logger.info("cmd:[{}]userId:[{}]",params.get("cmd"),params.get("userId"));
		logger.info("name: {}",params.get("name"));
		
		return "dotDoSample";
	}
	
}
