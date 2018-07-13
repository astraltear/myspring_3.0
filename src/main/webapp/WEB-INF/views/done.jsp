<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<c:if test="${not empty message}">
		<div id="message" class="success">${message}</div>
	</c:if>
</body>
</html>
