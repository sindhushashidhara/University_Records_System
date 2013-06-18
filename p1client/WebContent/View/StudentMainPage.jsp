
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Home Page </title>

<%  if(session.getAttribute("id1")==null)
{
        //out.print("hello");
       response.sendRedirect("http://localhost:8080/p1client/View/login.jsp");
}
%>

</head>
<body>
<% response.setHeader("Cache-Control","no-store");
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
           String names=(String)session.getAttribute("id1");
                                    out.println("hello"+names);
           String log=(String)session.getAttribute("time");
           out.println();
           out.println("last log in time"+log);
           
%>
<a href="logout.jsp">SignOut</a> <br/>
<h4> Student center </h4> 
<a href="CourseSearch.jsp" >Course Search</a> <br/>
<a href="http://www.w3schools.com" target="_blank">Enroll</a> <br/>
<a href="http://www.w3schools.com" target="_blank">MyAcademics</a> <br/>

</body>
</html>


