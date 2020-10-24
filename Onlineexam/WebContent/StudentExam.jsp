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
String subjectId = request.getParameter("subjectCode");
String year = request.getParameter("year");
String semester = request.getParameter("semester");
String examType = request.getParameter("examType");
SubjectDataMapper sm = new SubjectDataMapper();

ExamService es = new ExamService();

ScriptbookService sbs= new ScriptbookService();
/* ExamDataMapper em = new ExamDataMapper(); */

Subject subject = sm.loadSubject(request.getParameter("subjectCode"));

UserDataMapper um = new UserDataMapper();
Exam e = es.getExam(subjectId, year, semester, examType);
String student_id = (String)session.getAttribute("userid");
Student student_obj = um.loadFullStudent(student_id);

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
<th>Attempt</th>

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
<td>
<% if(exam.getPublished().equals("Y") && exam.getClosed().equals("N")) {
	%>
<button data-subject-id="<%=subject.getCode()%>" data-year="<%=exam.getYear()%>" data-semester="<%=exam.getSemester()%>" data-exam-type="<%=exam.getExamType()%>" class="attempt-exam">Attempt</button>
<% } %>
</td>

</tr>
      
    
 
<%
	} // for loop for multiple choise question
	%>

</table>
<br/>
<h2>Results</h2>

<table>
<tr>
<!-- <th>Exam Id</th> -->

<th>Year</th>
<th>Semester</th>
<th>Exam Type</th>
<th>Marks Obtained</th>
<th>Scriptbook</th>
</tr>

<%
for (Scriptbook sb : sbs.getMarkedExamScriptbooksByStudent(request.getParameter("subjectCode"), request.getParameter("year"), request.getParameter("semester"), request.getParameter("examType"), student_obj.getStudentId())) {
		
		 %>
		 
		
<tr>
<%-- <td><%= exam.getExamId() %></td> --%>
<td><%= sb.getYear() %></td>
<td><%= sb.getSemester() %></td>
<td><%= sb.getExamType() %></td>
<td><%= sb.getTotalMark() %></td>
<td>
<button data-subject-id="<%=subject.getCode()%>" data-year="<%=e.getYear()%>" data-semester="<%=e.getSemester()%>" data-exam-type="<%=e.getExamType()%>" data-student-number="<%=sb.getStudentNumber()%>" class="view-scriptbook">View</button>
</td>

</tr>

  
    
 
<%
	} // for loop for multiple choise question
	%>

</table>




</body>
<script>
function load(){
	
	var viewButtons = document.querySelectorAll(".view-scriptbook");
	console.log(viewButtons);
	for (var i=0; i<viewButtons.length; i++){
		viewButtons[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/Onlineexam/StudentScriptbookView.jsp?`
			var params = "subjectCode=" + e.target.getAttribute("data-subject-id") + 
			"&year=" + e.target.getAttribute("data-year") + 
			"&semester=" + e.target.getAttribute("data-semester") + 
			"&examType=" + e.target.getAttribute("data-exam-type")
			link = link + params
			window.location = link
		})
	}
}



window.onload = load

</script>

</html>