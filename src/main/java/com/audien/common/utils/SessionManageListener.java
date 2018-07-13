package com.audien.common.utils;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SessionManageListener implements HttpSessionListener {
	
	private static int totalActiveSessions;
	
	
	private static final Logger logger = LoggerFactory.getLogger(SessionManageListener.class);

	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		totalActiveSessions++;
		
		logger.info("sessionCreated:"+totalActiveSessions);	
		logger.info("sessionCreated sessionId:"+event.getSession().getId());	
		/*
		HttpSession    session     = event.getSession();
        ServletContext context     = session.getServletContext();
        Enumeration<String> obj =  context.getAttributeNames();
        
        System.out.println("---------- context getAttributeNames start--------------");
        while (obj.hasMoreElements()) {
			String string = (String) obj.nextElement();
			System.out.println(string);
        }
        System.out.println("---------- context getAttributeNames end--------------");
           */

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		 totalActiveSessions--;
		 logger.info("sessionDestroyed:"+totalActiveSessions);	
		 logger.info("sessionDestroyed sessionId:"+event.getSession().getId());
		 logger.info("sessionDestroyed getAttribute:"+event.getSession().getAttribute("userId"));	
		 
		 SessionStore sessionStore = SessionStore.getInstance();
		sessionStore.removeKey(event.getSession().getId());
		 

	}

}
