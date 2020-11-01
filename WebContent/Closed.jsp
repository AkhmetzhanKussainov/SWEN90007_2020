<%@ page import="Security.*" %>
<%@ page import="domain.*" %>
<%@ page import="service.*" %>
<%@ page import="datasource.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>


<% 
//Read page Authorisation Code
System.out.println("Request to access Student");
		
request.setAttribute("action", "Access Student");

String alertMessage = "You lack authorisation to view this page";

//Check if authorised
if (AuthorizationEnforcer.checkAuthorization(request)) {

	System.out.println("Authorized");
	
}

else {
	
	request.setAttribute("correctPermission", false);
	request.setAttribute("alertPermission", alertMessage);
	System.out.println("You lack appropriate authorisation");
	request.getRequestDispatcher("Permissions.jsp").forward(request, response);
	
}
//End authorization code
%>


<head>
<meta charset="ISO-8859-1">
<title>Exam is closed</title>

</head>
<body>
<%

%>
<h1 align="center">The exam is closed</h1>

<h4>Hi ${username}, your exam was closed.</h4>

<h4>A scriptbook was submitted with every answer marked as "unanswered"</h4>

<h4>Contact Learning support to apply for special consideration if you believe this was in error.</h4>

<form action="Logout" method="post">
	<input type="submit" value="Logout"/>
</form>
 
</body>
</html>