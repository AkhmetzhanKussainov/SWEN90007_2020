<%@ page import="Security.*" %>
<%@ page import="Concurrency.ExclusiveLockManager" %>
<%@ page import="domain.*" %>
<%@ page import="service.*" %>
<%@ page import="datasource.*" %>
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

<%
////Concurrency Check - lock for editing exam details

//Get some info for concurrency check
String subjectId = request.getParameter("subjectCode");
String year = request.getParameter("year");
String semester = request.getParameter("semester");
String examType = request.getParameter("examType");

ExamService es = new ExamService();
Exam e = es.getExam(subjectId, year, semester, examType);

String sessionID = (String) session.getAttribute("userid");
ExclusiveLockManager lock = ExclusiveLockManager.getLock();

//Create unique key
String uniqueKey = subjectId+year+semester+examType;

if (lock.hasLock(uniqueKey, sessionID,"ExamLock") == false) {
System.out.println("Acquired lock");
lock.acquireLock(uniqueKey, sessionID,"ExamLock");
}else {
System.out.println("Could not acquire lock");
response.sendRedirect("NoLock.jsp");
}

//End Concurrency check

%>

<head>
<meta charset="ISO-8859-1">
<title>Delete Exam</title>

</head>
<body>
<%



SubjectDataMapper sm = new SubjectDataMapper();


Subject subject = sm.loadSubject(request.getParameter("subjectCode"));

Exam exam = es.getExam(subjectId, year, semester, examType);

System.out.println(exam);

String examName = exam.getExamName();



%>
<h1 align="center">Delete Exam Information</h1>

<h4>Hi ${username}, you are about to delete an exam: <%=examName%></h4>

<br/>
<form action="DeleteExam" method="post">
	<input type="submit" value="Delete Exam"/>
    <input type="hidden" name="subjectId" value="<%=subjectId%>" />
    <input type="hidden" name="year" value="<%=year%>" />
    <input type="hidden" name="semester" value="<%=semester%>" />
    <input type="hidden" name="examType" value="<%=examType%>" />
</form>
<br/>
 
</body>
</html>