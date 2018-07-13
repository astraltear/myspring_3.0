<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Home</title>
</head>
<body>
		<form:form id="form" method="post" action="${pageContext.request.contextPath}/form/springFormSubmit" modelAttribute="formVo" >
			<form:errors path="*" cssClass="errorblock" element="div" />
			<br>
		  	
		  	Name<form:input path="name" /><form:errors path="name" cssClass="error" />
			<br>
			
			age<form:input path="age" /><form:errors path="age" cssClass="error" />
			<br>
			
			<button type="submit">Submit</button>
		</form:form>
</body>
</html>
