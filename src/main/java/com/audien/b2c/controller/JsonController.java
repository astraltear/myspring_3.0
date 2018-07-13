package com.audien.b2c.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audien.b2c.domain.FormVO;

@Controller
@RequestMapping("mapping/*")
public class JsonController {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonController.class);
	
//	json 처리 1
//	http://localhost:8080/b2c/directjsp
	@RequestMapping(value="/readWriteJson",  method= {RequestMethod.GET,RequestMethod.POST}, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public FormVO readWriteJson(@RequestBody FormVO formVO) {
		logger.info("Mapped by path + method + consumable media type ("+formVO.toString()+" )");
		return formVO;
	}
	
//	json 처리 2
	@RequestMapping(value="/responseEntitySample", method= RequestMethod.GET)
	public ResponseEntity<List<FormVO>> responseEntitySample(){
		FormVO formVO = new FormVO();
		formVO.setName("ASTRALTEAR");
		formVO.setAge(12);
		List list = new ArrayList<FormVO>();
		list.add(formVO);
		
		return new ResponseEntity<List<FormVO>>(list,HttpStatus.OK);
	}
	
	@RequestMapping("/writeJson2")
	@ResponseBody
	public FormVO jsonPrint2() {
		FormVO formVO = new FormVO();
		formVO.setName("testName");
		formVO.setAge(20);
		
		return formVO;
		
	}
	
	
}
