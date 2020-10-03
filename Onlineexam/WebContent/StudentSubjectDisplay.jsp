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

String heading =  "Student Portal";

response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
if(session.getAttribute("username") == null || !session.getAttribute("usertype").equals("S")){
	response.sendRedirect("Login.jsp");
}

UserDataMapper um = new UserDataMapper();
String student_id = (String)session.getAttribute("userid");
Student student_obj = um.loadFullStudent(student_id);
%>
<h1> <%= heading %></h1>
<h3> Welcome, <%= session.getAttribute("username") %></h3>
<br/>
<h3>Subjects</h3>
<table>
<tr>
<th>subjectId</th>
<th>Name</th>
</tr>

<%
for (Subject subject : um.loadSubjectByStudent(student_obj)) {
	/* System.out.println(subject.getName()); */
		 %>
		 
		
<tr>
<td><%= subject.getCode() %></td>
<td><%= subject.getName() %></td>
</tr>

		        
    
 
<%
 		  } // for loop for multiple choise question
	%>

</table>

<br/>
<br/>
<form action="Logout" method="post">
	<input type="submit" value="Logout"/>
</form>



<br/>


</body>
</html> 