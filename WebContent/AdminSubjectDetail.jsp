<%@ page import="Security.*" %>
<%@ page import="domain.*" %>
<%@ page import="domain.Teacher" %>
<%@ page import="datasource.*" %>
<%@ page import="service.*" %>
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
<title>Admin Subject Detail</title>
</head>
<body>

<%-- Handle issues creating subject --%> 
<input id="correctCreation" type="hidden" value="${correctCreation}"/>
<input id="alertIssue" type="hidden" value="${alertIssue}"/>
<script type="text/javascript">
    var correct = document.getElementById('correctCreation').value;
    var alertIssue = document.getElementById('alertIssue').value;
    if ((correct)==='false') {
    	alert(alertIssue);
    }
</script>


<h1>Add Subject</h1>
<%

//Load the admin service
AdminService am = new AdminService(); 



%>

<form action="CreateSubjectDetail" method="post">

<label>Code</label>
<input required type="text" name="code"/>
<br/>
<br/>
<label>Title</label>
<input required type="text" name="title"/>

<label>Year</label>
<input required type="text" name="year"/>

<label>Semester</label>
<input required type="text" name="semester"/>


<br/>
<br/>

<h2>Students</h2>
<table>
  <tr>
  <th></th>
    <th>User Name</th>
    <th>StudentId</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>House</th>
  </tr>
<br/>
<%

//Iterate over a list of students
for (Student student : am.getAllStudents()) {
	        			
       		 %>    		  
  <tr>
  
  <td>
  <input type="checkbox" name="studentCheckbox" value=<%= student.getStudentId() %>>
  </td>
    <td><%= student.getUserName() %></td>   
    <td><%= student.getStudentId() %></td>
    <td><%= student.getFirstName() %></td>
    <td><%= student.getLastName() %></td>
    <td><%= student.getHouseAsString() %></td>
  </tr>
        <%}%>
        	</table>
<br/>
<h2>Teachers</h2>
<table>
  <tr>
  <th></th>
    <th>User Name</th>  
    <th>TeacherId</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>House</th>
    <th>Title</th>
  </tr>
<br/>
<%

//Iterate over a list of students
for (Teacher teacher : am.getAllTeachers()) {
	
       		 %>    		  
  <tr>
  
  <td>
  <input type="checkbox" name="teacherCheckbox" value=<%= teacher.getTeacherId() %>>
  </td>
    <td><%= teacher.getUserName() %></td>  
    <td><%= teacher.getTeacherId() %></td>
    <td><%= teacher.getFirstName() %></td>
    <td><%= teacher.getLastName() %></td>
    <td><%= teacher.getHouseAsString() %></td>
    <td><%= teacher.getTitle() %></td>
  </tr>
        <%}%>
        	</table>
<br/>        	
        	
<input type="submit" value="Add Subject"/>

</form>

</body>
</html>