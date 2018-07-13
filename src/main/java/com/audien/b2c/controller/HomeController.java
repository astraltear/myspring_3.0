package com.audien.b2c.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audien.b2c.domain.FormVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	DataSource masterDataSource;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		try {
			Connection connection = masterDataSource.getConnection();
			Statement stmt = connection.createStatement();
			String sql = "select * from tbl_member";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				logger.info(rs.getString(1)+"");
			}
			
			stmt.close();
			rs.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "home";
	}
	
	@RequestMapping("/callException")
	@ResponseBody
	public String callException(@RequestParam( value="param1", required=false) int param1, @RequestParam(value="param2", required=false) int param2) {
		return Integer.toString(param1/param1);
	}
	
	
	/*
	 http://localhost:8080/b2c/stringCollection?values=1&values=2&values=3&values=4&values=5
	 Converted collection [1, 2, 3, 4, 5]
	 
	 http://localhost:8080/b2c/stringCollection?values=q,w,ew,re,rw,sd,te,twe,rwe,rwer
	 Converted collection [q, w, ew, re, rw, sd, te, twe, rwe, rwer]
	 */
	@RequestMapping("/stringCollection")
	@ResponseBody
	public String formatConvert(@RequestParam Collection<String> values) {
		return "Converted collection " + values;
	}
	
	@RequestMapping("/stringList")
	@ResponseBody
	public String formatConvert(@RequestParam List<String> values) {
		return "Converted List " + values;
	}
	
//	http://localhost:8080/b2c/validate?name=abc&age=10 OK
//	http://localhost:8080/b2c/validate?name=abc
	@RequestMapping("/validate")
	@ResponseBody
	public String validate(@Valid FormVO formVO, BindingResult result) {
		
		if (result.hasErrors()) {
			return "Object has validation errors";
		} else {
			return "No errors";	
		}
		
	}

}
