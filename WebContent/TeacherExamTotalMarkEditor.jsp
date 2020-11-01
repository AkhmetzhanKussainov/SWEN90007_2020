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
<title>Edit total mark</title>
</head>
<body>
<%
String subjectId = request.getParameter("subjectCode");
String year = request.getParameter("year");
String semester = request.getParameter("semester");
String examType = request.getParameter("examType");
String studentId = request.getParameter("studentNumber");

SubjectDataMapper sm = new SubjectDataMapper();
ExamDataMapper em = new ExamDataMapper();

ExamService es = new ExamService();

Subject subject = sm.loadSubject(request.getParameter("subjectCode"));


Exam e = es.getExam(subjectId, year, semester, examType);

String title = e.getExamName();

ScriptbookService sbs = new ScriptbookService();

int currentTotal = sbs.getCurrentMark(subjectId, year, semester, examType, studentId);

int summedTotal = sbs.getAutomaticMark(subjectId, year, semester, examType, studentId);

%>
<%-- <h1><%= subject.getName()%>: <%= subject.getCode()%></h1> --%>
<h1>Edit total mark</h1>
<h1>Edit scriptbook total mark</h1>
<br/>
<h2>For exam: <%= title %></h2>
<br/>
<h2>For student: <%= studentId %></h2>

<h3>Current total: <%= currentTotal %></h1>
<h3>Summed total: <%= summedTotal %></h1>


<form action="UpdateScriptBookTotalMark" method="post">

<label>New Total Mark: </label>
<input required type="text" name="newTotalMark" value="<%= currentTotal %>"/>
<br/>

<br/> 
<input type="hidden" name="subjectCode" value="<%= subjectId %>"/>
<input type="hidden" name="year" value="<%= year %>"/>
<input type="hidden" name="semester" value="<%= semester %>"/>
<input type="hidden" name="examType" value="<%= examType %>"/>
<input type="hidden" name="studentNumber" value="<%= studentId %>"/>
<input type="submit" value="Update Mark"/>

</form>

</body>
</html>