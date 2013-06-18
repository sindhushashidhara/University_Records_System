<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Course</title>
<script type="text/javascript">
function validate_empid()
{
	var str_empid = document.getElementById('empid');
	var regex = /^\d{4}/;
	var res = regex.test(str_empid);
	if(res.toString()=="false") {
		alert("Enter four digit number for Employee ID");
	}
}
</script>
</head>
<body>
<h1> Add Course Record </h1>
<form method="post" action="AddCourse">
<table>
<tr> <td> Enter CourseID : </td><td> <input type="text" name="cid" /></tr>
<tr> <td> Enter Course Name : </td><td> <input type="text" name="cname" /></tr>
<tr> <td> Instruction Timings : </td><td> <input type="text" name="it" /></tr>
<tr> <td> Location : </td><td> <input type="text" name="loc" /></tr>
<tr> <td> Enter EmpID of faculty teaching course : </td><td> <input type="text" name="empid" /></tr>
<tr> <td> </td> </tr>
<tr> <td> <input type="submit" value="AddRecord" /></td></tr>
<tr> <td> 
<%if("true".equals(request.getAttribute("courseid")))
{%>
	<div style="color: red">Course added</div>
 <% } else if("false".equals(request.getAttribute("courseid"))){ 
  %><div style="color: red">Course add error!!!</div> <%} %><br>


</td></tr>
</table>
</form>
</body>
</html>