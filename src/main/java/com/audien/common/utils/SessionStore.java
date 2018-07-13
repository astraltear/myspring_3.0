package com.audien.common.utils;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionStore {
	private static SessionStore sessionStore = null;
	public static ConcurrentHashMap loginUsers = new ConcurrentHashMap();
	
	private static final Logger logger = LoggerFactory.getLogger(SessionStore.class);

	
	public static synchronized SessionStore getInstance() {
		if (sessionStore == null) {
			sessionStore = new SessionStore();
		}
		return sessionStore;
	}

	public void setSession(HttpSession session, String userId) {
		session.setAttribute("userId", userId);
		
		if (loginUsers.containsValue(userId) ) {
			Enumeration e = loginUsers.keys();
			String key="";
			
			while (e.hasMoreElements()) {
				key = (String) e.nextElement();
				
				if (userId.equals(loginUsers.get(key))) {
					logger.info("setSession::duplication key occur remove: key["+key+"]userId["+userId+"]mapUserId["+loginUsers.get(key)+"]");
					loginUsers.remove(key);
				}
			}
		}
		
		loginUsers.put(session.getId(), userId);
		
		
		printLoginUsers("setSession");
	}

	public boolean isEqualSession( String sessionId,String userId) {
		printLoginUsers("isLogin");
		Enumeration e = loginUsers.keys();
		String key="";
		boolean isEqual=false;
		
		while (e.hasMoreElements()) {
			key = (String) e.nextElement();
			logger.info("isEqualSession:loginUsers info:key["+key+"]sessionId["+sessionId+"]userId["+userId+"]mapUserId["+loginUsers.get(key)+"]");
			
			if (userId.equals(loginUsers.get(key)) && sessionId.equals(key)   ) {
				logger.info("isEqualSession:동일한 세션정보");
				isEqual=true;
			} else {
				logger.info("isEqualSession:상이한 세션정보");
				isEqual=false;
			}
		}
		return isEqual;
	}
	
	public void removeKey(String sessionId) {
		loginUsers.remove(sessionId);
	}
	
	public void printLoginUsers(String methodName) {
		Enumeration e = loginUsers.keys();
		String key="";
		
		logger.info("SessionStore:printLoginUsers "+methodName+" start---------------");
		while (e.hasMoreElements()) {
			key = (String) e.nextElement();
			logger.info("key:"+key);
			logger.info("value:"+loginUsers.get(key));
		}
		logger.info("SessionStore:printLoginUsers "+methodName+" end---------------");
	}
	
}
