<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Upload Books in Bulk</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="../Css/PageStyle.css">
		<script type="text/javascript" src="../JS/BookValidation.js" ></script>
		<script language="JavaScript" type="text/javascript">
			function pageLoad() 
			{
			    window.history.forward();
			}
			
		    setTimeout("pageLoad()", 0);
		    
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
	<body>
      <form id="bookBulkUpload1" name="bookBulkUploadContainer1" action="../BookBulkUploadServlet" method="post" enctype = "multipart/form-data">
			<table class="table" >
				<tr>
				  <th colspan="2">Please Select A File To Upload:</th>
				</tr>
				<tr>
					<td>
				  		<input id="bookBulkUploadFileInput" type = "file" name = "file" size = "50" />
				  		<br>
				  		<input type="submit" style="width: 200px;
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
										cursor: pointer;" onclick="javascript:validateBookBulkUpload()" value="Upload & Add Books">
										<br>
						<label id="bookBulkUploadFileValidationCheck" style="visibility: hidden;" >**First upload the file to proceed.</label>
						<br>
				  		<label style="font-family: 'Cambria'; font-size: 14px" >Please make sure to have single book entry(SSV format) in a single line of the uploading file.</label>
				  		<label style="font-family: 'Cambria'; font-size: 14px" >Also please put the value in the order below::</label>
				  		<br>
				  		<label style="font-family: 'Cambria'; font-size: 14px" >Book Name;Author Name;Availability Date;GenreID Value</label>
				  		<br><br>
				  		<label style="font-family: 'Cambria'; font-size: 12px" >**For Genre ID Values, Please Contact Administrator</label>
				  		<br>
				  		<label style="font-family: 'Cambria'; font-size: 12px">**Maximum FIle size is 1 MB.</label>
				  		<label style="font-family: 'Cambria'; font-size: 12px">**Only .txt files are allowed.</label>
				  	</td>
				</tr>
			</table>
		</form>
		<form id="bookBulkUpload2" name="bookBulkUploadContainer2" action="../BookBulkUploadServlet" method="get">
			<table>
				<tr>
					<td colspan="2">
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
										cursor: pointer;" value="Cancel">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>