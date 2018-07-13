<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"  %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<c:out value="${pageContext.session.id}" /> 
<a href="${pageContext.request.contextPath}/account/logout">로그아웃</a>
<a href="${pageContext.request.contextPath}/account/otherLink">otherLink</a>
<a href="${pageContext.request.contextPath}/account/contextInfo">contextInfo</a>
</body>
</html>
