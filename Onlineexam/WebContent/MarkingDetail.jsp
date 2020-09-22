<%@ page import="domain.*" %>
<%@ page import="domain.Question.choice" %>
<%@ page import="service.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detail View</title>

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
<body>
<h3 align="center">Multiple Choice Questions</h3>

<div align="center">
<%

QuestionService qm = new QuestionService();

           		 for (MultipleQuestion multipleq : qm.getAllMultipleQuestions()) {
       		 %>
       		 <h3 align="center">Question</h3>
       		 <form name="MultiUpdateForm" action="/Onlineexam/" method="post">
        <table  style="width:90%">

        	<tr>        	
        	<td colspan="8"><%= multipleq.getQuestionText() %> <input type="hidden" name="type" value="multiple" /></td>
        	</tr>
            <tr>
            
                <th>A. <%= multipleq.getAnswer(choice.A) %></th>
                <th>B. <%= multipleq.getAnswer(choice.B) %></th>
                <th>C. <%= multipleq.getAnswer(choice.C) %></th>
                <th>D. <%= multipleq.getAnswer(choice.D) %></th>
                <th>Answer:<%= multipleq.getCorrectAnswer() %></th>
                <th>Mark: <%= multipleq.getPossibleMark() %></th>
                <th style="WIDTH: 60px"><input type="text" style="WIDTH: 40px" name="mark"/></th>
                <th style="WIDTH: 60px"><input type = "submit" value = "Save" /></th>
                 
            </tr>       
        </table>
        <input type="hidden" name="id" value="<%= multipleq.getId() %>" />
        
        
        <input type="hidden" name="qtype" value="multi"/>    
        </form>
        <%
          		  } // for loop for multiple choise question
        	%>
		
		
		
		<h3 align="center">Short Answer Questions</h3>
		
		
<%
           		 for (ShortQuestion shortq : qm.getAllShortQuestions()) {
       		 %>
       		 <h3 align="center">Question</h3>		
		<form name="ShortUpdateForm" action="/Onlineexam/" method="post">
		
		<table style="width:90%">
		<tr><td><%= shortq.getQuestionText() %></td>
		<td rowspan="6"><%= shortq.getPossibleMark() %></td>
		<td rowspan="6" style="WIDTH: 60px"><input type="text" style="WIDTH: 40px" name="mark"/></td>
		<td rowspan="6" style="WIDTH: 60px"><input type = "submit" value = "Save" /></td>
		
		</tr>
		<tr><td rowspan="5"><%= shortq.getPossibleMark() %></td></tr>
		
		
		</table>
		<input type="hidden" name="id" value="<%= shortq.getId() %>" />
		<input type="hidden" name="qtype" value="short"/> 
		</form>
		
		<%
          		  } // for loop for short answer question
        	%>
    </div>

</body>
</html>