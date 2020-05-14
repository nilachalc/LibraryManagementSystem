<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>LogIn Failed</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="../Css/PageStyle.css">
		<script language="JavaScript" type="text/javascript">
			function submitForm()
			{
			  	document.failedLogInContainer.submit() ;
			}
		</script>
	</head>
	<body class="body" >
		<form id="logInFailed" name="failedLogInContainer" action="../LogInFailedServlet" method="post" >
			<h2 align="center" >It seems that you are not A Valid User.</h2>
			<h3 align="center" >Try<a href="javascript:submitForm()" > Again</a></h3>
		</form>
	</body>
</html>