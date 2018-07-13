<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Home</title>
</head>
<body>

		model  <br>
		userid : ${ user.userid } <br>
		username : ${ user.username } <br>
		userpassword : ${ user.userpassword } <br>
		userage : ${ user.userage } <br>
		usersex : ${ user.usersex } <br>
		useraddr : ${ user.useraddr } <br>
		usertelno : ${ user.usertelno } <br><br>

		세션 정보 <br>
		userid : ${ sessionScope.user.userid } <br>
		username :${ sessionScope.user.username } <br>
		userpassword :${ sessionScope.user.userpassword } <br>
		userage :${ sessionScope.user.userage } <br>
		usersex :${ sessionScope.user.usersex } <br>
		useraddr :${ sessionScope.user.useraddr } <br>
		usertelno :${ sessionScope.user.usertelno } <br><br>



	<form id="form" method="post" action="<c:url value="/user/step4" />" >
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		${user }<br>
		<button type="submit">Submit</button>
	</form>
</body>
</html>
