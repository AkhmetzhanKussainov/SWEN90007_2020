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

<div align="center">
<%
UserDataMapper um = new UserDataMapper();
  um.loadAllUsers();
           		 for (Student student : um.getAllStudents()) {
       		 %>
       		 <h3 align="center">User info:</h3>
       		 
       		 
       		  <table>
  <tr>
    <th>User Name</th>
    <th>Password</th>
    <th>StudentId</th>
  </tr>
  <tr>
    <td><%= student.getUserName() %></td>
    <td><%= student.getPassword() %></td>
    <td><%= student.getStudentId() %></td>
  </tr>
  
</table> 
       		 
            
                
                
             
          
        <%
          		  } // for loop for multiple choise question
        	%>
</div>


<div align="center">
<%

           		 for (Teacher teacher : um.getAllTeachers()) {
       		 %>
       		 <h3 align="center">Teacher info:</h3>
       		 
       		 
       		  <table>
  <tr>
    <th>User Name</th>
    <th>Password</th>
    <th>TeacherId</th>
  </tr>
  <tr>
    <td><%= teacher.getUserName() %></td>
    <td><%= teacher.getPassword() %></td>
    <td><%= teacher.getTeacherId() %></td>
  </tr>
  
</table> 
       		 
            
                
                
             
          
        <%
          		  } // for loop for multiple choise question
        	%>
</div>


<div align="center">
<%

           		 for (Student student : um.getAllStudents()) {
       		 %>
       		 <h3 align="center">Lazy info:</h3>
       		 
       		 
       		  <table>
  <tr>
    <th>User Name</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>House</th>
  </tr>
  <tr>
    <td><%= student.getUserName() %></td>
    <td><%= student.getFirstName() %></td>
    <td><%= student.getLastName() %></td>
    <td><%= student.getHouseAsString() %></td>
  </tr>
  
</table> 
       		 
            
                
                
             
          
        <%
          		  } // for loop for multiple choise question
        	%>
</div>


</body>
</html> 