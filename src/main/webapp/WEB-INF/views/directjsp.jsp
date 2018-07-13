<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
	
<script type="text/javascript">
	$(document).ready(function(){
		$("#putJson").click(function(){
			$.ajax({ 
				type: "POST", 
				url: "/b2c/mapping/readWriteJson", 
				data:"{\"name\":\"자기계발\",\"age\":\"10\"}",
				dataType: "text", 
				contentType: "application/json", 
				//beforeSend: function(req) { req.setRequestHeader("Accept", "application/x-www-form-urlencoded"); }, 
				success: function(text) {  $("#printJSON").text(text)  }, 
				error: function(xhr) { alert(xhr) }
				});	
			return false;
		});
	});
</script>
</head>
<body>
<h1>
	Direct JSP CALL!!  
</h1>
<input type="button" name="putJson" id="putJson"  value="JSON">
<div id="printJSON"></div>
</body>
</html>
