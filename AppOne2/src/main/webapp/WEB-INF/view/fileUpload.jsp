<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>upload file</title>
<script
			  src="https://code.jquery.com/jquery-3.3.1.min.js"
			  integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
			  crossorigin="anonymous"></script>
</head>
<body>

<%@include file="./fragments/header.jsp" %>

	<center> <form:form method="POST" action="uploadFile"
		enctype="multipart/form-data">

		<br>
		File to upload: 
		<input type="file" name="file">
		<br />

		<input accept=".xls,.xlsx" type="submit" value="Upload"> Press here to upload the file!
		
		<br>
		
		${invalidFile}
		
		${success}
		
		
		
		
	</form:form> 
	</center>
</body>
</html>