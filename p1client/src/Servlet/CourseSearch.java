package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.CourseProxy;

/**
 * Servlet implementation class CourseSearch
 */
public class CourseSearch extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	CourseProxy p1=new CourseProxy();
	//ServiceProxy proxy = new ServiceProxy();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseSearch() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String qdone;
		System.out.println("search servlet");
		try{
			
			String id = request.getParameter("Courseid");
			
			String name = request.getParameter("CourseName");
			System.out.println("search servlet");
			p1.setEndpoint("http://localhost:8080/p1/services/course");
			qdone=p1.search(id,name);
			String[] a=qdone.split("/");
			request.setAttribute("courseid",a[0]);
			request.setAttribute("cname",a[1]);
			request.setAttribute("itime",a[2]);
			request.setAttribute("location",a[3]);
			request.setAttribute("faculty",a[4]);
			request.getRequestDispatcher("/View/CourseDetails.jsp").
			forward(request,response);
			System.out.println(a[0]);
			System.out.println(a[1]);
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