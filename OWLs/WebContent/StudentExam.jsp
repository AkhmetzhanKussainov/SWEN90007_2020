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
System.out.println("Request to access Student");
		
request.setAttribute("action", "Access Student");

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
<title>Student Subject Display</title>

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
<th>Exam Type</th>
<th>Exam Name</th>
<th>Total Marks</th>
<th>Published</th>
<th>Closed</th>
<th>Attempt</th>

</tr>

<%

if (es.getExams(request.getParameter("subjectCode")).size()==0) {
	%><tr>There are no exams to display</tr><%
}

int counter = 0;
for (Exam exam : es.getExams(request.getParameter("subjectCode"))) {
	if(exam.getPublished().equals("N")) {continue;}
	counter++;
	System.out.println(exam.getSubjectId());
	System.out.println(counter);
	System.out.println(exam.getYear());
	System.out.println(exam.getSemester());
	String yearTemp = exam.getYear();
	
		 %>
		 
		
<tr>
<%-- <td><%= exam.getExamId() %></td> --%>
<td><%= exam.getYear() %></td>
<td><%= exam.getSemester() %></td>
<td><%= exam.getExamType() %></td>
<td><%= exam.getExamName() %></td>
<td><%= exam.getTotalMarks() %></td>
<td><%= exam.getPublished() %></td>
<td><%= exam.getClosed() %></td>
<td>
<% //Can't do closed logic because of concurrency?
//if(exam.getPublished().equals("Y") && exam.getClosed().equals("N")) {
	if (exam.getPublished().equals("Y")) {
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
<%-- <h2>Results</h2>

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
<td><%= exam.getExamId() %></td>
<td><%= sb.getYear() %></td>
<td><%= sb.getSemester() %></td>
<td><%= sb.getExamType() %></td>
<td><%= sb.getTotalMark() %></td>
<td>
<button data-subject-id="<%=subject.getCode()%>" data-year="<%=sb.getYear()%>" data-semester="<%=sb.getSemester()%>" data-exam-type="<%=sb.getExamType()%>" data-student-number="<%=sb.getStudentNumber()%>" class="view-scriptbook">View</button>
</td>

</tr>

  
    
 
<%
	} 
	%>

</table> --%>


<br/>
<input type="button" value="Return button" name="Return" 
onclick="
var link = `${document.location.origin}/OWLs/StudentSubjectDisplay.jsp?`;
window.location = link;
" />


<br/>
<form action="Logout" method="post">
	<input type="submit" value="Logout"/>
</form>


</body>
<script>
function load(){
	
	/* New */
	/* var loadScriptbooks = document.querySelectorAll(".attempt-exam");
	console.log("clickedView");
	console.log(loadScriptbooks);
	for (var i=0; i<loadScriptbooks.length; i++){
		loadScriptbooks[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/OWLs/TeacherExamScriptbooks.jsp?`
			var params = "subjectCode=" + e.target.getAttribute("data-subject-id") + 
			"&year=" + e.target.getAttribute("data-year") + 
			"&semester=" + e.target.getAttribute("data-semester") + 
			"&examType=" + e.target.getAttribute("data-exam-type")
			link = link + params
			window.location = link
		})
	} */
	
	/* Old */
	var viewButtons = document.querySelectorAll(".attempt-exam");
	console.log(viewButtons);
	for (var i=0; i<viewButtons.length; i++){
		viewButtons[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/OWLs/StudentExamScriptBook.jsp?`
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