<%@ page import="Security.*" %>
<%@ page import="domain.*" %>
<%@ page import="domain.Teacher" %>
<%@ page import="datasource.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<% 
//Read page Authorisation Code
System.out.println("Request to access Admin");
		
request.setAttribute("action", "Access Admin");

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

<h1 align="center">Administration Information</h1>

<h4>Hi ${username}, logging in was successful.</h4> 

<h3 align="center">Student info:</h3>

<div align="center">
<br/>
<table>
  <tr>
    <th>User Name</th>
    
    <th>StudentId</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>House</th>
  </tr>
<%
UserDataMapper um = new UserDataMapper();
  um.loadAllUsers();
           		 for (Student student : um.getAllStudents()) {
           			
       		 %>
       		 
       		 
       		 
       		  
  <tr>
    <td><%= student.getUserName() %></td>
    
    <td><%= student.getStudentId() %></td>
    <td><%= student.getFirstName() %></td>
    <td><%= student.getLastName() %></td>
    <td><%= student.getHouseAsString() %></td>
  </tr>
  

       		 
            
                
                
             
          
        <%
          		  } // for loop for multiple choise question
        	%>
        	</table> 
</div>
<h3 align="center">Teacher info:</h3>
<br/>
<table>
  <tr>
    <th>User Name</th>
    
    <th>TeacherId</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>House</th>
    <th>Title</th>
  </tr>

<div align="center">
<%

           		 for (Teacher teacher : um.getAllTeachers()) {
       		 %>
       		 
       		 
       		 
       		  
  <tr>
    <td><%= teacher.getUserName() %></td>
    
    <td><%= teacher.getTeacherId() %></td>
        <td><%= teacher.getFirstName() %></td>
    <td><%= teacher.getLastName() %></td>
    <td><%= teacher.getHouseAsString() %></td>
    <td><%= teacher.getTitle() %></td>
  </tr>
  

       		 
            
                
                
             
          
        <%
          		  } // for loop for multiple choise question
        	%>
        	</table> 
</div>

<h3 align="center">Subject info:</h3>
<br/>
<div align="center">

<form action="CreateSubject" method="post">
<input type="submit" value="Create New Subject"/>
</form>
<br/>

<table>
  <tr>
    <th style="width:300px">Subject ID</th>
    <th>Subject Name</th>
    <th></th>

  </tr>
<%
SubjectDataMapper sm = new SubjectDataMapper();


  
           		 for (Subject subject : sm.loadAllSubject()) {
           			 
           			 
       		 %>
       		 
       		 
       		 
       		  
  <tr>
    <td><%= subject.getCode() %></td>
    <td><%= subject.getName() %></td>
    <td><button data-value="<%= subject.getCode() %>" class="subject-link">View</button></td>
  </tr>
  
       		 
            
                
                
             
          
        <%
          		  } 
        	%>
        	</table> 
 <br/>
 <br/>
<form action="ClearLocks" method="post">
<input type="submit" value="Clear Locks"/>
</form>
<br/>        	
<br/>
<form action="Logout" method="post">
	<input type="submit" value="Logout"/>
</form>
        	
</div>


</body>
<script>
function load(){
	var examLinks = document.querySelectorAll(".subject-link");
	for (var i=0; i<examLinks.length; i++){
		examLinks[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/OWLs/AdminSubject.jsp?subjectCode=` +  e.target.getAttribute("data-value")
			window.location = link
		})
	}	
}

window.onload = load

</script>
</html>