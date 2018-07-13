package com.audien.b2c.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.audien.b2c.domain.FormVO;

@Controller
@RequestMapping("/form")
public class FormController {

	private static final Logger logger = LoggerFactory.getLogger(FormController.class);
	/*
		@ModelAttribute가 붙은 createFormVo()메서드를 볼 수 있는데 이 메서드는 해당 컨트롤러로 접근하려는 모든 요청에 
		@ModelAttribute가 붙은 메서드의 리턴 값을 설정된 모델명으로 자동 포함해주는 역할을 담당해준다. 
		물론 이미 동일한 이름의 모델이 생성되었있다면 위의 메서드 값은 포함되지 않으며 오로지 설정한 모델명과 일치하는 객체가 존재하지 않는 경우에만 메서드의 리턴 값을 서버의 응답과 함께 클라이언트에게 전송하는 역할을 담당한다.
  
	
	@ModelAttribute("formVo")
	public FormVO createFormVo() {
		return new FormVO();
	}
 */	
	@RequestMapping(value="/springForm", method=RequestMethod.GET)
	public String springForm(Model model) {
		model.addAttribute("formVo", new FormVO());
		return "springForm";
	}
	
	@RequestMapping(value="/springFormSubmit",  method=RequestMethod.POST)
	public String springFormSubmit(@Valid @ModelAttribute("formVo") FormVO formVo,BindingResult result, Model model) {
		logger.info("FormController.springFormSubmit formVO:"+formVo.toString());
		logger.info("FormController.springFormSubmit result.hasErrors():"+result.hasErrors());
		
		if (result.hasErrors()) {
			return "springForm";
			
		} else {
			String message = "Form submitted successfully.  Bound " + formVo;
			model.addAttribute("message", message);
			return "done";
		}
	}
	
	@RequestMapping(value="/plainForm", method=RequestMethod.GET)
	public String plainForm(Model model) {
		model.addAttribute("formVo", new FormVO());
		return "plainForm";
	}
	
	@RequestMapping(value="/plainFormSubmit",  method=RequestMethod.POST)
	public String plainFormSubmit(@Valid FormVO formVo,BindingResult result, Model model) {
		logger.info("FormController.plainFormSubmit formVO:"+formVo.toString());
		logger.info("FormController.plainFormSubmit result.hasErrors():"+result.hasErrors());
		
		if (result.hasErrors()) {
			return "plainForm";
			
		} else {
			String message = "Form submitted successfully.  Bound " + formVo;
			model.addAttribute("message", message);
			return "done";
		}
	}
	
	@RequestMapping(value="/getRequest", method=RequestMethod.GET)
	@ResponseBody
	public String getRequest() {
		logger.info("FormController.getRequest ");
		return "getRequest";
	}
	

}
