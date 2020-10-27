<%@ page import="domain.*" %>
<%@ page import="datasource.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Permissions</title>

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

<%-- Handle issues creating subject --%> 
<input id="correctPermission" type="hidden" value="${correctPermission}"/>
<input id="alertPermission" type="hidden" value="${alertPermission}"/>
<script type="text/javascript">
    var correct = document.getElementById('correctPermission').value;
    var alertPermission = document.getElementById('alertPermission').value;
    if ((correct)==='false') {
    	alert(alertPermission);
    }
</script>
<h1 align="center">Permissions Information</h1>

<h4>Hi ${username}, please contact the administrator about your permissions.</h4> 
</body>
</html>