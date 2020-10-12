<%@ page import="domain.Question" %>
<%@ page import="domain.Subject" %>
<%@ page import="domain.Exam" %>
<%@ page import="domain.Question.choice" %>
<%@ page import="domain.MultipleQuestion" %>
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
      position: fixed;
      top: 0;
      left: 0;
      height: 100vh;
      width: 100vw;
      display: flex;
      justify-content: center;
      align-items: center;
      background: #00000070;
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
<title>Teacher Exam Detail</title>
</head>
<body>
<%
QuestionService qs = new QuestionService();
String subjectId = request.getParameter("subjectCode");
String year = request.getParameter("year");
String semester = request.getParameter("semester");
String examType = request.getParameter("examType");
String questionId = request.getParameter("questionID");
String editMultipleMode = null;
String deleteMultipleMode = null;
String addMultipleMode = null;
MultipleQuestion mq = null; 

if (request.getParameter("addMultipleMode") != null){
	addMultipleMode = request.getParameter("addMultipleMode");
}

if (request.getParameter("editMultipleMode") != null){
	editMultipleMode = request.getParameter("editMultipleMode");
}

if (request.getParameter("deleteMultipleMode") != null){
	deleteMultipleMode = request.getParameter("deleteMultipleMode");
}

if (questionId != null){
	mq = qs.getMultipleQuestion(questionId, subjectId, semester, year, examType);
}

String baseURL = request.getRequestURL().toString() + "?subjectCode=" + subjectId + "&year=" + year + "&semester=" + semester + "&examType=" + examType;

SubjectDataMapper sm = new SubjectDataMapper();
/* ExamDataMapper em = new ExamDataMapper(); */

ExamService es = new ExamService();


Subject subject = sm.loadSubject(request.getParameter("subjectCode"));


Exam e = es.getExam(subjectId, year, semester, examType);



%>
<%-- <h1><%= subject.getName()%>: <%= subject.getCode()%></h1> --%>
<h1>Questions for: <%= e.getExamName()%>, Year: <%= e.getYear()%>, Sem: <%= e.getSemester()%>, ExamType: <%= e.getExamType()%></h1>

<div style="width:90%; margin: auto; text-align: right;">
<a href="<%= baseURL + "&addMultipleMode=True" %>">
<button>Add</button></a>
</div>

