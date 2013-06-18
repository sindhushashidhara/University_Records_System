<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Student </title>
<script type="text/javascript">
function validate_sjsuid()
{
	var str_sjsuid = document.getElementById('sjsuid').value;
	var regex = /^\d{7}/;
	var res = regex.test(str_sjsuid);
	if(res.toString()=="false") {
		alert("Enter seven digit number for SJSU ID");
	}
}
function delete_student() {
	var res = confirm("Are you sure you want to delete record of this student?");
	if(res==true) {
	return true	
	}
	else {
		return false
	}
}
</script>

</head>
<body>
<h1>Delete Student</h1>
<form method="post" action="DeleteStudent">
<table>
<tr> <td> Enter SJSU ID : </td><td><input type="text" name="sjsuid" onblur="validate_sjsuid()"/>
<tr> <td> <input type="submit" value="Delete Record" onclick="delete_student()" />
<%if("true".equals(request.getAttribute("courseid")))
{%>
	<div style="color: red">Student Deleted</div>
 <% } else if("false".equals(request.getAttribute("courseid"))){ 
  %><div style="color: red">Student Delete error!!!</div> <%} %><br>


</table>
</form>
</body>
</html>