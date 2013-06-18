<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">



<%
   
    response.setHeader("Cache-Control","no-store");
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script language="JavaScript">
<
var nHist = window.history.length;
if(window.history[nHist] != window.location)
  window.history.forward();
>
      </Script>
 <title>JSP Page</title>
    </head>
    <body>
<%

session.removeAttribute("id1");
//session.invalidate();
response.sendRedirect("login.jsp");%>
   
    </body>
</html>