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

<%-- Handle incorrect login details --%> 
<input id="correctLogin" type="hidden" value="${correctLogin}"/>
<script type="text/javascript">
    var correct = document.getElementById('correctLogin').value;
    if ((correct)==='false') {
    	alert('The username or password is incorrect');
    }
</script>

<%

response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

if(session.getAttribute("usertype") != null){
	if (session.getAttribute("usertype").equals("S")){
		response.sendRedirect("StudentSubjectDisplay.jsp");	
	}
	else if (session.getAttribute("usertype").equals("T")){
		response.sendRedirect("TeacherSubjectDisplay.jsp");	
	}
	else if (session.getAttribute("usertype").equals("A")){
		response.sendRedirect("Admin.jsp");	
	}
	else if (session.getAttribute("usertype").equals("H")){
		response.sendRedirect("Admin.jsp");	
	}
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