<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>Create an User</title>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<link rel="stylesheet" href="../Css/PageStyle.css">
			<script type="text/javascript" src="../JS/UserValidation.js" ></script>
			<script language="JavaScript" type="text/javascript">
				function submitFormToCancel()
				{
				  	document.userRegistrationContainer.cancelOperation.value = 'goBackHome' ;
				}
				function submitFormToAdd()
				{
					validateAddition();
				}
			</script>
			<%
				if (request.getSession(true).getAttribute("loggedOnUser") == null) {
					response.sendRedirect("../LogIn.jsp");
				}
			%>
		</head>
		<body>
			<form id="UserRegistration" name="userRegistrationContainer" action="../UserCreationServlet" method="post">
				<jsp:include page="UserRegistrationWindow.jsp"></jsp:include>
				<table>
					<tr>
						<td>
							<input type="submit" style="width: 150px;
											height: 40px;
											background-color: #4dff4d;
											border: none;
											color: black;
											padding: 15px 32px;
											text-align: center;
											text-decoration: blink;
											display: inline-block;
											font-size: 15px;
											margin: 4px 2px;
											cursor: pointer;" onclick="javascript:submitFormToAdd()" value="Add">
							<input type="submit" style="width: 150px;
											height: 40px;
											background-color: #4dff4d;
											border: none;
											color: black;
											padding: 15px 32px;
											text-align: center;
											text-decoration: blink;
											display: inline-block;
											font-size: 15px;
											margin: 4px 2px;
											cursor: pointer;" onclick="javascript:submitFormToCancel()" value="Cancel">
						</td>
					</tr>
				</table>
				<input type="hidden" name="cancelOperation" >
			</form>
			<table id="tableValidationMessages" >
				<tr>
					<td id="userFirstNameCheck" style="visibility: hidden;">
						<label style="font-weight: bold; color: red;" >** Please enter a valid first name for the user.</label>
					</td>
				</tr>
				<tr>
					<td id="userLastNameCheck" style="visibility: hidden;">
						<label style="font-weight: bold; color: red;" >** Please enter a valid last name for the user.</label>
					</td>
				</tr>
				<tr>
					<td id="userAddressCheck" style="visibility: hidden;">
						<label style="font-weight: bold; color: red;" >** Please enter a valid address for the user.</label>
					</td>
				</tr>
				<tr>
					<td id="userMobileCheck" style="visibility: hidden;">
						<label style="font-weight: bold; color: red;" >** Please enter a valid mobile number for the user.</label>
					</td>
				</tr>
				<tr>
					<td id="userEmailCheck" style="visibility: hidden;">
						<label style="font-weight: bold; color: red;" >** Please enter a valid email address for the user.</label>
					</td>
				</tr>
				<tr>
					<td id="userAgeCheck" style="visibility: hidden;">
						<label style="font-weight: bold; color: red;" >** Please enter a valid Age for the user. The user be at least 10 years old.</label>
					</td>
				</tr>
				<tr>
					<td id="userGenderCheck" style="visibility: hidden;">
						<label style="font-weight: bold; color: red;" >** Please enter a valid gender for the user.</label>
					</td>
				</tr>
			</table> 
		</body>
	</html>