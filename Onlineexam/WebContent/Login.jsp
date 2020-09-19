<%@ page import="domain.*" %>
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
           		 for (Student student : Student.getAllMultiple()) {
       		 %>
       		 <h3 align="center">Question</h3>
       		 <form name="MultiUpdateForm" action="/Onlineexam/" method="post">
        <table  style="width:90%">

        	<tr>        	
        	<td colspan="8"><%= multipleq.getQuestion() %> <input type="hidden" name="type" value="multiple" /></td>
        	</tr>
            <tr>
            
                <th>A. <%= multipleq.getAns1() %></th>
                <th>B. <%= multipleq.getAns2() %></th>
                <th>C. <%= multipleq.getAns3() %></th>
                <th>D. <%= multipleq.getAns4() %></th>
                <th>Ans:<%= multipleq.getChos() %></th>
                <th>Mark: <%= multipleq.getMark() %></th>
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

</body>
</html>