package com.audien.b2c.controller;

import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.audien.b2c.service.EhCacheService;

@Controller
@RequestMapping("/ehcacheSample")
public class EhCacheController {
	
	private static final Logger logger = LoggerFactory.getLogger(EhCacheController.class);
	
	@Inject
	EhCacheService ehCacheService;
	
	@RequestMapping("/getNormal/{f_key}")
	@ResponseBody
	public String getNormal(@PathVariable String f_key ) {
//		https://joinup.ec.europa.eu/svn/openeprior/branches/V_1_0/Implementation/CentralCatalogueDataAccess/resources/ehcache.xml
		
		Map<String, Object> map =  ehCacheService.getNormalDataNonCaching(f_key);
		
		logger.info(" NON-CACHING {}", map.get("getRandom"));
		
		
		return "getNormal";
	}
	
	@RequestMapping("/getEhcache/{f_key}")
	@ResponseBody
	public String getEhcache(@PathVariable String f_key ) {
//		https://joinup.ec.europa.eu/svn/openeprior/branches/V_1_0/Implementation/CentralCatalogueDataAccess/resources/ehcache.xml
		
		
		Map<String, Object> map =  ehCacheService.getNormalDataCaching(f_key);
		logger.info(" CACHING {}", map.get("getRandom"));
		
		return "getEhcache";
	}
	
	@RequestMapping("/delEhcache/{f_key}")
	@ResponseBody
	public String delEhcache(@PathVariable String f_key ) {
//		https://joinup.ec.europa.eu/svn/openeprior/branches/V_1_0/Implementation/CentralCatalogueDataAccess/resources/ehcache.xml
		
		
		Map<String, Object> map =  ehCacheService.getNormalDataEraseCache(f_key);
		logger.info(" DELETE CACHING {}", map.get("getRandom"));
		
		return "delEhcache";
	}
	
	@RequestMapping("/getEhcacheDate")
	@ResponseBody
	public String getEhcacheDate() {
		return ehCacheService.getDateToString();
	}
	
	
	
}
