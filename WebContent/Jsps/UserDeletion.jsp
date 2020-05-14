<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Delete a User</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<link rel="stylesheet" href="../Css/PageStyle.css">
		<script type="text/javascript" src="../JS/UserValidation.js" ></script>
		<script language="JavaScript" type="text/javascript">
			function pageLoad() 
			{
				var userDeletionImpossible = document.userDeletionContainer.userDeletionPossibilityCheck.value;
				
			    if (userDeletionImpossible == "true") {
			    	document.getElementById("messageDeletionFailure").style = 'visibility: visible;';
			    }
			    window.history.forward();
			}
			
		    setTimeout("pageLoad()", 0);
		    
			function submitFormToCancel()
			{
			  	document.userDeletionContainer.cancelOperation.value = 'goBackHome' ;
			}
			window.onload = pageLoad;
		</script>
		<style>
			body {
				background-color: #e6f2ff;
			}
			table {
			  font-family: arial, sans-serif;
			  border-collapse: collapse;
			  width: 100%;
			}
			
			td {
			  border: 1px solid #dddddd;
			  text-align: left;
			  padding: 8px;
			}
			
			th {
			  background-color: #0099cc;
			  border: 1px solid #dddddd;
			  text-align: left;
			  padding: 8px;
			}
			
			tr:nth-child(even) {
			  background-color: #dddddd;
			}
		</style>
		<%
			if (request.getSession(true).getAttribute("loggedOnUser") == null) {
				response.sendRedirect("../LogIn.jsp");
			}
		%>
	</head>
	<body class="body" >
		<form id="UserDeletion" name="userDeletionContainer" action="../UserDeletionServlet" method="post" >
			<table class="table">
				<tr>
				  <th colspan="3" >Please Select The User To Be Deleted From The Below List:</th>
				</tr>
				<tr>
				  	<td>
				  		<label class="label">Users</label>
				  	</td>
				  	<td>
					  	<select name="userDropDownValuesForDeletion">
					  		<option value="-1" selected="selected">--Select User--</option>
						    <c:forEach items="${userDropDownValuesForDeletion}" var="userDropDownValue">
						        <option value="${userDropDownValue.userId}">${userDropDownValue.userFullName}</option>
						    </c:forEach>
						</select>
					</td>
				</tr>
			</table>
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
											cursor: pointer;" onclick="javascript:validateDeletion()" value="Delete">
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
				<p><p>
				<table id="messageDeletionFailure" style="visibility: hidden;" align="center">
					<tr>
						<td>
							<h4><label style="color: ##003300; font-style: italic; font-weight: bold; text-align: center;" >This User has at least 1 Issued Book with Him/Her !!!</label></h4>
						</td>
					</tr>
				</table>
				<input type="hidden" name="cancelOperation" >
				<%
			  		Boolean isUserDeletionImpossible = (Boolean)request.getServletContext().getAttribute("userDeletionImpossible");
			  		request.getServletContext().setAttribute("userDeletionImpossible", new Boolean(false));
				%>
				<input type="hidden" name="userDeletionPossibilityCheck" value="<%=isUserDeletionImpossible%>">
		</form>
		<table>
			<tr>
				<td id="userDropDownValidationCheck" style="visibility: hidden;">
					<label style="font-weight: bold; color: red;" >** Please select a user.</label>
				</td>
			</tr>
		</table>
	</body>
</html>