package com.audien.b2c.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AnnotationExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String handleException(Model model, Exception ex) {
		
		model.addAttribute("erroMsg", ex.getMessage());
		return "/status/exceptionPage";
	}
}
