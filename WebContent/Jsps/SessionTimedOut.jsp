<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Session TimedOut</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="../Css/PageStyle.css">
		<script language="JavaScript" type="text/javascript">
			function submitForm()
			{
			  	document.sessionTimedOutContainer.submit() ;
			}
		</script>
	</head>
	<body class="body" >
		<form id="logInFailed" name="sessionTimedOutContainer" action="../SessionTimedOutServlet" method="post" >
			<h2 align="center" >Sorry!! The session has timed out.</h2>
			<h3 align="center" >click <a href="javascript:submitForm()" > here </a> to login Again.</h3>
		</form>
	</body>
</html>