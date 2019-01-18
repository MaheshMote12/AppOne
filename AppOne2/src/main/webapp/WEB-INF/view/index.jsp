<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>  Login Form</title>
</head>
<body>

<spring:url value="/login" var="login"/>

<form:form name="submitForm" method="POST" action="${login}" >
		<div align="center">
			<table>
				<tr>
					<td>User Name</td>
					<td><input type="text" name="userName" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>${invalidCredentials}</td>
					<td><input type="submit" value="Submit" /></td>
				</tr>
			</table>
			
			<c:if test="${error}">
			
			<div style="color: red">Username and password invalid</div>
		</c:if>
		<c:if test="${logout}">
			
			<div style="color: green">you are logged out</div>
		</c:if>
		</div>
	</form:form>	
</body>
</html>