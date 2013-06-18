package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.CourseProxy;

/**
 * Servlet implementation class AddCourse
 */
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CourseProxy p1=new CourseProxy();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String qdone;
		System.out.println("Add course servlet");
		try{
			
			String id = request.getParameter("cid");
			System.out.println(id);
			String name = request.getParameter("cname");
			System.out.println(name);
			String loc = request.getParameter("loc");
			System.out.println(loc);
			String insttime = request.getParameter("it");
			int empid = Integer.parseInt(request.getParameter("empid"));
			System.out.println("add servlet" + empid);
			
			p1.setEndpoint("http://localhost:8080/p1/services/course");
			qdone=p1.add(id,name,insttime,loc,empid);
			//String[] a=qdone.split("/");
			String s;
			if(qdone.contentEquals("true"))
			{
				s="true"; 
				//s="course added successfully";
			}else
			{
				s="false"; 
				//s="course add error";
			}
			request.setAttribute("courseid",s);
			//request.setAttribute("cname",a[1]);
			//request.setAttribute("itime",a[2]);
			//request.setAttribute("location",a[3]);
			//request.setAttribute("faculty",a[4]);
			request.getRequestDispatcher("/View/AddCourse.jsp").forward(request,response);
			//System.out.println(a[0]);
			//System.out.println(a[1]);
			
			//response.sendRedirect("http://localhost:8080/p1client/View/CourseDetails.jsp");
			/*request.setAttribute("key1", Object1);
			request.setAttribute("key2", Object2);
			request.setAttribute("key3", Object3);
			.
			.
			.
			request.setAttribute("keyn", Objectn);
			then

			String destination = "/WEB-INF/pages/result.jsp";
			RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
			rd.forward(request, response);*/
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}
	
	
	}

}
