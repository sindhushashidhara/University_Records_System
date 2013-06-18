<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
function validate_zip()
{
	var str_zip = document.getElementById('zip').value;
	var regex = /^\d{5}/;
	var res = regex.test(str_zip);
	if(res.toString()=="false") {
		alert("Enter Proper value for ZIP");
	}
}
function validate_empid()
{
	var str_empid = document.getElementById('empid').value;
	var regex = /^\d{4}/;
	var res = regex.test(str_empid);
	if(res.toString()=="false") {
		alert("Enter four digit number for Employee ID");
	}
}
function validate_fn()
{
	var str_fn = document.getElementById('fn').value;
	if(str_fn=="") {
		alert("First Name field can't be kept blank");
	}
}

function validate_ln()
{
	var str_ln = document.getElementById('ln').value;
	if(str_ln=="") {
		alert("Last Name field can't be kept blank");
	}
}
</script>
<title>Add Instructor</title>
</head>
<body>
<h1> Add Faculty </h1>

<form method="post" action="AddInstructor">
<table border="0">
<tr>
<td> Enter Employee-ID : </td> <td> <input type="text" name="empid" onblur="validate_empid()"/> </td> 
</tr>

<tr>
<td> Enter First name : </td> <td> <input type = "text" name = "fn" onblur="validate_fn()"/> </td>
</tr>

<tr>
<td> Enter Last name : </td> <td> <input type = "text" name = "ln" onblur="validate_ln()"/></td>
</tr>

<tr>
<td> Enter Street	:  </td> <td> <input type = "text" name = "street" /></td>
</tr>

<tr>
<td> Enter City	:   </td> <td> <input type = "text" name = "city" /></td>
</tr>

<tr>
<td> Select State :   </td> <td> <select name="state">
				<option value="0"> Select State </option>
				<option value="ca"> CA </option>
				<option value="az"> AZ </option>
				<option value="il"> IL </option>
				<option value="ny"> NY </option>
</select> </td>
</tr>

<tr>
<td> Enter ZIP :    </td> <td><input type = "text" name="zip" onblur="validate_zip()"/><p id="p"></p></td>
</tr>

<tr>
<td> Select Department :  </td> <td><select name="sd">
					<option value="0"> Select Department </option>
					<option value="cmpe"> CMPE </option>
					<option value="ee"> EE </option>
					<option value="me"> ME </option>
</select> </td>
</tr>

<tr>
<td>Office Hours : </td> <td><input type="text" name="oh" /></td>
</tr>

<tr>
<td>Courses Instructed : </td> <td><input type="text" name="ci" /></td>
</tr>

<tr>
<td>Current courses :  </td> <td><input type="text" name="cc" /></td>
</tr>



<input type = "submit" value = "Add Record">
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