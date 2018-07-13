<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<ul>
		<li>post</li>
		<li>
			<form action='<c:url value="/hiddenMethodTest/httpmethodTest"/>' method="post">
				<input type="text" name="_method" value="post">
				<input type="submit" />
			</form>
		</li>
		<li>put</li>
		<li>
			<form action="<c:url value="/hiddenMethodTest/httpmethodTest"/>" method="post">
				<input type="text" name="_method" value="put"> 
				<input type="submit" />
			</form>
		</li>
		<li>delete</li>
		<li>

			<form action="<c:url value="/hiddenMethodTest/httpmethodTest"/>" method="post">
				<input type="text" name="_method" value="delete"> 
				<input type="submit" />
			</form>
		</li>
	</ul>
	
</body>
</html>