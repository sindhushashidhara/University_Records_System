package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.*;



//import java.sql.*;
//import com.mysql.jdbc.Driver;

class DatabaseConnection extends course
{
	Connection conn=null;
	static ResultSet rs,rs1,rs2,rs3,rs4;
	Statement stm=null;
	String v1,v2,v3,v4,v6,v7,v8,v9,v10,v12;
	int v5,v11;
	public DatabaseConnection()
	{
		try    
        {   
            Class.forName("com.mysql.jdbc.Driver").newInstance();                            
        }   
        catch (Exception E)    
        {   
            System.err.println("Exception while loading driver");   
            E.printStackTrace();   
        }   
           
        try   
        {           
            ConnectionPool cp = new ConnectionPool("jdbc:mysql://127.0.0.1:3306/universityrecordsystem","root","sa1234");   
           conn=cp.getConnection();
            // conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/universityrecordsystem","root","sa1234");
			System.out.println("constructor");
			stm=conn.createStatement();
			if(!conn.isClosed())
			{
				System.out.println("connection ok");
			}
           // Connection[] connArr = new Connection[100];   
           
               
              //  connArr[i] = cp.checkout();   
                //System.out.println("Checking out..." + connArr[i]);   
                //System.out.println("Available Connections ... " + cp.availableCount());   
                               
  
            //for(int i=0; i<connArr.length;i++)   
            //{   
              //  cp.checkin(connArr[i]);   
                //System.out.println("Checked in..." + connArr[i]);   
                //System.out.println("Available Connections ... " + cp.availableCount());   
            //}   
        }   
        catch(SQLException sqle)   
        {   
            sqle.printStackTrace();   
        }   
        catch(Exception e)   
        {   
            e.printStackTrace();   
        }           
		
		/*try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("11111");	
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/universityrecordsystem","root","sa1234");
			System.out.println("constructor");
			stm=conn.createStatement();
			if(!conn.isClosed())
			{
			//	System.out.println("connection ok");
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}*/
	}
	
