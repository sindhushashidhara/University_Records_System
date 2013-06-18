package connection;


import javax.jws.WebService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;



//import sun.misc.Request;



@WebService
public class student {


	
	int sjsuid;
	String fname;
	String lname;
	String street;
	String city;
	String state;
	int zip;
	String dept;
	String ccmplted;
	String ctaken;


public String add(int sid,String fname,String lname,String street,String city,String state,int zip, String dept, String ccmpleted,String ctaken)
{
	DatabaseConnection db1=new DatabaseConnection();
	//course c1=new course();
	String serc;
	System.out.println(sid+ "-------------------");
	serc = db1.adds(sid,fname,lname,street,city,state,zip,dept,ccmpleted,ctaken);
	
	
	//System.out.println(a);
		return serc;
	
}

public String del(int sid)
{
	DatabaseConnection db1=new DatabaseConnection();
	//student c1=new student();
	String serc;
	System.out.println(sid+ "-------------------");
	serc = db1.dels(sid);
	
	
	//System.out.println(a);
		return serc;
	
}
public String update(int sid,String fname,String lname,String street,String city,String state,int zip, String dept,String ccmpleted,String ctaken)
{
	DatabaseConnection db1=new DatabaseConnection();
	//course c1=new course();
	String serc;
	System.out.println(sid+ "-------------------");
	//String a=c1.searchc(cid);
	
	serc = db1.updates(sid,fname,lname,street,city,state,zip,dept,ccmpleted,ctaken);
	
	
	//System.out.println(a);
		return serc;
	
}
public String searchf(int sid)
{
	DatabaseConnection db1=new DatabaseConnection();
	student c2=new student();
	String serc;
	System.out.println(sid+ "-------------------");
	serc = db1.searchs(sid);
	//empid fname lname street city zip dept ofhrs instrctd cinstrct
	//String s1=context.
	c2.sjsuid=db1.v5;
	c2.fname=db1.v2;
	c2.lname=db1.v3;
	c2.street=db1.v4;
	c2.city=db1.v6;
	c2.zip=db1.v11;
	c2.dept=db1.v8;
	c2.ccmplted=db1.v9;
	c2.ctaken=db1.v10;

	String a=c2.sjsuid+"/"+c2.fname+"/"+c2.lname+"/"+c2.street +"/"+c2.city+"/"+c2.zip+"/"+c2.dept+"/"+c2.ccmplted+"/"+c2.ctaken;
	
	//System.out.println(a);
		return a;
	
}

	
/*	public String search(String id,String Name)
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
	
}*/


}
