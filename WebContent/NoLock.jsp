<%@ page import="Security.*" %>
<%@ page import="domain.*" %>
<%@ page import="service.*" %>
<%@ page import="datasource.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Resource conflict</title>

</head>
<body>
<%

%>
<h1 align="center">Resource conflict</h1>

<h4>Hi ${username}, the resource you tried to access is being edited by another user.</h4>

<h4>You won't be able to access or delete it until the user has finished editing</h4>


<form action="Logout" method="post">
	<input type="submit" value="Logout"/>
</form>
 
</body>
</html>