package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.CourseProxy;
import connection.StudentProxy;

/**
 * Servlet implementation class AddCourse
 */
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentProxy p1=new StudentProxy();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudent() {
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
		System.out.println("Add student servlet");
		try{
			
			int id =Integer.parseInt(request.getParameter("sjsuid"));
			System.out.println(id);
			String fname = request.getParameter("fn");
			System.out.println(fname);
			String lname = request.getParameter("ln");
			System.out.println(lname);
			String street = request.getParameter("street");
			System.out.println(street);
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			int zip = Integer.parseInt(request.getParameter("zip"));
			System.out.println("add servlet" + zip);
			
			String sd = request.getParameter("sd");
			System.out.println(sd);
			//String oh = request.getParameter("oh");
			//String oi = request.getParameter("oi");
			String ci = request.getParameter("ci");
			System.out.println(ci);
			String cc = request.getParameter("cc");
			System.out.println(cc);
			p1.setEndpoint("http://localhost:8080/p1/services/student");
			qdone=p1.add(id,fname,lname,street,city,state,zip,sd,ci,cc);

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
			request.getRequestDispatcher("/View/AddStudent.jsp").forward(request,response);
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
