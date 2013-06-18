<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Instructor</title>
<script type="text/javascript">
function validate_empid()
{
	var str_empid = document.getElementById('empid').value;
	var regex = /^\d{4}/;
	var res = regex.test(str_empid);
	if(res.toString()=="false") {
		alert("Enter four digit number for Employee ID");
	}
}
</script>

</head>
<body>
<h1> Delete Instructor Record</h1>
<form method="post" action="DeleteInstructor">
<table>
<tr> <td> Enter Employee ID : </td> <td> <input type="text" name="empid" onblur="validate_empid()"/> </tr>
<tr> <td> <input type="submit" value="Delete Record" /> </td> </tr>

<%if("true".equals(request.getAttribute("courseid")))
{%>
	<div style="color: red">Course Deleted</div>
 <% } else if("false".equals(request.getAttribute("courseid"))){ 
  %><div style="color: red">Course Delete error!!!</div> <%} %><br>

</table>
</form>
</body>
</html>