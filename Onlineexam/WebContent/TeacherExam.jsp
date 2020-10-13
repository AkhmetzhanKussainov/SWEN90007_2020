<%@ page import="domain.*" %>
<%@ page import="datasource.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
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
ExamDataMapper em = new ExamDataMapper();

Subject subject = sm.loadSubject(request.getParameter("subjectCode"));


em.loadExams();


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
<th>Semester</th>
<th>Exam Creator</th>
<th>Exam Name</th>
<th>Total Marks</th>
<th>Published</th>
<th>Closed</th>
<th>Edit</th>
</tr>

<%
for (Exam exam : em.loadExams()) {
	System.out.println(exam.getSubjectId());
	if (exam.getSubjectId().equals(request.getParameter("subjectCode"))){
		
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
<td><button data-subject-id="<%=subject.getCode()%>" data-year="<%=exam.getYear()%>" data-semester="<%=exam.getSemester()%>" data-exam-type="<%=exam.getExamType()%>" class="edit-exam">Edit</button></td>
</tr>

  
    
 
<%
	}
 		  } // for loop for multiple choise question
	%>

</table>



</body>
<script>
function load(){
	var addButton = document.getElementById("add-btn");
	addButton.addEventListener("click", function(e){
		var link = `${document.location.origin}/TeacherExamDetail.jsp?subjectCode=` +  e.target.getAttribute("data-value")
		window.location = link
	})
	
	var editButtons = document.querySelectorAll(".edit-exam");
	console.log(editButtons);
	for (var i=0; i<editButtons.length; i++){
		editButtons[i].addEventListener("click", function(e){
			var link = `${document.location.origin}/Onlineexam/TeacherExamDetailEdit.jsp?`
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