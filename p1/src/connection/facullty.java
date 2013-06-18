package connection;


import javax.jws.WebService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;



//import sun.misc.Request;



@WebService
public class facullty {


	
	int empid;
	String fname;
	String lname;
	String street;
	String city;
	String state;
	int zip;
	String dept;
	String ofhrs;
	String instrctd;
	String cinstrct;


public String add(int eid,String fname,String lname,String street,String city,String state,int zip, String dept, String ofhrs, String instrctd,String cinstrct)
{
	DatabaseConnection db1=new DatabaseConnection();
	course c1=new course();
	String serc;
	System.out.println(eid+ "-------------------");
	serc = db1.addf(eid,fname,lname,street,city,state,zip,dept,ofhrs,instrctd,cinstrct);
	
	
	//System.out.println(a);
		return serc;
	
}

public String del(int eid)
{
	DatabaseConnection db1=new DatabaseConnection();
	course c1=new course();
	String serc;
	System.out.println(eid+ "-------------------");
	serc = db1.delf(eid);
	
	
	//System.out.println(a);
		return serc;
	
}
public String update(int eid,String fname,String lname,String street,String city,String state,int zip, String dept, String ofhrs, String instrctd,String cinstrct)
{
	DatabaseConnection db1=new DatabaseConnection();
	//course c1=new course();
	String serc;
	System.out.println(eid+ "-------------------");
	//String a=c1.searchc(cid);
	
	serc = db1.updatef(eid,fname,lname,street,city,state,zip,dept,ofhrs,instrctd,cinstrct);
	
	
	//System.out.println(a);
		return serc;
	
}
public String searchf(int eid)
{
	DatabaseConnection db1=new DatabaseConnection();
	facullty c2=new facullty();
	String serc;
	System.out.println(eid+ "-------------------");
	serc = db1.searchf(eid);
	//empid fname lname street city zip dept ofhrs instrctd cinstrct
	//String s1=context.
	c2.empid=db1.v5;
	c2.fname=db1.v2;
	c2.lname=db1.v3;
	c2.street=db1.v4;
	c2.city=db1.v6;
	c2.zip=db1.v11;
	c2.dept=db1.v8;
	c2.ofhrs=db1.v9;
	c2.instrctd=db1.v10;
	c2.cinstrct=db1.v12;
	String a=c2.empid+"/"+c2.fname+"/"+c2.lname+"/"+c2.street +"/"+c2.city+"/"+c2.zip+"/"+c2.dept+"/"+c2.ofhrs+"/"+c2.instrctd+"/"+c2.cinstrct;
	
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
