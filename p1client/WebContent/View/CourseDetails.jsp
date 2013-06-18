<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Details Page</title>
<script type="text/javascript">
        function Search() {
            var CourseID = document.getElementById('CourseID');
            var CourseName = document.getElementById('CourseName');
            var MeetingTime = document.getElementById('MeetingTime');
            var Location = document.getElementById('Location');
            var FacultyName = document.getElementById('FacultyName');
                       
        }
    </script>
</head>
<body>
<% 
String s = request.getAttribute("courseid").toString();
String s1 = request.getAttribute("cname").toString();
String s2 = request.getAttribute("itime").toString();
String s3 = request.getAttribute("location").toString();
String s4 = request.getAttribute("faculty").toString();
//	System.out.println(s);
%>
<h4> Course Details</h4>
<table border="1">
<tr>
<th>Course ID</th>
<th>Course Name</th>
<th>Meeting Time</th>
<th>Location</th>
<th>Faculty Name</th>
</tr>
<tr>

   <td > <input type="text" name="CourseID" id="CourseID" value=<%= s %> /> </td>
   <td> <input type="text" name="CourseName" id="CourseName"value=<%= s1 %>/> </td>
   <td> <input type="text" name="MeetingTime" id="MeetingTime" value=<%= s2 %>/> </td>
   <td> <input type="text" name="Location" id="Location"value=<%= s3 %>/> </td>
   <td> <input type="text" name="FacultyName" id="FacultyName"value=<%= s4 %>/> </td>
   </tr>
   </table>
    



</body>
</html>