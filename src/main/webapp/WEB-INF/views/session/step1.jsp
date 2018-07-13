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
		

		<form id="form" method="post" action="${pageContext.request.contextPath}/user/step2" >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			username<input name="username" value="" /><br>
			userpassword<input name="userpassword" value="" /><br>
			userage<input name="userage" value="" /><br>
			usersex<input name="usersex" value="" /><br>
			useraddr<input name="useraddr" value="" /><br>
			usertelno<input name="usertelno" value="" /><br>
			
			<button type="submit">Submit</button>
		</form>
</body>
</html>
