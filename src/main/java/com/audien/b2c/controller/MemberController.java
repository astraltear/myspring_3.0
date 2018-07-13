package com.audien.b2c.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.audien.b2c.domain.MemberVO;
import com.audien.b2c.service.MemberService;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	MemberService memberService;

	@RequestMapping(value = "/slave", method = RequestMethod.GET)
	public ResponseEntity<List<MemberVO>> getMemberJacksonSlave() {

		logger.debug("MemberController start !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		List list = new ArrayList<MemberVO>();

		list = memberService.readMemberAllSlave();

		return new ResponseEntity<List<MemberVO>>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/master", method = RequestMethod.GET)
	public ResponseEntity<List<MemberVO>> getMemberJacksonMaster() {

		logger.debug("MemberController start !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		List list = new ArrayList<MemberVO>();

		list = memberService.readMemberAllMaster();

		return new ResponseEntity<List<MemberVO>>(list, HttpStatus.OK);
	}

}
