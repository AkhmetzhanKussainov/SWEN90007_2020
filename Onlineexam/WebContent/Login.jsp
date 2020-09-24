<%@ page import="domain.*" %>
<%@ page import="datasource.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detail View</title>

<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(odd) {
  background-color: #dddddd;
}
</style>

</head>
<body>
<%

response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

if(session.getAttribute("username") != null){
	response.sendRedirect("SubjectDisplay.jsp");
}

%>

<h1>Login</h1>
<br/>

<form action="Login" method="post">

<label>username</label>
<input  type="text" name="username"/>
<br/>
<br/>
<label>password</label>
<input type="password" name="password"/>
<br/>
<br/>
<input type="submit" value="login"/>

</form>


</body>
</html> 