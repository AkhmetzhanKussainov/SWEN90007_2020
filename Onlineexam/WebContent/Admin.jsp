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
<h3 align="center">User Information</h3>
<h3 align="center">Student info:</h3>

<div align="center">
<%
UserDataMapper um = new UserDataMapper();
  um.loadAllUsers();
           		 for (Student student : um.getAllStudents()) {
           			
       		 %>
       		 
       		 
       		 
       		  <table>
  <tr>
    <th>User Name</th>
    <th>Password</th>
    <th>StudentId</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>House</th>
  </tr>
  <tr>
    <td><%= student.getUserName() %></td>
    <td><%= student.getPassword() %></td>
    <td><%= student.getStudentId() %></td>
    <td><%= student.getFirstName() %></td>
    <td><%= student.getLastName() %></td>
    <td><%= student.getHouseAsString() %></td>
  </tr>
  
</table> 
       		 
            
                
                
             
          
        <%
          		  } // for loop for multiple choise question
        	%>
</div>
<h3 align="center">Teacher info:</h3>

<div align="center">
<%

           		 for (Teacher teacher : um.getAllTeachers()) {
       		 %>
       		 
       		 
       		 
       		  <table>
  <tr>
    <th>User Name</th>
    <th>Password</th>
    <th>TeacherId</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>House</th>
    <th>Title</th>
  </tr>
  <tr>
    <td><%= teacher.getUserName() %></td>
    <td><%= teacher.getPassword() %></td>
    <td><%= teacher.getTeacherId() %></td>
        <td><%= teacher.getFirstName() %></td>
    <td><%= teacher.getLastName() %></td>
    <td><%= teacher.getHouseAsString() %></td>
    <td><%= teacher.getTitle() %></td>
  </tr>
  
</table> 
       		 
            
                
                
             
          
        <%
          		  } // for loop for multiple choise question
        	%>
</div>

<h3 align="center">Subject info:</h3>

<div align="center">

<form action="CreateSubject" method="post">
<input type="submit" value="Create New Subject"/>
</form>
<br/>

<%
SubjectDataMapper sm = new SubjectDataMapper();


  
           		 for (Subject subject : sm.loadAllSubject()) {
           			 
           			 
       		 %>
       		 
       		 
       		 
       		  <table>
  <tr>
    <th style="width:300px">Subject ID</th>
    <th>Subject Name</th>

  </tr>
  <tr>
    <td><%= subject.getCode() %></td>
    <td><%= subject.getName() %></td>
  </tr>
  
</table> 
       		 
            
                
                
             
          
        <%
          		  } // for loop for multiple choise question
        	%>
        	
<br/>
<form action="Logout" method="post">
	<input type="submit" value="Logout"/>
</form>
        	
</div>


</body>
</html>