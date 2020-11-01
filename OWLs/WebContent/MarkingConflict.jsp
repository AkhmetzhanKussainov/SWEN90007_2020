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
System.out.println("Request to access Teacher");
		
request.setAttribute("action", "Access Teacher");

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
<title>Marking conflict</title>

</head>
<body>
<%

String shortquestions = request.getParameter("shortconflicts");
String multiplequestions = request.getParameter("multipleconflicts");

%>
<h1 align="center">There was a marking conflict</h1>

<h4>Hi ${username}, your marking was not fully saved</h4>

<h4>There were issues with short questions: <%= shortquestions %></h4>

<h4> and multiple choice questions: <%= multiplequestions %></h4>
<form action="Logout" method="post">
	<input type="submit" value="Logout"/>
</form>
 
</body>
</html>