<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ page isErrorPage="true" %>   
		<link rel="stylesheet" href="Css/PageStyle.css">
		<script language="JavaScript" type="text/javascript">
			function pageLoad() 
			{
			    window.history.forward();
			}
			
		    setTimeout("pageLoad()", 0);
		    
			window.onload = pageLoad;
			
			function logInAgain(){
				window.open("http://localhost:8081/LibraryManagementSystem_Diptendu_Kundu/LogIn.jsp");
			}
		</script>
		<title>Exception Occurred.</title>
	</head>
	<body class="body" >
		<h2 align="center" >Sorry!! Something Went Very Unusual.</h2>
		<h4 align="center" ></h4>
		<table class="table" align="center" >
			<tr>
				<td><label class="label" >The Problem is ::  <%= exception.getMessage() %>  ::</label></td>
			</tr>
			<tr align="center" >
				<td><input type="button" style="width: 150px;" class="button" onclick="javascript:logInAgain()" value="LogIn Again" ></td>
			</tr>
		</table>
	</body>
</html>