<%
            for (MultipleQuestion multipleq : qs.getAllMultipleQuestions(subjectId, year, semester, examType)) {
       		 %>
       		 
        <table  style="width:90%; margin: auto;">

        	<tr>        	
        	<td colspan="8"><%= multipleq.getId() %>. <%= multipleq.getQuestionText() %> <input type="hidden" name="type" value="multiple" /></td>
        	</tr>
            <tr>
            
               <th>A. <%= multipleq.getAnswer(choice.A) %></th>
                <th>B. <%= multipleq.getAnswer(choice.B) %></th>
                <th>C. <%= multipleq.getAnswer(choice.C) %></th>
                <th>D. <%= multipleq.getAnswer(choice.D) %></th> 
                <th>Answer:<%= multipleq.getCorrectAnswer() %></th>
                <th>Mark: <%= multipleq.getPossibleMark() %></th>
                <th style="WIDTH: 60px">
                	<a href="<%= baseURL + "&editMultipleMode=True" + "&questionID=" + multipleq.getId() %>">
					<button>Edit</button></a>
				</th>
                <th style="WIDTH: 60px">
                	<a href="<%= baseURL + "&deleteMultipleMode=True" + "&questionID=" + multipleq.getId() %>">
					<button>Delete</button></a>
				</th>
                 
            </tr>       
            <br/>
        </table>
        
        <br/>
        <br/>
       
        <%
          		  } // for loop for multiple choise question
        	%>
       
       
       <% 
       if (addMultipleMode != null && addMultipleMode.equals("True")){
       %> 	
        <div class="question-form-wrap">
      <div class="question-form">
      <h2>Add</h2>
        <form method="post" action="AddQuestion">
        <input type="hidden" class="input" name="subjectCode" value="<%= subjectId %>"></input>
         <input type="hidden" class="input" name="year" value="<%= year %>"></input>
         <input type="hidden" class="input" name="semester" value="<%= semester %>"></input>
         <input type="hidden" class="input" name="examType" value="<%= examType %>"></input>
         <input type="hidden" class="input" name="url" value="<%= baseURL %>"></input>
         
         
          <div>
            <label>Question Text</label>
            <input class="input" name="question-text"></input>
          </div>
          <br />
          <div>
            <label>A</label>
            <input class="input" name="choice-a" />
          </div>
          <br />
          <div>
            <label>B</label>
            <input class="input" name="choice-b" />
          </div>
          <br />
          <div>
            <label>C</label>
            <input class="input" name="choice-c" />
          </div>
          <br />
          <div>
            <label>D</label>
            <input class="input" name="choice-d" />
          </div>
          <br />
          <div>
            <label>Marks</label>
            <input type="number" class="input" name="possible-mark" />
          </div>
          <br />
          <div>
            <label>Answer</label>
           <select name="answer">
			<option value="A">A</option>
			<option value="B">B</option>
			<option value="C">C</option>
			<option value="D">D</option>
			</select>
          </div>
          <div class="flex flex--right">
            <input type="submit" />
          </div>
        </form>
        <a href="<%= baseURL %>"><button>Cancel</button></a>
      </div>
    </div>
    
    <% } %>
 
  
       <% 
       if (editMultipleMode != null && editMultipleMode.equals("True") && questionId != null){
       %> 	
        <div class="question-form-wrap">
      <div class="question-form">
      <h2>Edit</h2>
        <form method="post" action="EditQuestion">
        
        <input type="hidden" class="input" name="subjectCode" value="<%= subjectId %>"></input>
         <input type="hidden" class="input" name="year" value="<%= year %>"></input>
         <input type="hidden" class="input" name="semester" value="<%= semester %>"></input>
         <input type="hidden" class="input" name="examType" value="<%= examType %>"></input>
         <input type="hidden" class="input" name="url" value="<%= baseURL %>"></input>
         <input type="hidden" class="input" name="question-id" value="<%= mq.getId() %>"></input>
         
          <div>
            <label>Question Text</label>
            <input class="input" name="question-text" value="<%= mq.getQuestionText() %>"></input>
          </div>
          <br />
          <div>
            <label>A</label>
            <input class="input" name="choice-a"  value="<%= mq.getAnsA() %>"/>
          </div>
          <br />
          <div>
            <label>B</label>
            <input class="input" name="choice-b"  value="<%= mq.getAnsB() %>"/>
          </div>
          <br />
          <div>
            <label>C</label>
            <input class="input" name="choice-c"  value="<%= mq.getAnsC() %>"/>
          </div>
          <br />
          <div>
            <label>D</label>
            <input class="input" name="choice-d"  value="<%= mq.getAnsD() %>"/>
          </div>
          <br />
          <div>
            <label>Marks</label>
            <input type="number" class="input" name="possible-mark"  value="<%= mq.getPossibleMark() %>"/>
          </div>
          <br />
          <div>
            <label>Answer</label>
           <select name="answer"  value="<%= mq.getCorrectAnswer() %>">
			<option 
			value="A" 
			<% if (mq.getCorrectAnswer().equals("A")){
				%>
				selected="selected"
			<% } %>
			>A</option>
			<option
			 <% if (mq.getCorrectAnswer().equals("B")){
				%>
				selected="selected"
			<% } %>
			 value="B">B</option>
			<option
			<% if (mq.getCorrectAnswer().equals("C")){
				%>
				selected="selected"
			<% } %>
			 value="C">C</option>
			<option 
			value="D"
			<% if (mq.getCorrectAnswer().equals("D")){
				%>
				selected="selected"
			<% } %>
			>D</option>
			</select>
          </div>
          <div class="flex flex--right">
            <input type="submit" />
          </div>
        </form>
        <a href="<%= baseURL %>"><button>Cancel</button></a>
      </div>
    </div>
    
    <% } %>
 
 
 
 
 	 <% 
       if (deleteMultipleMode != null && deleteMultipleMode.equals("True")){
       %> 	
 	<div class="question-form-wrap">
      <div class="question-form">
        <form method="post" action="DeleteQuestion">
         <input type="hidden" class="input" name="subjectCode" value="<%= subjectId %>"></input>
         <input type="hidden" class="input" name="year" value="<%= year %>"></input>
         <input type="hidden" class="input" name="semester" value="<%= semester %>"></input>
         <input type="hidden" class="input" name="examType" value="<%= examType %>"></input>
         <input type="hidden" class="input" name="url" value="<%= baseURL %>"></input>
         <input type="hidden" class="input" name="question-id" value="<%= mq.getId() %>"></input>
          <h2>Are you sure you want to delete this Question?</h2>
          <div class="flex flex--right">
            <input type="submit" value="Delete" />
          </div>
        </form>
        <a href="<%= baseURL %>"><button>Cancel</button></a>
      </div>
    </div>       
    
 <% } %>


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
</html>