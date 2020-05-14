<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>LogIn here</title>
		<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
		<link rel="stylesheet" href="Css/PageStyle.css">
		<script language="JavaScript" type="text/javascript">
			function validateLogInInfo() {
				var adminName = document.credentialCheckContainer.adminName.value ;
				var password = document.credentialCheckContainer.password.value;
				
				if (adminName == '' || password == '') {
					 document.getElementById("credentialValidation").style = 'visibility: visible;';
					 event.preventDefault();
				 } else {
					 document.getElementById("credentialValidation").style = 'visibility: hidden;'; 
				 }
			}
		</script>
	</head>
	<body class="body" >
		<h2 align="center">Library Management System</h2>
		<h3 align="center">LogIn Page</h3>
		<form name="credentialCheckContainer" method="post" action="LogInServlet">
		   	<table align="center" class="table">
			  <tr class="tr">
			    <td class="td">
			    	<label class="label">UserName</label>
			    </td>
			    <td class="td">
			    	<input name="adminName" type="text" class="textbox" >
			    </td>
			  </tr>
			  <tr class="tr">
			    <td class="td">
			    	<label class="label">Password</label>
			    </td>
			    <td class="td">
			    	<input type="password" name="password" class="textbox" >
			    </td>
			  </tr>
			  <tr class="tr">
			  	<td class="td"></td>
			  	<td class="td">
			  		<input type="submit" onclick="javascript:validateLogInInfo()" class="button" value="GO">
			  	</td>
			  </tr>
			</table>
			<table id="credentialValidation" align="center" class="table" style="visibility: hidden;" >
				<tr class="tr">
				  	<td class="td">
				  		<label style="font-family: 'Cambria'; font-size: 12px; color: red;" >**Please enter both the credentials.</label>
				  	</td>
			  </tr>
			</table>
		</form>
	</body>
</html>