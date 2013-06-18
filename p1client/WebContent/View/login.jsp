<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login</title>
<style type="text/css">
body {
	text-align: center;
}
</style>
<script type="text/javascript">


function validate(thisform)
{
	with (thisform)
	{
		  if (isNaN(user.value)) {
                alert('not a number');
                user.focus();
    			return false;
            }
        
		if (user.value=="" || user.value == null)
			{
			alert("user cannot be blank!!");

			user.focus();
			return false;
			}
		
		if (password.value=="" || password.value == null)
		{
		alert("password cannot be blank!!");

		password.focus();
		return false;
		}

	}

}

</script>
</head>
<body>
	LOGIN PLEASE
	<form id="form1" method="post" action="login">
		<div>
			<span class="label">UserName </span><input type="text" name="user"  onblur="return validate(form1)"/>
		</div>
		<div>
			<span class="label">password </span><input type="password"name="password" />
		</div>
		<div>
			<input class="submit" type="submit" value="submit">
		</div>
<%if("false".equals(request.getAttribute("courseid")))
{%>
	<div style="color: red">Login Error!!! Please Enter Valid Id & Password</div>
 <% }  
  %>

	</form>

</body>
</html>