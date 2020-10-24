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

ScriptbookService sbs = new ScriptbookService();

Subject subject = sm.loadSubject(request.getParameter("subjectCode"));

ExamService es = new ExamService();

Exam e = es.getExam(subjectId, year, semester, examType);



%>
<%-- <h1><%= subject.getName()%>: <%= subject.getCode()%></h1> --%>
<h1>Scriptbooks for Exam:  <%= e.getExamName()%>, Year: <%= e.getYear()%>, Sem: <%= e.getSemester()%>, ExamType: <%= e.getExamType()%></h1>


<table>
<tr>
<!-- <th>Exam Id</th> -->
<th>Student</th>
<th>Marks</th>
<th>Marked</th>
<th>Submitted</th>
<th>Load</th>
</tr>

<%
for (Scriptbook sb : sbs.getExamScriptbooks(request.getParameter("subjectCode"), request.getParameter("year"), request.getParameter("semester"), request.getParameter("examType"))) {
		
		 %>
		 
		
<tr>
<%-- <td><%= exam.getExamId() %></td> --%>
<td><%= sb.getStudentNumber() %></td>
<td><%= sb.getTotalMark() %></td>
<td><%= sb.isMarked() %></td>
<td><%= sb.isSubmitted() %></td>

<td>
<% if(!sb.isMarked() && sb.isSubmitted()) {
	%>
<button data-subject-id="<%=subject.getCode()%>" data-year="<%=e.getYear()%>" data-semester="<%=e.getSemester()%>" data-exam-type="<%=e.getExamType()%>" data-student-number="<%=sb.getStudentNumber()%>" class="load-scriptbook-marking">Scriptbook</button>
<% } %>
</td>

</tr>

  
    
 
<%
	} // for loop for multiple choise question
	%>

</table>





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

<script>
function load(){
	
	var loadScriptbookMarking = document.querySelectorAll(".load-scriptbook-marking");
	console.log(loadScriptbookMarking);
	for (var i=0; i<loadScriptbookMarking.length; i++){
		loadScriptbookMarking[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/Onlineexam/TeacherExamMarking.jsp?`
			var params = "subjectCode=" + e.target.getAttribute("data-subject-id") + 
			"&year=" + e.target.getAttribute("data-year") + 
			"&semester=" + e.target.getAttribute("data-semester") + 
			"&examType=" + e.target.getAttribute("data-exam-type") +
			"&studentNumber=" + e.target.getAttribute("data-student-number")
			link = link + params
			window.location = link
		})
	}
}



window.onload = load

</script>

</html>