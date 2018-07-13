package com.audien.b2c.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/redisExam")
public class RedisController {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisController.class);

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String, String> valueOps;
	
	@RequestMapping("/redisData")
	@ResponseBody
	public String redisData() {
		
		valueOps.set("samplekey", "sample value 샘플 값");
		
		logger.info(" sample key {}", valueOps.get("samplekey"));
		
		return "redisData";
	}
	
}
