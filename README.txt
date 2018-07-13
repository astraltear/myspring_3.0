ENV
	4.2.2.RELEASE
	JDK 1.8
	TOMCAT 8 
	
LIB
	AspectJ 
	AspectJ weaver
	aop
	spring jdbc
	mysql driver
	dbcp
	mybatis
	Transactions
	log4jdbc-log4j2
	jackson
	spring test
	commons codec
	commons lang
	junit
	Hibernate Validator
	Ehcache
	
## Aspect AOP
@Aspect 어노테이션을 이용해서 Aspect 클래스를 구현한다. 이때 Aspect 클래스는 Advice를 구현한 메서드와 Pointcut을 포함한다.
@Aspect
@Component
@Order(value=1)

 XML 설정에서 <aop:aspectj-autoproxy/>를 설정한다.

## JSP를 직접 호출
	controllers.xml 
	<mvc:view-controller path="/directjsp" view-name="directjsp"/>
	
## Exception 관리
	@ControllerAdvice
	AnnotationExceptionHandler
	
## 파리미터 Array
	http://localhost:8080/b2c/stringArr?values=q,w,ew,re,rw,sd,te,twe,rwe,rwer
	
	
## JSON 파라미터 인터렉션 호출
	JsonController.readWriteJson		문자열 JSON으로 리턴
	JsonController.responseEntitySample .json파일로 리턴
	JsonController.jsonPrint2 .json파일로 리턴

## BindingResult
	jsp form 태그 사용(http://www.mkyong.com/spring-mvc/spring-3-mvc-and-jsr303-valid-example/)
	FormController
		
	<beans:bean class="org.springframework.context.support.ResourceBundleMessageSource" id="messageSource">
		<beans:property name="basename" value="messages" />
	</beans:bean>
	
	messages.properties
		@Annotation Name.object.fieldname
		
## Valid
	http://springmvc.egloos.com/509029
	
## Spring EL Properties
	<util:properties id="config" location="classpath:/config/config.properties" />
	
	- xml
	<bean id="authorityLoader" class="...">
    	<property name="id" value="#{config['auth.id']}"/>
    	<property name="pw" value="#{config['auth.pw']}"/>
	</bean>
	
	- java
	@Value("#{config['auth.id']}") String id;
	@Value("#{config['auth.pw']}") String password;
	 
	logger.info("당신의 ID는 " + id + "입니다.");
	
	- jsp
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 
	<spring:eval expression="@config['auth.id']" />

## CSRF FILTER
	SessionController
		GET, HEAD, TRACE, OPTIONS 는 CSRF필터가 적용되지 않는다. 
		<!-- Security (used for CSRF protection only) -->
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-web</artifactId>
			<version>4.0.1.RELEASE</version>
		</dependency>
		
		<!-- CSRF Filter -->
		<bean id="csrfFilter" class="org.springframework.security.web.csrf.CsrfFilter">
			<constructor-arg>
				<bean class="org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository" />
			</constructor-arg>
		</bean>
		<bean id="requestDataValueProcessor" class="org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor" />
		
		
		<!--  CSRF Filter -->
		<filter>
			<filter-name>csrfFilter</filter-name>
			<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
		</filter>
		<filter-mapping>
			<filter-name>csrfFilter</filter-name>
			<url-pattern>/auth/*</url-pattern>
		</filter-mapping>

## HttpHiddenMethod
	HttpHiddenMethodController
		<!-- hidden method filter -->
		<filter>
			<filter-name>httpMethodFilter</filter-name>
			<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
		</filter>
		<filter-mapping>
			<filter-name>httpMethodFilter</filter-name>
			<url-pattern>/hiddenMethodTest/*</url-pattern>
		</filter-mapping>

## Filter > Interceptor > AOP
	설명: http://blog.naver.com/platinasnow/220035316135
		http://isstory83.tistory.com/90
		
## 로그인 인터셉터 처리 
	LoginInterceptor
	LoginInterceptorController
	
		preHandle : Controller 가 수행되기 전에 실행됩니다. 여기서는 이후 Controller를 수행할지 여부를 boolean 으로 return 하게 됩니다.
		postHandle : Controller 가 수행된후 View 를 호출하기 전 상태입니다.
		afterCompletion : View 작업까지 완료된 후 호출 됩니다. responseBody 를 이용할 경우 UI 에 이미 값을 전달후 해당 부분이 호출됩니다.
		
		<interceptors>
			<interceptor>
				<mapping path="/interceptorLogin/board_write" /> <!-- 특정페이지만 지정 -->
				<mvc:mapping path="/admin/**"/> <!-- 특정 URL 아래로 모두 지정 -->
				<beans:bean class="com.audien.common.interceptor.LoginInterceptor" />
			</interceptor>
			<!-- 추가로 여러개의 인터셉터 설정 가능 -->
		</interceptors>
		
		단 VO 객체는 serializable을 구현해야 한다. 
			
		  테스트 방법
		  호출 /interceptorLogin/board_write  
		  결과 /interceptorLogin/login 컨트롤러 페이지로 가는지확인
		  
		  호출 /interceptorLogin/setSession  
		  결과 /interceptorLogin/board_write 

		  
## HttpSessionListener 처리하기

** 세션 타임아웃 적용 우선순위
<session-config>
        <session-timeout>360</session-timeout>
</session-config>

1. 프로그램에 코딩된 session.setMaxInactiveInterval(int)
2. 각 웹 어플리케이션의 WEB-INF/web.xml
3. [tomcat설치디렉토리]/conf/web.xml

sessionCreated 세션이 생성될 때 ,sessionDestroyed 세션이 만료될때 호출된다(세션타임아웃 시에도 호출된다.)

web.xml에 아래와 같이 리스너를 등록한다.
<listener>
	<listener-class>com.audien.common.utils.SessionManageListener</listener-class>
</listener>


## Redis 연동하기
	com.audien.b2c.controller.RedisController
	jedis, spring-data-redis dependency add
	
## Ehcache

	<cache:annotation-driven cache-manager="cacheManager" />
	<bean id="cacheManager" class="org.springframework.cache.ehcache.EhCacheCacheManager">
		<property name="cacheManager" ref="ehcache"></property>
	</bean>
	<bean id="ehcache" class="org.springframework.cache.ehcache.EhCacheManagerFactoryBean">
		<property name="configLocation" value="classpath:/ehcache.xml"/>
		<property name="shared" value="true"/>
	</bean>
	
## CORS
	http://ooz.co.kr/232
	XMLHttpRequest cannot load http://www.ozit.co.kr. 
	No 'Access-Control-Allow-Origin' header is present on the requested resource.
	Origin 'http://abc.ozit.co.kr' is therefore not allowed access.