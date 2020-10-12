<%@ page import="domain.*" %>
<%@ page import="datasource.*" %>
<%@ page import="service.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher Subject Display</title>

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
<title>Student Subject Exams</title>
</head>
<body>
<%
SubjectDataMapper sm = new SubjectDataMapper();

ExamService es = new ExamService();
/* ExamDataMapper em = new ExamDataMapper(); */

Subject subject = sm.loadSubject(request.getParameter("subjectCode"));


/* em.loadExams(); */


%>
<h1><%= subject.getName()%>: <%= subject.getCode()%></h1>
<h2>Exams</h2>

<table>
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
for (Exam exam : es.getExams(request.getParameter("subjectCode"))) {
	System.out.println(exam.getSubjectId());
		
		 %>
		 
		
<tr>
<%-- <td><%= exam.getExamId() %></td> --%>
<td><%= exam.getYear() %></td>
<td><%= exam.getSemester() %></td>
<td><%= exam.getExamCreator() %></td>
<td><%= exam.getExamName() %></td>
<td><%= exam.getTotalMarks() %></td>
<td><%= exam.getPublished() %></td>
<td><%= exam.getClosed() %></td>
</tr>

      
    
 
<%
	} // for loop for multiple choise question
	%>

</table>



</body>
</html>