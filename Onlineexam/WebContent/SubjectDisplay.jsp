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
if(session.getAttribute("username") == null){
	response.sendRedirect("Login.jsp");
}

String heading = "";

if (session.getAttribute("usertype").equals("S")){
	heading = "Student Portal";
}else{
	heading = "Teacher Portal";
}

%>

<h1> <%= heading %></h1>
<h3> Welcome, <%= session.getAttribute("username") %></h3>
<br/>
<h3>Subjects</h3>


<form action="Logout" method="post">
	<input type="submit" value="Logout"/>
</form>



<br/>


</body>
</html> 