<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Course</title>
<script type="text/javascript">
function delete_record(){
	var res = confirm("Are you sure you want to delete this course?");
	if(res==true) {
		
	}
	else {
		
	}
}
</script>
</head>
<body>
<h1> Delete Course</h1>
<form method="post" action="DeleteCourse">
<table>
<tr> <td> Enter Course-ID : </td> <td> <input type="text" name="cid" onblur="delete_record()" /></tr>
<tr> <td> <input type="submit" value="Delete Record"/></td></tr>
<tr> <td> 
<%if("true".equals(request.getAttribute("courseid")))
{%>
	<div style="color: red">Course Deleted</div>
 <% } else if("false".equals(request.getAttribute("courseid"))){ 
  %><div style="color: red">Course Delete error!!!</div> <%} %><br>


</td></tr>

</table>
</form>
</body>
</html>