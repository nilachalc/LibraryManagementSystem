<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
		<head>
			<title>Create an Book</title>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<link rel="stylesheet" href="../Css/PageStyle.css">
			<script type="text/javascript" src="../JS/BookValidation.js" ></script>
			<script language="JavaScript" type="text/javascript">
				function submitFormToCancel()
				{
				  	document.bookRegistrationContainer.cancelOperation.value = 'goBackHome' ;
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
			<form id="BookRegistration" name="bookRegistrationContainer" action="../BookCreationServlet" method="post">
				<jsp:include page="BookRegistrationWindow.jsp"></jsp:include>
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
					<td id="bookNameCheck" style="visibility: hidden;">
						<label style="font-weight: bold; color: red;" >** Please enter a valid name for the book.</label>
					</td>
				</tr>
				<tr>
					<td id="autorNameCheck" style="visibility: hidden;">
						<label style="font-weight: bold; color: red;" >** Please enter a valid author name for the book.</label>
					</td>
				</tr>
				<tr>
					<td id="availabilityDateCheck" style="visibility: hidden;">
						<label style="font-weight: bold; color: red;" >** Please enter a valid availability date for the book.</label>
					</td>
				</tr>
				<tr>
					<td id="genreCheck" style="visibility: hidden;">
						<label style="font-weight: bold; color: red;" >** Select genre for the book.</label>
					</td>
				</tr>
			</table> 
		</body>
	</html>