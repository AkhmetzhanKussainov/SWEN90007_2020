<%@ page import="domain.*" %>
<%@ page import="domain.Teacher" %>
<%@ page import="datasource.*" %>
<%@ page import="service.*" %>
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

//Load the admin service and get the subject
AdminService am = new AdminService();
Subject subject = am.getSubject(request.getParameter("subjectCode"));

%>

<h1 align="center"><%= subject.getName()%> Information</h1>

<p>Course code: <%= subject.getCode()%></p>

<h3 align="center">Appointed Teachers:</h3>

<%
if (!(am.hasTeachersBySubject(subject.getCode()))) {
	%>
	<p align="center">No teacher for this subject</p>
	<%
} else {%>

<div align="center">
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
<%

//Iterate over a list of students
for (Teacher teacher : am.getTeachersBySubject(subject.getCode())) {
           			
       		 %>    		  
  <tr>
    <td><%= teacher.getUserName() %></td>  
    <td><%= teacher.getTeacherId() %></td>
        <td><%= teacher.getFirstName() %></td>
    <td><%= teacher.getLastName() %></td>
    <td><%= teacher.getHouseAsString() %></td>
    <td><%= teacher.getTitle() %></td>
  </tr>
        <%}}%>
        	</table> 
</div>

<h3 align="center">Enrolled Students:</h3>

<%
if (!(am.hasStudentsBySubject(subject.getCode()))) {
	%>
	<p align="center">No student for this subject</p>
	<%
} else {%>

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

//Iterate over a list of students
for (Student student : am.getStudentsBySubject(subject.getCode())) {
           			
       		 %>    		  
  <tr>
    <td><%= student.getUserName() %></td>   
    <td><%= student.getStudentId() %></td>
    <td><%= student.getFirstName() %></td>
    <td><%= student.getLastName() %></td>
    <td><%= student.getHouseAsString() %></td>
  </tr>
        <%}}%>
        	</table> 
</div>

<h3 align="center">Exams:</h3>

<%
if (!(am.hasExamsBySubject(subject.getCode()))) {
	%>
	
	<p align="center">No exam for this subject</p>
	<%
} else {%>

<div align="center">
<br/>
<table>
  <tr>
    <th>Year</th>
<th>Semester</th>
<th>Exam Creator</th>
<th>Exam Name</th>
<th>Total Marks</th>
<th>Published</th>
<th>Closed</th>
<th>Type</th>

  </tr>

<%
//Iterate over a list of exams
for (Exam exam : am.getExamsBySubject(subject.getCode())) {
           			
       		 %>    		  
  <tr>
   <td><%= exam.getYear() %></td>
<td><%= exam.getSemester() %></td>
<td><%= exam.getExamCreator() %></td>
<td><%= exam.getExamName() %></td>
<td><%= exam.getTotalMarks() %></td>
<td><%= exam.getPublished() %></td>
<td><%= exam.getClosed() %></td>
<td><%= exam.getExamType() %></td>
  </tr>
        <% } } %>
        	</table> 
</div>


<br/>
<form action="Logout" method="post">
	<input type="submit" value="Logout"/>
</form>
        	



</body>
</html>