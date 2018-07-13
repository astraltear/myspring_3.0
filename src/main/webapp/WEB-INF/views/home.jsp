<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<script src="https://content.jwplatform.com/libraries/RZfT2JuW.js"></script>
<link rel="stylesheet" href="<c:url value="/webjars/bootstrap/4.0.0-alpha.4/dist/css/bootstrap.min.css" />">
<script src="<c:url value="/webjars/jquery/3.1.1/dist/jquery.min.js" />"></script>
<script src="<c:url value="/webjars/bootstrap/4.0.0-alpha.4/dist/js/bootstrap.min.js" />"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.2.61/jspdf.min.js"></script>
<script src="<c:url value="/resources/html2canvas.js" />"></script>
<script type="text/javascript">
$(function () {
	$("#pdfdown").click(function (){
		console.log("TEST CLICK:");
		html2canvas(document.getElementById("report"),{
			onrendered:function(canvas){
				var imgData = canvas.toDataURL("image/png");
				console.log("Report Image URL:"+imgData);
				var doc = new jsPDF('p','mm',[297,210]);
				doc.addImage(imgData, 'PNG', 10,10,190,95);
				doc.save('sample-file.pdf');
			}
		});
	});
});
</script>
</head>
<body>
<div id="report">
	<h1>Hello world!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<p>한글처리!!!!!</p>


	<form id="form" method="post" action="${pageContext.request.contextPath}/account/login">
		userId<input name="userId" value="" />
		<button type="submit">Submit</button>
	</form>


	<div class="container">
	  <h2>Button Styles</h2>
	  <button type="button" class="btn btn-default">Default</button>
	  <button type="button" class="btn btn-primary">Primary</button>
	  <button type="button" class="btn btn-success">Success</button>
	  <button type="button" class="btn btn-info">Info</button>
	  <button type="button" class="btn btn-warning">Warning</button>
	  <button type="button" class="btn btn-danger">Danger</button>
	  <button id="pdfdown" type="button" class="btn btn-link">PDF DOWN</button>
	</div>
</div>
	<br><br><br><br><br>

	<div id="player"></div>

	<script>
		jwplayer("player")
				.setup(
						{
							sources : [ {
								//				file : "http://maudien.nowcdn.co.kr/audien/ad_test/mp4:AD_7a0af773f8c5a4591713_01.mp4/playlist.m3u8"
								file : "http://maudien.nowcdn.co.kr/audien/ad_test/mp4:AD_7a0af773f8c5a4591713_01.mp4/playlist.m3u8"
							} ],
							height : 360,
							width : 640
						});
	</script>



</body>
</html>
