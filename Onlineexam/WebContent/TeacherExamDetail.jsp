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