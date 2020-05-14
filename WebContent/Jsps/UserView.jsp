<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>View a User</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<link rel="stylesheet" href="../Css/PageStyle.css">
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
		<form id="UserView" name="userViewContainer" action="../UserViewServlet" method="post" >
			<table class="table">
				<tr>
				  <th colspan="3" >:: All The User of This Library are In The List Below ::</th>
				</tr>
				<tr>
				  	<td>
						<table class="table">
							<tr style="background-color: #bfff00;">
								<td><label class="label" >User Name</label></td>
								<td><label class="label" >Address</label></td>
								<td><label class="label" >Mobile</label></td>
								<td><label class="label" >Email</label></td>
								<td><label class="label" >Age</label></td>
								<td><label class="label" >Gender</label></td>
							</tr>
							<c:forEach items="${userValuesForView}" var="userValue">
								<tr>
									<td><label>${userValue.userName}</label></td>
									<td><label>${userValue.address}</label></td>
									<td><label>${userValue.mobile}</label></td>
									<td><label>${userValue.email}</label></td>
									<td><label>${userValue.age}</label></td>
									<td><label>${userValue.gender}</label></td>
								</tr>		
							</c:forEach>
						</table>
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
											cursor: pointer;" value="Ok, Thanks">
						</td>
					</tr>
				</table>
				<p><p>
		</form>
	</body>
</html>