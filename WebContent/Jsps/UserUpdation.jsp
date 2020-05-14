<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Update a User</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<link rel="stylesheet" href="../Css/PageStyle.css">
		<script type="text/javascript" src="../JS/UserValidation.js" ></script>
		<script language="JavaScript" type="text/javascript">
			function pageLoad() 
			{
				var userUpdationShowData = document.userUpdationContainer2.userUpdationShowDataEnabler.value;
				
			    if (userUpdationShowData == "true") {
			    	document.getElementById("userDataShow").style = 'visibility: visible;';
			    }
			    window.history.forward();
			}
			
		    setTimeout("pageLoad()", 0);
		    
			function submitFormToUpdate()
			{
				var userValueAsString = [];
				
				if (!userDropDownValidationForUpdation()) {
					
					validateUpdate();
					
				  	userValueAsString.push(document.userUpdationContainer1.userFirstName.value);
				  	userValueAsString.push(document.userUpdationContainer1.userLastName.value);
				  	userValueAsString.push(document.userUpdationContainer1.userAddress.value);
				  	userValueAsString.push(document.userUpdationContainer1.userMobile.value);
				  	userValueAsString.push(document.userUpdationContainer1.userEmail.value);
				  	userValueAsString.push(document.userUpdationContainer1.userAge.value);
				  	userValueAsString.push(document.userUpdationContainer1.userGender.value);
				  	
				  	document.userUpdationContainer2.userUpdatedValue.value = userValueAsString.join(",");
				  	
				  	document.userUpdationContainer2.updateOperation.value = 'updateUserOperation' ;
				} else {
					document.getElementById("userDropDownValidationCheck").style = 'visibility: visible;';
					event.preventDefault();
				}
			}
			
			function submitFormToCancel()
			{
			  	document.userUpdationContainer2.cancelOperation.value = 'goBackHome' ;
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
		<form id="UserUpdation1" name="userUpdationContainer1" action="../UserUpdationServlet" method="get" >
			<table class="table">
				<tr>
				  <th colspan="3" >Please Select The User To Be Updated From The Below List:</th>
				</tr>
				<tr>
				  	<td>
				  		<label class="label">Users</label>
				  	</td>
				  	<td>
					  	<select name="userDropDownValuesForUpdation">
					  		<option value="-1" selected="selected">--Select User--</option>
						    <c:forEach items="${userDropDownValuesForUpdation}" var="userDropDownValueForUpdation">
						    	<c:choose>
							    	<c:when test = "${userValue.userId == userDropDownValueForUpdation.userId}">
						        		<option value="${userDropDownValueForUpdation.userId}" selected="selected" >${userDropDownValueForUpdation.userFullName}</option>
						        	</c:when>
							    	<c:otherwise>
							        	<option value="${userDropDownValueForUpdation.userId}">${userDropDownValueForUpdation.userFullName}</option>
							        </c:otherwise>
						        </c:choose>
						    </c:forEach>
						</select>
					</td>
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
										cursor: pointer;" onclick="javascript:validateGetUserDetailsBeforeUpdate()" value="Get Details">
					</td>
				</tr>
				<tr id="userDataShow" style="visibility: hidden; ">
					<td colspan="3" >
						<table>
							<tr style="background-color: #bfff00;">
								<td><label class="label" >First Name</label></td>
								<td><label class="label" >Last Name</label></td>
								<td><label class="label" >Address</label></td>
								<td><label class="label" >Mobile</label></td>
								<td><label class="label" >Email</label></td>
								<td><label class="label" >Age</label></td>
								<td><label class="label" >Gender</label></td>
							</tr>
							<tr>
								<td><input name="userFirstName" type="text" value="${userValue.firstName}"></td>
								<td><input name="userLastName" type="text" value="${userValue.lastName}"></td>
								<td><input name="userAddress" type="text" value="${userValue.address}"></td>
								<td><input name="userMobile" type="text" value="${userValue.mobile}"></td>
								<td><input name="userEmail" type="text" value="${userValue.email}"></td>
								<td><input name="userAge" type="text" value="${userValue.age}"></td>
								<td>
									<select name="userGender">
									    <c:forEach items="${genderListUpdation}" var="gender">
									    	<c:choose>
									        	<c:when test = "${userValue.gender == gender.key}">
									        		<option value="${gender.key}" selected="selected" >${gender.value}</option>
									        	</c:when>
									        	<c:otherwise>
									       			<option value="${gender.key}">${gender.value}</option>
									        	</c:otherwise>
										    </c:choose>
									    </c:forEach>
									</select>
								</td>
							</tr>		
						</table>						
					</td>
				</tr>
			</table>
		</form>
		<form id="UserUpdation2" name="userUpdationContainer2" action="../UserUpdationServlet" method="post" >
			<table>
				<tr>
					<td>
						<input id="updateButton" type="submit" style="width: 150px;
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
										cursor: pointer;" onclick="javascript:submitFormToUpdate()" value="Update">
						<input id="cancelButton" type="submit" style="width: 150px;
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
			<input type="hidden" name="updateOperation" >
			<%
		  		Boolean isUserUpdationReady = (Boolean)request.getServletContext().getAttribute("userUpdationReady");
		  		request.getServletContext().setAttribute("userUpdationReady", new Boolean(false));
			%>
			<input type="hidden" name="userUpdationShowDataEnabler" value="<%=isUserUpdationReady%>">
			<input type="hidden" name="userUpdatedValue">
		</form>
		<table id="tableValidationMessages">
			<tr>
				<td id="userDropDownValidationCheck" style="visibility: hidden;">
					<label style="font-weight: bold; color: red;" >** Please select a user and fetch Details for him/her.</label>
				</td>
			</tr>
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
		</table>
	</body>
</html>