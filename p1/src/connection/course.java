package connection;


import javax.jws.WebService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;



import sun.misc.Request;



@WebService
public class course {


	
	String courseid;
	String coursename;
	String instructiontime;
	String location;
	int faculty;


public String add(String cid,String cname, String ctime, String cloc, int empid)
{
	DatabaseConnection db1=new DatabaseConnection();
	course c1=new course();
	String serc;
	System.out.println(cid+ "-------------------");
	serc = db1.addc(cid,cname,ctime,cloc,empid);
	
	
	//System.out.println(a);
		return serc;
	
}

public String del(String cid)
{
	DatabaseConnection db1=new DatabaseConnection();
	course c1=new course();
	String serc;
	System.out.println(cid+ "-------------------");
	serc = db1.delc(cid);
	
	
	//System.out.println(a);
		return serc;
	
}
public String update(String cid,String cname, String ctime, String cloc, int empid)
{
	DatabaseConnection db1=new DatabaseConnection();
	//course c1=new course();
	String serc;
	System.out.println(cid+ "-------------------");
	//String a=c1.searchc(cid);
	
	serc = db1.updatec(cid,cname,ctime,cloc,empid);
	
	
	//System.out.println(a);
		return serc;
	
}
public String searchc(String id)
{
	DatabaseConnection db1=new DatabaseConnection();
	course c2=new course();
	String serc;
	System.out.println(id+ "-------------------");
	serc = db1.searchc(id);
	
	//String s1=context.
	c2.courseid=db1.v1;
	c2.coursename=db1.v2;
	c2.instructiontime=db1.v3;
	c2.location=db1.v4;
	c2.faculty=db1.v5;
	String a=c2.courseid+"/"+c2.coursename+"/"+c2.instructiontime+"/"+c2.location+"/"+c2.faculty;
	
	//System.out.println(a);
		return a;
	
}

	
	public String search(String id,String Name)
{
	DatabaseConnection db1=new DatabaseConnection();
	course c1=new course();
	String serc;
	System.out.println(id+ "-------------------");
	serc = db1.searchc(id,Name);
	
	//String s1=context.
	c1.courseid=db1.v1;
	c1.coursename=db1.v2;
	c1.instructiontime=db1.v3;
	c1.location=db1.v4;
	c1.faculty=db1.v5;
	String a=c1.courseid+"/"+c1.coursename+"/"+c1.instructiontime+"/"+c1.location+"/"+c1.faculty;
	
	//System.out.println(a);
		return a;
	
}


}
