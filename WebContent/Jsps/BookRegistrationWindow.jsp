<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
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
		<script>
			function updateValue() {
	            var dateInputValue = document.getElementById('dateInput').value;
	            document.getElementById('availabilityDateId').value = dateInputValue;
	        } 
    	</script>
		<%
			if (request.getSession(true).getAttribute("loggedOnUser") == null) {
				response.sendRedirect("../LogIn.jsp");
			}
		%>
	</head>
	<body>
		<table class="table">
			  <tr>
			    <th colspan="3" >Please Enter The Book Details Below:</th>
			  </tr>
			  <tr>
			    <td>
			    	<label class="label">Book Name</label>
			    </td>
			    <td>
			    	<input name="bookName" type="text" class="textbox" >
			    </td>
			  </tr>
			  <tr>
			    <td>
			    	<label class="label">Author Name</label>
			    </td>
			    <td>
			    	<input name="authorName" type="text" class="textbox" >
			    </td>
			  </tr>
			  <tr>
			    <td>
			    	<label class="label">Availability Date</label>
			    </td>
			    <td>
				    <jsp:include page="../Html/DatePicker.html"></jsp:include>
			    	<input name="availabilityDate" id="availabilityDateId" type= "hidden" class="textbox">
			    </td>
			  </tr>
			  <tr>
			    <td>
			    	<label class="label">Genre</label>
			    </td>
			    <td>
		    		<select name="genreDropDownValues">
				  		<option value="-1" selected="selected">--Select Genre--</option>
					    <c:forEach items="${genreDropDownValues}" var="genreDropDownValue">
					        <option value="${genreDropDownValue.genreId}">${genreDropDownValue.genreName}</option>
					    </c:forEach>
					</select>
			    </td>
			  </tr>
		</table>
	</body>
</html>