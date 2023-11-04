<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Register</title>
</head>
<body>
	<form:form action="saveUser" modelAttribute="user">
    Name     : <form:input path="userName" />
		<br>
		<br>
    Email    : <form:input path="userEmail" />
		<br>
		<br>
    Password : <form:input path="userPassword" />
		<br>
		<br>
		<form:button>Register</form:button>
	</form:form>
</body>
</html>