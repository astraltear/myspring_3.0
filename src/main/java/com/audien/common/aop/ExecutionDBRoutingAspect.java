package com.audien.common.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.audien.common.db.ContextHolder;
import com.audien.common.db.DataSourceType;
import com.audien.common.db.ReadOnlyConnection;

@Aspect
@Component
@Order(value=1)
public class ExecutionDBRoutingAspect {
	private static final Logger logger = LoggerFactory.getLogger(ExecutionDBRoutingAspect.class);
	
	
	  @Around("execution(* com.audien..*Service.*(..))")
	  public Object doServiceProfiling(ProceedingJoinPoint joinPoint) throws Throwable {
	  
	    logger.info("@ExecutionDBRoutingAspect 시작");
	    
	    //Annotation  method
	    final String methodName = joinPoint.getSignature().getName();
	    final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
	    Method method = methodSignature.getMethod();
	    if(method.getDeclaringClass().isInterface()){
	      method = joinPoint.getTarget().getClass().getDeclaredMethod(methodName, method.getParameterTypes());
	    }
	    
	    //Annotation
	    ReadOnlyConnection dataSource = (ReadOnlyConnection) method.getAnnotation(ReadOnlyConnection.class);
	    if(dataSource != null){
	      ContextHolder.setDataSourceType(dataSource.value	());
	    }else{
	   /* 
	    //get*, select*  를하면  MASTER
	    if(!(method.getName().startsWith("get") || method.getName().startsWith("select"))){
	      ContextHolder.setDataSourceType(DataSourceType.MASTER);
	    }*/
	    	ContextHolder.setDataSourceType(DataSourceType.MASTER);
	  }
	  logger.info("DataSource ===> " + ContextHolder.getDataSourceType());
	  
	  Object returnValue = joinPoint.proceed();
	  ContextHolder.clearDataSourceType();
	  
	  logger.info("@ExecutionDBRoutingAspect 종료");
	  
	  return returnValue;
	  
	  }
}
