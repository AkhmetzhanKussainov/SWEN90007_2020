<%@ page import="domain.Question" %>
<%@ page import="domain.ShortQuestion" %>
<%@ page import="domain.MultipleQuestion" %>
<%@ page import="domain.Subject" %>
<%@ page import="domain.Exam" %>
<%@ page import="domain.Question.choice" %>
<%@ page import="domain.MultipleAttempt" %>
<%@ page import="domain.ShortAttempt" %>
<%@ page import="domain.Student" %>
<%@ page import="datasource.*" %>
<%-- <%@ page import="domain.Question.choice" %> --%>
<%@ page import="service.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<style>

	.question-form-wrap {
	  display: none;
      position: fixed;
      top: 0;
      left: 0;
      height: 100vh;
      width: 100vw;
     /*  display: flex; */
      justify-content: center;
      align-items: center;
      background: #00000070;
    }
    
    .question-form-wrap.show {
    	display: flex;
    }
    
    .question-form {
      padding: 30px;
      min-width: 300px;
      background: #fff;
    }
    .flex {
      display: flex;
    }
    .flex--right {
      justify-content: flex-end;
    }
    form {
      margin: 0;
    }
    .input {
      width: 100%;
    }
    button {
      margin-right: 10px;
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
<title>Student Exam ScriptBook</title>
</head>
<body>
<%
QuestionService qs = new QuestionService();
String subjectId = request.getParameter("subjectCode");
String year = request.getParameter("year");
String semester = request.getParameter("semester");
String examType = request.getParameter("examType");
String questionId = request.getParameter("questionID");
String shortQuestionId = request.getParameter("shortQuestionId");
String studentNumber = request.getParameter("studentNumber");
String editMultipleMode = null;
String submit = null;
String addMultipleMode = null;
String deleteShortMode = null;

String editShortMode = null;
String addShortMode = null;
MultipleAttempt ma = null; 
ShortAttempt sa = null; 

if (request.getParameter("addMultipleMode") != null){
	addMultipleMode = request.getParameter("addMultipleMode");
}

if (request.getParameter("editMultipleMode") != null){
	editMultipleMode = request.getParameter("editMultipleMode");
}

if (request.getParameter("submit") != null){
	submit = request.getParameter("submit");
}

if (request.getParameter("deleteShortMode") != null){
	deleteShortMode = request.getParameter("deleteShortMode");
}

if (request.getParameter("addShortMode") != null){
	addShortMode = request.getParameter("addShortMode");
}

if (request.getParameter("editShortMode") != null){
	editShortMode = request.getParameter("editShortMode");
}

/* 
if (questionId != null){
	ma = qs.getMultipleQuestion(questionId, subjectId, semester, year, examType);
}

if (shortQuestionId != null){
	sq = qs.getShortQestion(shortQuestionId, subjectId, semester, year, examType); */


String baseURL = request.getRequestURL().toString() + "?subjectCode=" + subjectId + "&year=" + year + "&semester=" + semester + "&examType=" + examType;

SubjectDataMapper sm = new SubjectDataMapper();
/* ExamDataMapper em = new ExamDataMapper(); */

ExamService es = new ExamService();

ScriptbookService sbs = new ScriptbookService();

MultipleQuestion mq = null;
ShortQuestion sq = null;

Subject subject = sm.loadSubject(request.getParameter("subjectCode"));


Exam e = es.getExam(subjectId, year, semester, examType);

UserDataMapper um = new UserDataMapper();
String student_id = (String)session.getAttribute("userid");
/* Student student_obj = um.loadFullStudent(student_id); */



%>
<form method="post" action="SubmitMarks">
<%-- <h1><%= subject.getName()%>: <%= subject.getCode()%></h1> --%>
<h1>ScriptBook for: <%= e.getExamName()%>, Year: <%= e.getYear()%>, Sem: <%= e.getSemester()%>, ExamType: <%= e.getExamType()%>, StudentNumber: <%= studentNumber%></h1>

<div style="width:90%; margin: auto; display: flex; align-items: center; justify-content: space-between;">
<h2>Multiple Choice Attempts</h2>
</div>

<%
	int i =0;
	
	System.out.println(sbs.getAllMultipleAttempts(subjectId, year, semester, examType, studentNumber));
            for (MultipleAttempt multiplea : sbs.getAllMultipleAttempts(subjectId, year, semester, examType, studentNumber)) {
            	questionId = multiplea.getQuestionId();
            	System.out.println("starting");
            	mq = qs.getMultipleQuestion(questionId, subjectId, year, semester, examType);
            	System.out.println(questionId);
            	System.out.println(mq.getQuestionText());

       		 %>
       		 
        <table  style="width:90%; margin: auto;">

        	<tr>        	
        	<td colspan="3"><%= multiplea.getQuestionId() %>. <%= mq.getQuestionText() %></td>
        	</tr>
            <tr>
            <th><input name="multiple-attempt-ans-<%=i%>" type="hidden" value="<%= multiplea.getAttemptedAns() %>"/>
            Attempted:<%= multiplea.getAttemptedAns() %></th>
            <th>Correct:<%= mq.getCorrectAnswer() %></th>
			<th><input name="multiple-attempt-qid-<%=i%>" type="hidden" value="<%= multiplea.getQuestionId() %>"/>
			Mark: <input name="multiple-attempt-mark-<%=i%>" style="width: 30px;" type="number" min="0" max="<%= multiplea.getTotalMarks() %>" value="<%= multiplea.getMark() %>"/> / <%= mq.getPossibleMarks() %></th>
				 
                 
            </tr>       
            <br/>
        </table>
        
        <br/>
        <br/>
       
        <%
          	i++;
            }
            // for loop for multiple choise question
        	%>
 
 
 <div style="width:90%; margin: auto; display: flex; align-items: center; justify-content: space-between;">
<h2>Short Attempts</h2>
</div>

<%
int j =0;
System.out.println(sbs.getAllShortAttempts(subjectId, year, semester, examType, studentNumber).size());

            for (ShortAttempt shorta : sbs.getAllShortAttempts(subjectId, year, semester, examType, studentNumber)) {
            	sq = qs.getShortQuestion(questionId, subjectId, year, semester, examType);
            	
       		 System.out.println(shorta.getQuestionId());
       		System.out.println(shorta.getAttemptedAns());
       		 %>
       		 
        <table  style="width:90%; margin: auto;">

        	<tr>        	
        	<td colspan="2"><%= shorta.getQuestionId() %>. <%= sq.getQuestionText() %> </td>
        	</tr>
            <tr>
              
             <th><input name="short-attempt-ans-<%=i%>" type="hidden" value="<%= shorta.getAttemptedAns() %>"/>
             Attempted:  <%= shorta.getAttemptedAns() %></th>
			<th width="200"><input name="short-attempt-qid-<%=i%>" type="hidden" value="<%= shorta.getQuestionId() %>"/>
			Mark: <input name="short-attempt-mark-<%=j%>" style="width: 30px;" type="number" min="0" max="<%= shorta.getTotalMarks() %>" value="<%= shorta.getMark() %>" /> / <%= sq.getPossibleMark() %></th>
                
                	<%-- <input type="hidden" name="short-question-id-<%=j%>" value="<%= shorta.getQuestionId() %>"/> --%>
		
                
                 <%-- <th style="width: 60px;">Mark: <%= shorta.getTotalMarks() %></th> --%>
            </tr>       
        </table>
        
        <br/>
        <br/>
       
        <%
        j++;
          		  } 
            // for loop for multiple choise question
        	%>
        	
       
       
 <%-- <% 
       if (submit != null && submit.equals("True")){
       %> 	 --%>
 	<div class="question-form-wrap" id="confirm-alert">
      <div class="question-form">
          <h2>Are you sure you want to submit the scriptbook?</h2>
          <div class="flex flex--right">
            <input type="submit" value="Submit" />
          </div>
      <span id="cancel" style="cursor:pointer;">Cancel</span>
      </div>
    </div>       
    
<%--  <% } %> --%>
 
 <!-- <div style="text-align: center;">
<input type="submit" value="Submit" />
</div> -->
<input type="hidden" class="input" name="multipleQuestionCount" value="<%= i %>"></input>
      		<input type="hidden" class="input" name="shortQuestionCount" value="<%= j %>"></input>
         <input type="hidden" class="input" name="subjectCode" value="<%= subjectId %>"></input>
         <input type="hidden" class="input" name="year" value="<%= year %>"></input>
         <input type="hidden" class="input" name="semester" value="<%= semester %>"></input>
         <input type="hidden" class="input" name="examType" value="<%= examType %>"></input>
         <input type="hidden" class="input" name="url" value="<%= baseURL %>"></input>
         <input type="hidden" class="input" name="studentId" value="<%= studentNumber %>"></input>
</form>


 <div style="width:90%; margin: auto; display: flex; align-items: center; justify-content: center;">
					<button id="submit" >Submit</button>
</div>


</body>
<script>
	function load(){
		var submitButton = document.getElementById("submit")
		submitButton.addEventListener("click", function(e){
			document.getElementById("confirm-alert").classList.add("show");
		})
		var cancelButton = document.getElementById("cancel")
		cancelButton.addEventListener("click", function(e){
			document.getElementById("confirm-alert").classList.remove("show");
		})
	}
	
	window.onbeforeunload = function(){
		return "Reloading the page will lead to loss of progress on the exam! Please confirm!"
	}
	
	window.onload = load
</script>
</html>