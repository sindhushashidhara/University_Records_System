package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import connection.ServiceProxy;
/**
 * Servlet implementation class SignUp
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServiceProxy proxy = new ServiceProxy();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String qdone;
		try{
			
			String user = request.getParameter("user");
			
			String pass = request.getParameter("password");
			//
			
			proxy.setEndpoint("http://localhost:8080/p1/services/Service");
			qdone=proxy.login(user,pass);
			//System.out.println("11111"+qdone);
			int a1=qdone.compareToIgnoreCase("e");
			System.out.println("a="+a1);
			if(a1==0)
			{
				System.out.println("invalid entry");
				String s="false";
				request.setAttribute("courseid",s);
				//request.setAttribute("cname",a[1]);
				//request.setAttribute("itime",a[2]);
				//request.setAttribute("location",a[3]);
				//request.setAttribute("faculty",a[4]);
				request.getRequestDispatcher("/View/login.jsp").forward(request,response);
				
			}
				
					
			//if(a1==0)
			int a=Integer.parseInt(qdone);
			//System.out.println(a);
			//String id=null;
			if(a==0)
			{
				//int id=Integer.parseInt(user);
				HttpSession session1=request.getSession();
				if(session1!=null)
				{
				session1.setAttribute("id1",user);
				out.print("Hello ");
				out.println(session1.getAttribute("id1"));
				
				}
		     	
		     	
			//	response.sendRedirect("connect.jsp");
			}
			if(a==1)
			{
				//int id=Integer.parseInt(user);
				HttpSession session2=request.getSession(true);
				if(session2!=null)
				{
				session2.setAttribute("id1",user);
				String q1=proxy.time(user);
				session2.setAttribute("time",q1);
				}
				response.sendRedirect("http://localhost:8080/p1client/View/StudentMainPage.jsp");
		     	
			}
			if(a==3)
			{
				//int id=Integer.parseInt(user);
				HttpSession session2=request.getSession(true);
				if(session2!=null)
				{
				session2.setAttribute("id1",user);
				String q1=proxy.time(user);
				session2.setAttribute("time",q1);
				}
				response.sendRedirect("http://localhost:8080/p1client/View/AdminHome.jsp");
		     	
			}
			
		}
		catch( Exception e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
	}

//	private String toString(String login2) {
		// TODO Auto-generated method stub
	//	return null;
	//}

}
