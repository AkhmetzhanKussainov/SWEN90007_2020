<%@ page import="Security.*" %>
<%@ page import="Concurrency.ExclusiveLockManager" %>
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

<%
////Concurrency Check - lock for editing exam details

//Get some info for concurrency check
String subjectId = request.getParameter("subjectCode");
String year = request.getParameter("year");
String semester = request.getParameter("semester");
String examType = request.getParameter("examType");

ExamService es = new ExamService();
Exam e = es.getExam(subjectId, year, semester, examType);


String sessionID = (String) session.getAttribute("userid");
ExclusiveLockManager lock = ExclusiveLockManager.getLock();

//Create unique key
String uniqueKey = subjectId+year+semester+examType;

if (lock.hasLock(uniqueKey, sessionID,"ExamLock") == false) {
	System.out.println("Acquired lock");
	lock.acquireLock(uniqueKey, sessionID,"ExamLock");
}else {
	System.out.println("Could not acquire lock");
	response.sendRedirect("NoLock.jsp");
}

//End Concurrency check

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
<title>Update Exam Details</title>
</head>
<body>
<%


SubjectDataMapper sm = new SubjectDataMapper();
Subject subject = sm.loadSubject(request.getParameter("subjectCode"));





%>
<%-- <h1><%= subject.getName()%>: <%= subject.getCode()%></h1> --%>
<h1>Edit Exam for: <%= subject.getCode()%></h1>


<form action="UpdateExam" method="post">

<label>Year</label>
<input readonly required type="text" name="year" value="<%= year %>"/>
<br/>
<br/>
<label>Semester</label>
<input readonly required type="text" name="semester" value="<%= semester %>"/>
<br/>
<br/>
<label>Exam Name</label>
<input required type="text" name="exam-name" value="<%= e.getExamName() %>"/>
<br/>
<br/>
<label>Exam Type</label>
<input readonly required type="text" name="exam-type" value="<%= examType %>"/>
<br/>
<br/>
<!-- <label>Exam Start Time</label>
<br/>
<label>Year</label>
<input required type="text" name="start-year" />
<label>Month</label>
<input required type="text" name="start-month"/>
<label>Hour</label>
<input required type="text" name="start-hour" />
<label>Min</label>
<input required type="text" name="start-min" />
<br/>
<br/>
<label>Exam End Time</label>
<br/>
<label>Year</label>
<input required type="text" name="end-year" />
<label>Month</label>
<input required type="text" name="end-month"/>
<label>Hour</label>
<input required type="text" name="end-hour" />
<label>Min</label>
<input required type="text" name="end-min" />
<br/> -->

<br/>
<br/>
<label>Total Marks</label>
<input required required type="number" name="total-marks" value="<%= e.getTotalMarks() %>"/>
<br/>
<br/>
<label>Published</label>
<%
System.out.println("Value" + e.getPublished());
if (e.getPublished().equals("Y")) {
	System.out.println("Y");
%>
<select name="published">
<option value="N">N</option>
<option selected value="Y">Y</option>
</select>
<%} else {System.out.println("N");%>

<select name="published">
<option selected value="N">N</option>
<option value="Y">Y</option>
</select>
<%}%>
<br/>
<br/>
<label>Closed</label>
<br/>
<%
System.out.println("Value" + e.getClosed());
if (e.getClosed().equals("Y")) {
	System.out.println("Y");
%>
<select name="closed">
<option value="N">N</option>
<option selected value="Y">Y</option>
</select>
<%} else {System.out.println("N");%>

<select name="closed">
<option selected value="N">N</option>
<option value="Y">Y</option>
</select>
<%}%>
<br/>
<br/>
<!-- <label>Start Time</label> -->
<!--<input type="datetime-local" name="start-time" value="<!%= e.getStartDateString() %>" />
<br/>
<br/>
<label>End Time</label>
<input type="datetime-local" name="end-time" value="<!%= e.getEndDateString() %>"/>
<br/> -->




<br/> 
<input type="hidden" name="subject-id" value="<%= subject.getCode()%>"/>
<input type="hidden" name="exam-creator" value="<%= e.getExamCreator()%>"/>
<input type="submit" value="Update Exam Details"/>

</form>

</body>
</html>