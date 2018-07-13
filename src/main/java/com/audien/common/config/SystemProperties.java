package com.audien.common.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemProperties {
	private static final Logger logger = LoggerFactory.getLogger(SystemProperties.class);

	private static PropertiesConfiguration config = null;
	private static String CONF_FILE = "system.properties";
	private static int CONF_RELOAD = 5000;
	
	static{
		try {
			// path 를 절대 경로로 주지 않았을 경우 다음 경로에서 찾게 됨
			//1. current directory
			//2. user home directory
			//3. classpath
			config = new PropertiesConfiguration(CONF_FILE);
			FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
			strategy.setRefreshDelay(CONF_RELOAD);
			config.setReloadingStrategy(strategy);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static boolean disabled(String job){
		boolean ret = true;
    	try {
    		ret= config.getBoolean(job);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    	}
    	
    	return ret;
    }
	
	public static String getId(String job){
		String str = "";
    	try {
    		str= config.getString(job);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    	}
    	
    	return str;
    }
}
