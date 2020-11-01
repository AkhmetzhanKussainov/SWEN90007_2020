<%@ page import="Security.*" %>
<%@ page import="domain.*" %>
<%@ page import="datasource.*" %>
<%@ page import="service.*" %>
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
<title>Teacher Exam Detail</title>
</head>
<body>
<%
SubjectDataMapper sm = new SubjectDataMapper();
/* ExamDataMapper em = new ExamDataMapper(); */
ExamService es = new ExamService();

Subject subject = sm.loadSubject(request.getParameter("subjectCode"));



%>
<%-- <h1><%= subject.getName()%>: <%= subject.getCode()%></h1> --%>
<h1>Add Exam for: <%= subject.getCode()%></h1>


<form action="CreateExam" method="post">

<label>Year</label>
<input required type="text" name="year"/>
<br/>
<br/>
<label>Semester</label>
<input required type="text" name="semester"/>
<br/>
<br/>
<label>Exam Name</label>
<input required type="text" name="exam-name"/>
<br/>
<br/>
<label>Exam Type</label>
<select name="exam-type">
<option value="F">F</option>
<option value="M">M</option>
</select>
<br/>
<br/>



<!-- <label>Exam Start Time</label>
<br/>
<label>eg. 2012 07 10 14 58</label>
<br/>
<label>Year (yyyy)</label>
<input required type="text" name="start-year" />
<label>Month (MM)</label>
<input required type="text" name="start-month"/>
<label>Day (dd)</label>
<input required type="text" name="start-day" />
<br/>
<label>Hour (HH)</label>
<input required type="text" name="start-hour" />
<label>Min (mm)</label>
<input required type="text" name="start-min" />
<br/>
<br/>
<label>Exam End Time</label>
<br/>
<label>Year (yyyy)</label>
<input required type="text" name="end-year" />
<label>Month (MM)</label>
<input required type="text" name="end-month"/>
<label>Day (dd)</label>
<input required type="text" name="end-day" />
<br/>
<label>Hour (HH)</label>
<input required type="text" name="end-hour" />
<label>Min (mm)</label>
<input required type="text" name="end-min" />
<br/> -->



<br/>
<br/>
<label>Total Marks</label>
<input required required type="number" name="total-marks"/>
<br/>
<br/>
<label>Start Time</label>
<!--  <input type="datetime-local" name="start-time"/>
<br/>
<br/>
<label>End Time</label>
<input type="datetime-local" name="end-time"/>
<br/>
<br/>-->
<input type="hidden" name="subject-id" value="<%= subject.getCode()%>"/>
<input type="submit" value="Add Exam"/>

</form>


</body>
</html>