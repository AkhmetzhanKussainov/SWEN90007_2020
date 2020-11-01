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
<th>Finalised</th>
<th>Submitted</th>
<th>Set Marks</th>
<th>Scriptbook</th>
</tr>

<%

if (sbs.getExamScriptbooks(request.getParameter("subjectCode"), request.getParameter("year"), request.getParameter("semester"), request.getParameter("examType")).size()==0) {
	%><tr>There are no scriptbooks to display</tr><%
}
	

System.out.println("getting scriptbooks");
for (Scriptbook sb : sbs.getExamScriptbooks(request.getParameter("subjectCode"), request.getParameter("year"), request.getParameter("semester"), request.getParameter("examType"))) {
	System.out.println("Gotten scriptbook");
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
<button data-subject-id="<%=subject.getCode()%>" data-year="<%=e.getYear()%>" data-semester="<%=e.getSemester()%>" data-exam-type="<%=e.getExamType()%>" data-student-number="<%=sb.getStudentNumber()%>" class="edit-scriptbook-total">Set</button>
<% } %>
</td>


<td>
<% if(!sb.isMarked() && sb.isSubmitted()) {
	%>
<button data-subject-id="<%=subject.getCode()%>" data-year="<%=e.getYear()%>" data-semester="<%=e.getSemester()%>" data-exam-type="<%=e.getExamType()%>" data-student-number="<%=sb.getStudentNumber()%>" class="load-scriptbook-marking">Load</button>
<% } %>
</td>

</tr>

  
    
 
<%
	} // for loop for multiple choise question
	%>

</table>



<form action="Logout" method="post">
	<input type="submit" value="Logout"/>
</form>
</body>

<script>
function load(){
	
	//For editing the total mark
	var editScriptbookTotal = document.querySelectorAll(".edit-scriptbook-total");
	
	for (var i=0; i<editScriptbookTotal.length; i++){
		editScriptbookTotal[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/OWLs/TeacherExamTotalMarkEditor.jsp?`
			var params = "subjectCode=" + e.target.getAttribute("data-subject-id") + 
			"&year=" + e.target.getAttribute("data-year") + 
			"&semester=" + e.target.getAttribute("data-semester") + 
			"&examType=" + e.target.getAttribute("data-exam-type") +
			"&studentNumber=" + e.target.getAttribute("data-student-number")
			link = link + params
			window.location = link
		})
	}
	
	//For loading the scriptbook
	var loadScriptbookMarking = document.querySelectorAll(".load-scriptbook-marking");
	console.log(loadScriptbookMarking);
	for (var i=0; i<loadScriptbookMarking.length; i++){
		loadScriptbookMarking[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/OWLs/TeacherExamMarking.jsp?`
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