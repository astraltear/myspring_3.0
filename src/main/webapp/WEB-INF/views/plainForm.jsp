<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
</head>
<body>
		<form id="form" method="post" action="${pageContext.request.contextPath}/form/plainFormSubmit" >
			<br>
		  	
		  	Name<input name="name" value="${formVo.name }" />
			<br>
			
			age<input name="age" value="${formVo.age }" />
			<br>
			
			<button type="submit">Submit</button>
		</form>
</body>
</html>
