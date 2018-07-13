package com.audien.b2c.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audien.b2c.domain.MemberVO;
import com.audien.b2c.service.SqlMapperService;

@Controller
@RequestMapping("/sqlMapperSample")
public class SqlMapperController {
	
	private static final Logger logger = LoggerFactory.getLogger(SqlMapperController.class);

	
	@Inject
	private SqlMapperService sqlMapperService;
	
	@RequestMapping("/getTime")
	@ResponseBody
	public String getTime() {
		String getTime = sqlMapperService.getTime();
		
		logger.info("SqlMapperController::getTime:"+getTime);
		
		return "getTime";
	}

	@RequestMapping("/selectBasic")
	@ResponseBody
	public String selectBasic() {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUserid("ASTRAL");
		
		List<MemberVO> list = sqlMapperService.selectBasic(memberVO);
		
		list.forEach(System.out::println);
		list.forEach(item -> logger.info("selectBasic print logger1:"+item.toString()));
		list.stream().forEach(item -> logger.info("selectBasic print logger2:"+item.toString()));
		
		return "selectMapper";
	}
	
	@RequestMapping("/multiInsert")
	@ResponseBody
	public String multiInsert() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		for (int i = 0; i < 10; i++) {
			MemberVO memberVO = new MemberVO();
			memberVO.setUserid(i+"");
			memberVO.setUserpw(i+" passwd");
			memberVO.setUsername(i+" username");
			memberVO.setEmail(i+" email");
			list.add(memberVO);
		}
		
		sqlMapperService.multiInsert(list);
		
		return "multiInsert";
	}
	
	@RequestMapping("/multiInsertAI")
	@ResponseBody
	public String multiInsertAI() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		for (int i = 0; i < 10; i++) {
			MemberVO memberVO = new MemberVO();
			memberVO.setUserid(i+"");
			memberVO.setUserpw(i+" passwd");
			memberVO.setUsername(i+" username");
			memberVO.setEmail(i+" email");
			list.add(memberVO);
		}
		
		list.forEach(item -> logger.info("multiInsertAI before insert:"+item.toString()));
		
		sqlMapperService.multiInsertAI(list);
		
		list.forEach(item -> logger.info("multiInsertAI after insert:"+item.toString()));
		
		return "multiInsertAI";
	}
	
	@RequestMapping("/selectKeyInsert")
	@ResponseBody
	public String selectKeyInsert() {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUserpw("selectKeyInsertPW");
		memberVO.setUsername("selectKeyInsertUSERNAME");
		memberVO.setEmail("selectKeyInsertEMAIL");
		
		sqlMapperService.selectKeyInsert(memberVO);
		
		return "selectKeyInsert";
	}
	
//	http://localhost:8080/b2c/sqlMapperSample/selectIF?userid=a
//	http://localhost:8080/b2c/sqlMapperSample/selectIF
	@RequestMapping("/selectIF")
	@ResponseBody
	public String selectIF(@RequestParam( value="userid",required=false) String userid) {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUserid(userid);
		
		List<MemberVO> list = sqlMapperService.selectIF(memberVO);
		
		list.forEach(item -> logger.info("selectIF print logger1:"+item.toString()));
		
		return "selectIF";
	}
	
//	http://localhost:8080/b2c/sqlMapperSample/selectChoose?userid=a
//	http://localhost:8080/b2c/sqlMapperSample/selectChoose
	@RequestMapping("/selectChoose")
	@ResponseBody
	public String selectChoose(@RequestParam( value="userid",required=false) String userid) {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUserid(userid);
		
		List<MemberVO> list = sqlMapperService.selectChoose(memberVO);
		
		list.forEach(item -> logger.info("selectChoose print logger1:"+item.toString()));
		
		return "selectChoose";
	}
	
	
	
//	http://localhost:8080/b2c/sqlMapperSample/selectWhere
//	http://localhost:8080/b2c/sqlMapperSample/selectWhere?userid=a
//	http://localhost:8080/b2c/sqlMapperSample/selectWhere?userid=a&userpw=b
//	http://localhost:8080/b2c/sqlMapperSample/selectWhere?userid=a&userpw=b&username=c
	@RequestMapping("/selectWhere")
	@ResponseBody
	public String selectWhere(@RequestParam( value="userid",required=false) String userid,
			@RequestParam( value="userpw",required=false) String userpw,
			@RequestParam( value="username",required=false) String username) {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUserid(userid);
		memberVO.setUserpw(userpw);
		memberVO.setUsername(username);
		
		List<MemberVO> list = sqlMapperService.selectWhere(memberVO);
		
		list.forEach(item -> logger.info("selectWhere print logger1:"+item.toString()));
		
		return "selectWhere";
	}
	
//	http://localhost:8080/b2c/sqlMapperSample/selectTrim
//	http://localhost:8080/b2c/sqlMapperSample/selectTrim?userid=a
//	http://localhost:8080/b2c/sqlMapperSample/selectTrim?userid=a&userpw=b
//	http://localhost:8080/b2c/sqlMapperSample/selectTrim?userpw=b
//	http://localhost:8080/b2c/sqlMapperSample/selectTrim?userid=a&userpw=b&username=c
	@RequestMapping("/selectTrim")
	@ResponseBody
	public String selectTrim(@RequestParam( value="userid",required=false) String userid,
			@RequestParam( value="userpw",required=false) String userpw,
			@RequestParam( value="username",required=false) String username) {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUserid(userid);
		memberVO.setUserpw(userpw);
		memberVO.setUsername(username);
		
		List<MemberVO> list = sqlMapperService.selectTrim(memberVO);
		
		list.forEach(item -> logger.info("selectTrim print logger1:"+item.toString()));
		
		return "selectTrim";
	}
	
//	http://localhost:8080/b2c/sqlMapperSample/updateIfNecessary
//	http://localhost:8080/b2c/sqlMapperSample/updateIfNecessary?userid=a
//	http://localhost:8080/b2c/sqlMapperSample/updateIfNecessary?userid=a&userpw=b
//	http://localhost:8080/b2c/sqlMapperSample/updateIfNecessary?userpw=b
//	http://localhost:8080/b2c/sqlMapperSample/updateIfNecessary?userid=a&userpw=b&username=c
	@RequestMapping("/updateIfNecessary")
	@ResponseBody
	public String updateIfNecessary(@RequestParam( value="userid",required=false) String userid,
			@RequestParam( value="userpw",required=false) String userpw,
			@RequestParam( value="username",required=false) String username) {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUserid(userid);
		memberVO.setUserpw(userpw);
		memberVO.setUsername(username);
		
		int result  = sqlMapperService.updateIfNecessary(memberVO);
		
		
		return "updateIfNecessary";
	}
	
//	http://localhost:8080/b2c/sqlMapperSample/selectForEach?arrList=a,b,c,d,e,f,g,h,i,j,k,l,m,n
	@RequestMapping("/selectForEach")
	@ResponseBody
	public String selectForEach(@RequestParam( value="arrList",required=false) List<String> arrList) {
		
		
		List<MemberVO> list = sqlMapperService.selectForeach(arrList);
		
		list.forEach(item -> logger.info("selectForEach print logger1:"+item.toString()));
		
		return "selectForEach";
	}
}
