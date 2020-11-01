<%@ page import="Security.*" %>
<%@ page import="domain.*" %>
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
<th></th>
</tr>

<%
for (Subject subject : um.loadSubjectByStudent(student_obj)) {
	/* System.out.println(subject.getName()); */
		 %>
		 
		
<tr>
<td><%= subject.getCode() %></td>
<td><%= subject.getName() %></td>
<td><button data-value="<%= subject.getCode() %>" class="exam-link">Exams</button></td>
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
<script>
function load(){
	var examLinks = document.querySelectorAll(".exam-link");
	for (var i=0; i<examLinks.length; i++){
		examLinks[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/StudentExam.jsp?subjectCode=` +  e.target.getAttribute("data-value")
			window.location = link
		})
	}	
}

window.onload = load

</script>
</html> 