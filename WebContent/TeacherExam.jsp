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
<title>Teacher Subject Display</title>

<style>

.title{
display: flex;
justify-content: space-between; 
align-items: center;

}

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
<title>Teacher Subject Exams</title>
</head>
<body>
<%
SubjectDataMapper sm = new SubjectDataMapper();

ExamService es = new ExamService();

Subject subject = sm.loadSubject(request.getParameter("subjectCode"));



%>
<h1><%= subject.getName()%>: <%= subject.getCode()%></h1>
<div class="title">
<h2>Exams</h2>

<div>
<button data-value="<%= subject.getCode() %>" id="add-btn">Add</button>
</div>
</div>


<table>
<tr>
<!-- <th>Exam Id</th> -->
<th>Year</th>
<th>Sem</th>
<th>Type</th>
<th>Exam Creator</th>
<th>Exam Name</th>
<th>Total Marks</th>
<th>Published</th>
<th>Closed</th>
<th>Delete</th>
<th>Details</th>
<th>Questions</th>
<th>Scriptbooks</th>

</tr>

<%
for (Exam exam : es.getExams(request.getParameter("subjectCode"))) {
	System.out.println(exam.getSubjectId());
		
		 %>
		 
		
<tr>
<%-- <td><%= exam.getExamId() %></td> --%>
<td><%= exam.getYear() %></td>
<td><%= exam.getSemester() %></td>
<td><%= exam.getType() %></td>
<td><%= exam.getExamCreator() %></td>
<td><%= exam.getExamName() %></td>
<td><%= exam.getTotalMarks() %></td>
<td><%= exam.getPublished() %></td>
<td><%= exam.getClosed() %></td>
<td><button data-subject-id="<%=subject.getCode()%>" data-year="<%=exam.getYear()%>" data-semester="<%=exam.getSemester()%>" data-exam-type="<%=exam.getExamType()%>" class="delete-exam">Delete</button></td>
<td><button data-subject-id="<%=subject.getCode()%>" data-year="<%=exam.getYear()%>" data-semester="<%=exam.getSemester()%>" data-exam-type="<%=exam.getExamType()%>" class="edit-exam">Update</button></td>
<td><button data-subject-id="<%=subject.getCode()%>" data-year="<%=exam.getYear()%>" data-semester="<%=exam.getSemester()%>" data-exam-type="<%=exam.getExamType()%>" class="edit-question">Edit</button></td>

<td>
<% if(exam.getPublished().equals("Y")) {
	%>
<button data-subject-id="<%=subject.getCode()%>" data-year="<%=exam.getYear()%>" data-semester="<%=exam.getSemester()%>" data-exam-type="<%=exam.getExamType()%>" class="load-scriptbooks">Mark</button>
<% } %>
</td>

</tr>

  
    
 
<%
	} // for loop for multiple choise question
	%>

</table>

<br/>
<input type="button" value="Return button" name="Return" 
onclick="
var link = `${document.location.origin}/TeacherSubjectDisplay.jsp?`;
window.location = link;
" />


<br/>
<form action="Logout" method="post">
	<input type="submit" value="Logout"/>
</form>

</body>
<script>
function load(){
	var addButton = document.getElementById("add-btn");
	addButton.addEventListener("click", function(e){
		var link = `${document.location.origin}/TeacherExamDetail.jsp?subjectCode=` +  e.target.getAttribute("data-value")
		window.location = link
	})
	
	var deleteButtons = document.querySelectorAll(".delete-exam");
	console.log(deleteButtons);
	for (var i=0; i<deleteButtons.length; i++){
		deleteButtons[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/Delete.jsp?`
			var params = "subjectCode=" + e.target.getAttribute("data-subject-id") + 
			"&year=" + e.target.getAttribute("data-year") + 
			"&semester=" + e.target.getAttribute("data-semester") + 
			"&examType=" + e.target.getAttribute("data-exam-type")
			link = link + params
			window.location = link
			
		})
	}
	
	var editButtons = document.querySelectorAll(".edit-exam");
	console.log(editButtons);
	for (var i=0; i<editButtons.length; i++){
		editButtons[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/TeacherExamDetailEdit.jsp?`
			var params = "subjectCode=" + e.target.getAttribute("data-subject-id") + 
			"&year=" + e.target.getAttribute("data-year") + 
			"&semester=" + e.target.getAttribute("data-semester") + 
			"&examType=" + e.target.getAttribute("data-exam-type")
			link = link + params
			window.location = link
		})
	}
	
	var editQuestions = document.querySelectorAll(".edit-question");
	console.log(editQuestions);
	for (var i=0; i<editQuestions.length; i++){
		editQuestions[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/TeacherExamQuestions.jsp?`
			var params = "subjectCode=" + e.target.getAttribute("data-subject-id") + 
			"&year=" + e.target.getAttribute("data-year") + 
			"&semester=" + e.target.getAttribute("data-semester") + 
			"&examType=" + e.target.getAttribute("data-exam-type")
			link = link + params
			window.location = link
		})
	}
	
	var loadScriptbooks = document.querySelectorAll(".load-scriptbooks");
	console.log(loadScriptbooks);
	for (var i=0; i<loadScriptbooks.length; i++){
		loadScriptbooks[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/TeacherExamScriptbooks.jsp?`
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