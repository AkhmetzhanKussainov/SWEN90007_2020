<%@ page import="domain.*" %>
<%@ page import="datasource.*" %>
<%@ page import="service.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
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
<title>Teacher Exam Questions</title>
</head>
<body>
<%
String subjectId = request.getParameter("subjectCode");
String year = request.getParameter("year");
String semester = request.getParameter("semester");
String examType = request.getParameter("examType");

SubjectDataMapper sm = new SubjectDataMapper();
/* ExamDataMapper em = new ExamDataMapper(); */

ExamService es = new ExamService();

Subject subject = sm.loadSubject(request.getParameter("subjectCode"));


Exam e = es.getExam(subjectId, year, semester, examType);



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

<%-- <select readonly name="exam-type" value="<%= e.getExamType() %>">
<option
<% if (e.getExamType().equals("F")){
				%>
				selected="selected"
			<% } %>
 value="F">F</option>
<option
<% if (e.getExamType().equals("M")){
				%>
				selected="selected"
			<% } %>
 value="M">M</option>
</select> --%>

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
<input type="submit" value="Add Exam"/>

</form>

</body>
</html>