	/*public String signUp(String userName, String pwd)
	{
		String result="";
		int rowcount;
		try{
			String querry="Insert into customer (username,password) values ('" +userName+"','"+pwd+"') ";
			
			rowcount=stm.executeUpdate(querry);
			if(rowcount>0)
			{
				result="true";
			//	System.out.println("inserted");
			}
			else
			{
				result="false";
			}
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return result;
	}*/

public String login1(int id, String pwd)
{
	System.out.println(id);
	//System.out.println(pwd);
	String result;
	//int rowcount;
	try{
		String querry="SELECT * from logintable where SJSUID='"+id+"' and Password=AES_ENCRYPT('"+pwd+"', 'mykey013')";
		ResultSet rs=stm.executeQuery(querry);
		if(rs.next())
		{
			
			result=rs.getString("Identifier");

		}
		else 
			{
			
				String querry3="SELECT * from admin where adminid='"+id+"' and password=AES_ENCRYPT('"+pwd+"','mykey015')";
				System.out.println(querry3);
				ResultSet rs3=stm.executeQuery(querry3);
				if(rs3.next())
				{
					System.out.println(rs3.getString("name"));
					result="3";

				}
				else
				{
					System.out.println("error");
					result="e";
				}
			}
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"e";
	}
}

public String time1(int id)
{
	//System.out.println(userName);
	//System.out.println(pwd);
	String result=null;
	int rowcount=0;
	try{
		
		String querry2="SELECT log from log where id='"+id+"'";
		//System.out.println(querry2);
		ResultSet rs=stm.executeQuery(querry2);
		if(rs.next())
		{
		result=rs.getString("log");
		//System.out.println(result+"----");
		}
		String querry1="INSERT INTO log (id,log) VALUES ('"+id+"',NOW()) ON DUPLICATE KEY UPDATE log=NOW()";
		rowcount=stm.executeUpdate(querry1);
		if(rowcount>0)
		{
			
			//result=rs.getString("log");
		//	System.out.println("updated");
		}
				
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"e";
	}
}



public String addc(String cid,String cname, String ctime, String cloc, int empid)
{
	//System.out.println(userName);
	//System.out.println(pwd);
	String result="false";
	int rowcount=0;
	try{
		
		
		String querry1="INSERT INTO course (CourseID,CourseName,Insttime,Location,EmpID) VALUES ('"+cid+"','"+cname+"','"+ctime+"','"+cloc+"','"+empid+"')";
		rowcount=stm.executeUpdate(querry1);
		if(rowcount>0)
		{
			
			result="true";
		System.out.println("updated");
		}
				
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"false";
	}
}


public String delc(String cid)
{
	//System.out.println(userName);
	//System.out.println(pwd);
	String result="false";
	int rowcount=0;
	try{
		
		
		String querry1="delete from course where CourseID='"+cid+"';";
		rowcount=stm.executeUpdate(querry1);
		if(rowcount>0)
		{
			
			result="true";
		System.out.println("updated");
		}
				
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"false";
	}
}


public String updatec(String cid,String cname, String ctime, String cloc, int empid)
{
	//System.out.println(userName);
	//System.out.println(pwd);
	String result="false";
	int rowcount=0;
	try{
		String querry1="update course set CourseName = '"+cname+"',Insttime='"+ctime+"',Location='"+cloc+"',EmpID='"+empid+"' where CourseID='"+cid+"'";
		rowcount=stm.executeUpdate(querry1);
		if(rowcount>0)
		{
			
			result="true";
		System.out.println("updated");
		}
				
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"false";
	}
}

public String searchc(String id)
{
	//System.out.println(userName);
	//System.out.println(pwd);
	String result=null;
	
	//int rowcount=0;
	try{
		
			String querry2="SELECT * from course where CourseID like '%"+id+"%'" ;
			System.out.println(querry2);
			ResultSet rs=stm.executeQuery(querry2);
			if(rs.next())
			{
				
				
				v1=rs.getString("CourseID");
				v2=rs.getString("CourseName");
				v3=rs.getString("Insttime");
				v4=rs.getString("Location");
				v5=rs.getInt("EmpID");
				
				result="true";
			//System.out.println(result+"----");
			}	
			else
			{
				result="e";
			}
		
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"e";
	}
}

public String searchc(String id, String name)
{
	//System.out.println(userName);
	//System.out.println(pwd);
	String result=null;
	
	//int rowcount=0;
	try{
		
		if(id.equalsIgnoreCase(null)|| id.equalsIgnoreCase("") )
		{
		String querry2="SELECT * from course where CourseName like '%"+name+"%'" ;
		System.out.println(querry2);
		ResultSet rs=stm.executeQuery(querry2);
		if(rs.next())
		{
			v1=rs.getString("CourseID");
			v2=rs.getString("CourseName");
			v3=rs.getString("Insttime");
			v4=rs.getString("Location");
			v5=rs.getInt("EmpID");
			result="true";
		//System.out.println(result+"----");
		}
		}
		else if(name.equalsIgnoreCase(null)|| name.equalsIgnoreCase("") )
		{
			String querry2="SELECT * from course where CourseID like '%"+id+"%'" ;
			System.out.println(querry2);
			ResultSet rs=stm.executeQuery(querry2);
			if(rs.next())
			{
				
				
				v1=rs.getString("CourseID");
				v2=rs.getString("CourseName");
				v3=rs.getString("Insttime");
				v4=rs.getString("Location");
				v5=rs.getInt("EmpID");
				
				result="true";
			//System.out.println(result+"----");
			}	
			else
			{
				result="e";
			}
		}
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"e";
	}
}











public String addf(int eid,String fname,String lname,String street,String city,String state,int zip, String dept, String ofhrs, String instrctd,String cinstrct)
{
	//System.out.println(use
	
	//rName);
	//System.out.println(pwd);
	String result="false";
	int rowcount=0;
	try{
		
		
		String querry1="INSERT INTO instructor (EmpID,FName,LName,Street,City,State,Zip,Department,OfHrs,CInstructed,CCourses) VALUES ('"+eid+"','"+fname+"','"+lname+"','"+street+"','"+city+"','"+state+"','"+zip+"','"+dept+"','"+ofhrs+"','"+instrctd+"','"+cinstrct+"')";
		rowcount=stm.executeUpdate(querry1);
		if(rowcount>0)
		{
			
			result="true";
		System.out.println("updated");
		}
				
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"false";
	}
}


public String delf(int eid)
{
	//System.out.println(userName);
	//System.out.println(pwd);
	String result="false";
	int rowcount=0;
	try{
		
		
		String querry1="delete from instructor where EmpID='"+eid+"';";
		rowcount=stm.executeUpdate(querry1);
		if(rowcount>0)
		{
			
			result="true";
		System.out.println("updated");
		}
				
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"false";
	}
}


public String updatef(int eid,String fname,String lname,String street,String city,String state,int zip, String dept, String ofhrs, String instrctd,String cinstrct)
{
	//System.out.println(userName);
	//System.out.println(pwd);
	String result="false";
	int rowcount=0;
	try{
		String querry1="update instructor set FName = '"+fname+"',LName = '"+lname+"',Street = '"+street+"',City = '"+city+"',State = '"+state+"',ZIP = '"+zip+"',Department = '"+dept+"',OfHrs='"+ofhrs+"',CInstructed='"+instrctd+"',CCourses='"+cinstrct+"' where EmpID='"+eid+"'";
		rowcount=stm.executeUpdate(querry1);
		if(rowcount>0)
		{
			
			result="true";
		System.out.println("updated");
		}
				
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"false";
	}
}


public String searchf(int id)
{
	//System.out.println(userName);
	//System.out.println(pwd);
	String result=null;
	
	//int rowcount=0;
	try{
		
			String querry2="SELECT * from instructor where EmpID like '%"+id+"%'" ;
			System.out.println(querry2);
			ResultSet rs=stm.executeQuery(querry2);
			if(rs.next())
			{
			 //	='"+instrctd+"',='"+cinstrct+"' where EmpID='"+eid+
				v5=rs.getInt("EmpID");
				v2=rs.getString("FName");
				v3=rs.getString("LName");
				v4=rs.getString("City");
				v6=rs.getString("State");
				v11=rs.getInt("ZIP");
				v7=rs.getString("Department");
				v8=rs.getString("OfHrs");
				v9=rs.getString("CInstructed");
				v10=rs.getString("CCourses");
				
				result="true";
			//System.out.println(result+"----");
			}	
			else
			{
				result="e";
			}
		
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"e";
	}
}







public String adds(int sid,String fname,String lname,String street,String city,String state,int zip, String dept, String ccmpleted,String ctaken)
{
	//System.out.println(use
	
	//rName);
	//System.out.println(pwd);
	String result="false";
	int rowcount=0;
	try{
		
		
		String querry1="INSERT INTO student (SJSUID,FName,LName,Street,City,State,Zip,Department,CCompleted,CEnrolled) VALUES ('"+sid+"','"+fname+"','"+lname+"','"+street+"','"+city+"','"+state+"','"+zip+"','"+dept+"','"+ccmpleted+"','"+ctaken+"')";
		rowcount=stm.executeUpdate(querry1);
		if(rowcount>0)
		{
			
			result="true";
		System.out.println("updated");
		}
				
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"false";
	}
}


public String dels(int sid)
{
	//System.out.println(userName);
	//System.out.println(pwd);
	String result="false";
	int rowcount=0;
	try{
		
		
		String querry1="delete from student where SJSUID='"+sid+"';";
		rowcount=stm.executeUpdate(querry1);
		if(rowcount>0)
		{
			
			result="true";
		System.out.println("updated");
		}
				
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"false";
	}
}


public String updates(int sid,String fname,String lname,String street,String city,String state,int zip, String dept, String ccmpleted,String ctaken)
{
	//System.out.println(userName);
	//System.out.println(pwd);
	String result="false";
	int rowcount=0;
	try{
		String querry1="update student set FName = '"+fname+"',LName = '"+lname+"',Street = '"+street+"',City = '"+city+"',State = '"+state+"',ZIP = '"+zip+"',Department = '"+dept+"',CCompleted='"+ccmpleted+"',CEnrolled='"+ctaken+"' where SJSUID='"+sid+"'";
		rowcount=stm.executeUpdate(querry1);
		if(rowcount>0)
		{
			
			result="true";
		System.out.println("updated");
		}
				
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"false";
	}
}


public String searchs(int id)
{
	//System.out.println(userName);
	//System.out.println(pwd);
	String result=null;
	
	//int rowcount=0;
	try{
		
			String querry2="SELECT * from student where SJSUID like '%"+id+"%'" ;
			System.out.println(querry2);
			ResultSet rs=stm.executeQuery(querry2);
			if(rs.next())
			{
			 //	='"+instrctd+"',='"+cinstrct+"' where EmpID='"+eid+
				v5=rs.getInt("SJSUID");
				v2=rs.getString("FName");
				v3=rs.getString("LName");
				v4=rs.getString("City");
				v6=rs.getString("State");
				v11=rs.getInt("ZIP");
				v7=rs.getString("Department");
				v7=rs.getString("CCompleted");
				v7=rs.getString("CEnrolled");
				
				result="true";
			//System.out.println(result+"----");
			}	
			else
			{
				result="e";
			}
		
		return result;
	}catch (SQLException e)
	{
		e.printStackTrace();
		return"e";
	}
}


}
