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
<title>Teacher Exam Detail</title>
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
<input disabled required type="text" name="year" value="<%= year %>"/>
<br/>
<br/>
<label>Semester</label>
<input disabled required type="text" name="semester" value="<%= semester %>"/>
<br/>
<br/>
<label>Exam Name</label>
<input required type="text" name="exam-name" value="<%= e.getExamName() %>"/>
<br/>
<br/>
<label>Exam Type</label>
<select disabled name="exam-type" value="<%= e.getExamType() %>">
<option value="F">F</option>
<option value="M">M</option>
</select>

<br/>
<br/>
<label>Total Marks</label>
<input required required type="number" name="total-marks" value="<%= e.getTotalMarks() %>"/>
<br/>
<br/>
<label>Published</label>
<select name="published" value="<%= e.getPublished() %>">
<option value="N">N</option>
<option value="Y">Y</option>
</select>
<br/>
<br/>
<label>Closed</label>
<select name="closed" value="<%= e.getClosed()%>">
<option value="N">N</option>
<option value="Y">Y</option>
</select>
<br/>
<br/>
<label>Start Time</label>
<input type="datetime-local" name="start-time" value="<%= e.getStartDateString() %>" />
<br/>
<br/>
<label>End Time</label>
<input type="datetime-local" name="end-time" value="<%= e.getEndDateString() %>"/>
<br/>
<br/>
<input type="hidden" name="subject-id" value="<%= subject.getCode()%>"/>
<input type="hidden" name="exam-creator" value="<%= e.getExamCreator()%>"/>
<input type="submit" value="Add Exam"/>

</form>

<%-- <table>
<tr>
<!-- <th>Exam Id</th> -->
<th>Year</th>
<th>Semester</th>
<th>Exam Creator</th>
<th>Exam Name</th>
<th>Total Marks</th>
<th>Published</th>
<th>Closed</th>
</tr>

<%
for (Exam exam : em.loadExams()) {
	System.out.println(exam.getSubjectId());
	if (exam.getSubjectId().equals(request.getParameter("subjectCode"))){
		
		 %>
		 
		
<tr>
<td><%= exam.getExamId() %></td>
<td><%= exam.getYear() %></td>
<td><%= exam.getSemester() %></td>
<td><%= exam.getExamCreator() %></td>
<td><%= exam.getExamName() %></td>
<td><%= exam.getTotalMarks() %></td>
<td><%= exam.getPublished() %></td>
<td><%= exam.getClosed() %></td>
</tr>

      
    
 
<%
	}
 		  } // for loop for multiple choise question
	%>

</table>
 --%>


</body>
</html>