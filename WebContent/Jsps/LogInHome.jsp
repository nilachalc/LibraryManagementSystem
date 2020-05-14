<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>LogIn Home</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<link rel="stylesheet" href="../Css/PageStyle.css">
		<script language="JavaScript" type="text/javascript">
			function pageLoad() 
			{
				var isUserCreatedSuccessfully = document.logInHomeContainer.userCreationCheck.value;
				var isUserDeletedSuccessfully = document.logInHomeContainer.userDeletionCheck.value;
				var isUserUpdatedSuccessfully = document.logInHomeContainer.userUpdationCheck.value;
				var isBookBulkUploadedSuccessfully = document.logInHomeContainer.bookBulkUploadCheck.value;
				
			    if (isUserCreatedSuccessfully == "true") {
			    	document.getElementById("successMessageCreation").style = 'visibility: visible; color: ##003300; font-style: italic; font-weight: bold; text-align: center;';
			    }
			    
			    if (isUserDeletedSuccessfully == "true") {
			    	document.getElementById("successMessageDeletion").style = 'visibility: visible; color: ##003300; font-style: italic; font-weight: bold; text-align: center;';
			    }
			    
			    if (isUserUpdatedSuccessfully == "true") {
			    	document.getElementById("successMessageUpdation").style = 'visibility: visible; color: ##003300; font-style: italic; font-weight: bold; text-align: center;';
			    }
			    
			    if (isBookBulkUploadedSuccessfully == "true") {
			    	document.getElementById("successMessageBookBulkUpload").style = 'visibility: visible; color: ##003300; font-style: italic; font-weight: bold; text-align: center;';
			    }
			    window.history.forward();
			}
		    setTimeout("pageLoad()", 0);
			function submitForm(navigation)
			{
			  	document.logInHomeContainer.userSelection.value = navigation ;
			  	document.logInHomeContainer.submit() ;
			}
			window.onload = pageLoad;
		</script>
		
		<%@page import="com.data.bean.AdminBean"%>
		<%
			if (request.getSession(true).getAttribute("loggedOnUser") == null) {
				response.sendRedirect("../LogIn.jsp");
			}
		%>					
	</head>
	<body class="body">
		<table align="right" >
			<tr>
				<td>
					Welcome <label style="font-weight: bolder;" >${loggedOnUser.adminName}</label>
				</td>
			</tr>
		</table>
		<p><p>
		<table align="center">
			<tr>
				<td>
					<h4><label id="successMessageCreation" style="visibility: hidden;" >The User has been added successfully. Happy Managing!!!</label></h4>
					<h4><label id="successMessageDeletion" style="visibility: hidden;" >The User has been deleted successfully. Happy Managing!!!</label></h4>
					<h4><label id="successMessageUpdation" style="visibility: hidden;" >The User has been updated successfully. Happy Managing!!!</label></h4>
				</td>
			</tr>
		</table>
		<h1 align="center" >Select one from the below operations</h1>
		<form name="logInHomeContainer" method="post" action="../LogInHomeServlet">
			<input type="hidden" name="userSelection">
			<table align="center" class="linktable">
				<tr class="linktr" >
					<td>
						<table align="center" class="linktable">
							<thead>
								<tr>
									<th class="linkheader">Access Users</th>
								</tr>
							</thead>
							<tr class="linktr" >
								<td>
									<a href="javascript:submitForm('createUser')" class="link" >Add User</a>
								</td>
							</tr>
							<tr class="linktr">
								<td>
									<a href="javascript:submitForm('deleteUser')" class="link" >Delete User</a>
								</td>
							</tr>
							<tr class="linktr">
								<td>
									<a href="javascript:submitForm('viewUser')" class="link" >View Users</a>
								</td>
							</tr>
							<tr class="linktr">
								<td>
									<a href="javascript:submitForm('updateUser')" class="link" >Update Users</a>
								</td>
							</tr>
						</table>
					</td>
					<td colspan="3">
						<table align="center" class="linktable">
							<tr>
								<th class="linkheader" >View Books</th>
							</tr>
							<tr class="linktr" >
								<td>
									<a href="javascript:submitForm('viewUser')" class="link" >View Issued Books</a>
								</td>
							</tr>
							<tr class="linktr" >
								<td>
									<a href="javascript:submitForm('viewUser')" class="link" >View Available Books</a>
								</td>			   	
							</tr>
							<tr class="linktr" >
								<td>
									<a href="javascript:submitForm('viewUser')" class="link" >View Books For A User</a>
								</td>
							</tr>
						</table>
					</td>
					<td colspan="3">
						<table align="center" class="linktable">
							<thead>
								<tr>
									<th class="linkheader">Access Books</th>
								</tr>
							</thead>
							<tr class="linktr" >
								<td>
									<a href="javascript:submitForm('viewUser')" class="link" >Add Book</a>
								</td>
							</tr>
							<tr class="linktr" >
								<td>
									<a href="javascript:submitForm('viewUser')" class="link" >Delete Book</a>
								</td>
							</tr>
							<tr class="linktr" >
								<td>
									<a href="javascript:submitForm('viewUser')" class="link" >Update Book</a>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<table align="center" class="linktable">
							<thead>
								<tr>
									<th class="linkheader">Actions</th>
								</tr>
							</thead>
							<tr class="linktr" >
								<td colspan="3" >
									<a href="javascript:submitForm('createUser')" class="link" >Issue Book</a>
								</td>
							</tr>
							<tr class="linktr" >
								<td colspan="3" >
									<a href="javascript:submitForm('bookBulkUpload')" class="link" >Book Bulk Upload</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table align="center" >
				<tr>
					<td>
						<input align="middle" type="submit" class="button" value="LogOut">
					</td>
				</tr>
			</table>
			<table align="center">
			<tr>
				<td>
					<h4><label id="successMessageBookBulkUpload" style="visibility: hidden;" >Total <c:out value="${ NoOfBookUploaded }"></c:out> Book/Books have been added successfully. Happy Managing!!!</label></h4>
				</td>
			</tr>
		</table>
			<%
			  	Boolean isUserCreated = (Boolean)request.getServletContext().getAttribute("userCreated");
		  		request.getServletContext().setAttribute("userCreated", new Boolean(false));
		  		
		  		Boolean isUserDeleted = (Boolean)request.getServletContext().getAttribute("userDeleted");
		  		request.getServletContext().setAttribute("userDeleted", new Boolean(false));
		  		
		  		Boolean isUserUpdated = (Boolean)request.getServletContext().getAttribute("userUpdated");
		  		request.getServletContext().setAttribute("userUpdated", new Boolean(false));
		  		
		  		Boolean isBookBulkUploaded = (Boolean)request.getServletContext().getAttribute("bookBulkUploaded");
		  		request.getServletContext().setAttribute("bookBulkUploaded", new Boolean(false));
			%>
			<input type="hidden" name="userCreationCheck" value="<%=isUserCreated%>">
			<input type="hidden" name="userDeletionCheck" value="<%=isUserDeleted%>">
			<input type="hidden" name="userUpdationCheck" value="<%=isUserUpdated%>">
			<input type="hidden" name="bookBulkUploadCheck" value="<%=isBookBulkUploaded%>">
		</form>
	</body>
</html>