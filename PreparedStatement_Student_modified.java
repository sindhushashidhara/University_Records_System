package edu.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import connection.Student;
import connection.ConnectionPool;
import connection.course;
import connection.DatabaseConnection;
import connection.faculty;
import connection.Service;

public class PreparedStatement_Student_modified implements IDao {
	
	Connection conn = null;
	Statement stmt2 = null;
	ResultSet rs = null;
	java.sql.PreparedStatement stmt = null;
	boolean isPoolingUsed = true;
	static boolean  isObjectCachingUsed = true;
	static boolean  isCacheUpdated = false;
	
	public static HashMap<String,InstructorCacheRecord> instructorCache = new HashMap<String,InstructorCacheRecord>();
	
	public PreparedStatement_Student_modified()
	{	
		getConnectionFromPool();
		if(!isCacheUpdated)
			loadInstructorRecordCache();
	}
	
	private void getSingleConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/univesitydb", "root", "rushi");
			stmt2 = conn.createStatement();

			if (!conn.isClosed())
				System.out.println("Successfully connectiod");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void getConnectionFromPool()
	{
			
		try {
			
				conn = ConnectionPool.getConnectionInstanceFromPool();
				System.out.println(conn);
				if(conn != null)
				{
					stmt2 = conn.createStatement();
					isPoolingUsed = true;
					if (!conn.isClosed())
						System.out.println("Successfully connectiod");
				}
				else
				{
					System.out.println("Connection Pool Threshold");
				}
			} catch (SQLException e) 
			{
			e.printStackTrace();
			}
	}
	
		
	public String adds(int sid,String fname,String lname,String street,String city,String state,int zip, String dept, String ccmpleted,String ctaken)
	{
		
					
		String result = "";
		int rowcount = 0;
		int second = 0;

		try {
			
			String query = "Insert into student (SJSUID,FName,LName,Street,City,State,Zip,Department,CCompleted,CEnrolled) values (?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, SJSUID);
			stmt.setString(2, FName);
			stmt.setString(3, LName);
			stmt.setString(4, Street);
			stmt.setString(5, City);
			stmt.setString(6, State);
			stmt.setString(7, Zip);
			stmt.setString(8, Department);
			stmt.setString(9, CCompleted);
			stmt.setString(10,CEnrolled);
			
			
			rowcount = stmt.executeUpdate();
			
			query = "Insert into student (SJSUID,FName,LName,Street,City,State,Zip,Department,CCompleted,CEnrolled) values (?,?,?,?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, SJSUID);
			stmt.setString(2, I.getfname());
			stmt.setString(3, I.getlname());
			stmt.setString(4, I.getStreet());
			stmt.setString(5, I.getCity());
			stmt.setString(6, I.getState());
			stmt.setString(7, I.getZip();
			stmt.setString(8, I.getDepartment());
			stmt.setString(9, I.getCCompleted());
			stmt.setString(10, I.getCEnrolled());
			
			second = stmt.executeUpdate();
			
			if (rowcount > 0 && second >0) {
				result = "true";
				System.out.println("Instructor added successful");
				
				if(isObjectCachingUsed)
				{
						// Adding entry to cache
						InstructorCacheRecord_modified record = new InstructorCacheRecord_modified();
						record.setFName(I.getfname());
						record.setLName(I.getlname());
						record.setStreet(I.getStreet());
						record.setCity(I.getCity());
						record.setState(I.getState());
						record.setZip(I.getZip());
						record.setDepartment(I.getDepartment());
						record.setOfHrs(I.getOfHrs());
						record.setCInstructed(I.getCCompleted());
						record.setCCourses(I.getCEnrolled());
						
						System.out.println("Stoing value in cache");
						instructorCache.put(SJSUID, record);
				}
				
			} else {
				result = "false:The data could not be inserted in the databse";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("exception");
			result = "false:The data could not be inserted in the databse due to SQL exception";
			e.printStackTrace();
		}
		
		// return connection instance to the pool
		if(isPoolingUsed)	
			ConnectionPool.returnConnectionInstanceToPool();
				
		return result;
		
		// TODO Auto-generated method stub
	}

	
	public String delf(int sid)
	{
		// TODO Auto-generated method stub
		
		
		
		
		System.out.println("Instructor Deleted Successfully");
		
		// return connection instance to the pool
				if(isPoolingUsed)	
					ConnectionPool.returnConnectionInstanceToPool();
		
				if(isObjectCachingUsed)
				//Remove the entry from cache
				instructorCache.remove(sid);
				
		return pdao.delete(p);		
		
	}

	
	public String searchf(int sid) 
	{	String result = "";
		boolean success = false;
		if(isObjectCachingUsed && isCacheUpdated)
		{	
			System.out.println("Getting entries from cache");
			for(String key: instructorCache.keySet() )
			{success = true;
				result += key+ "/"+instructorCache.get(key).getfname()+ "/"+ instructorCache.get(key).getlname()+ "/"+ 
						instructorCache.get(key).getstreet()+ "/"+ instructorCache.get(key).getCity() + "/"+ instructorCache.get(key).getState() + 
					"/"+ instructorCache.get(key).getZip()+"/"+ instructorCache.get(key).getDepartment()+"/"+ instructorCache.get(key).getCCompleted()+"/"+ instructorCache.get(key).getCCourses();
				result += "!";  
			}
			// return connection instance to the pool
			if(isPoolingUsed)	
				ConnectionPool.returnConnectionInstanceToPool();
			if (success)
			return result;	
			else
				return "false: No record found";
		}
		else
		{
				// TODO Auto-generated method stub
			
				boolean success1 = false;
				
				try {
					String query = "SELECT * from student where SJSUID like '%"+sid+"%'" ";
					stmt = conn.prepareStatement(query);
					
					//rs = stmt.executeQuery(query);
					rs = stmt.executeQuery();
					
					while(rs.next())
					{	success1 = true;
						System.out.println(rs.getString("sid")+ " "+rs.getString("fname")+ " "+ rs.getString("lname")+ " "+ rs.getString("street")+ " "+ rs.getString("city") + " "+ rs.getString("state") + 
								" "+ rs.getString("zip")+" "+ rs.getString("dept")+ " "+ rs.getString("ccmpleted")+" "+ rs.getString("ctaken"));
						result += rs.getString("eid")+ "/"+rs.getString("fname")+ "/"+ rs.getString("lname")+ "/"+ 
								rs.getString("street")+ "/"+ rs.getString("city") + "/"+ rs.getString("state") + 
							"/"+ rs.getString("zip")+"/"+ rs.getString("dept")+"/"+ rs.getString("ccmpleted")+"/"+ rs.getString("ctaken");
						result += "!";
						
						//update cache
						if(isObjectCachingUsed)
						{
							InstructorCacheRecord record = new InstructorCacheRecord();
							try {
								record.setFirstName(rs.getString("firstName"));
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							record.setFName(I.getfname());
							record.setLName(I.getlname());
							record.setStreet(I.getStreet());
							record.setCity(I.getCity());
							record.setState(I.getState());
							record.setZip(I.getZip());
							record.setDepartment(I.getDept());
							record.setOfHrs(I.getccmpleted());
							record.setCInstructed(I.getctaken());
							
							instructorCache.put(rs.getString("sid"), record);
						}
					}
					
					
				} catch (SQLException e) {
					e.printStackTrace();
					return "false: SQL Exception occured";
				}
				
				// We cannot return connection on cache load
				// return connection instance to the pool
						if(isPoolingUsed && isCacheUpdated)	
							ConnectionPool.returnConnectionInstanceToPool();
				
						
						
				//return rs.toString();
						if(success1)
							return result;
						else
							return "false:No Records found";
		}
	}

		
	
	
	public String update(int sid,String fname,String lname,String street,String city,String state,int zip, String dept, String ccmpleted,String ctaken) {
	
				
		try {
				
				String query = "update student set FName = '"+fname+"',LName = '"+lname+"',Street = '"+street+"',City = '"+city+"',State = '"+state+"',ZIP = '"+zip+"',Department = '"+dept+"',CCompleted='"+ccmpleted+"',CEnrolled='"+ctaken+"' where SJSUID='"+sid+"'" ;
				stmt = conn.prepareStatement(query);
				stmt.setString(1, SJSUID);
			stmt.setString(2, I.getfname());
			stmt.setString(3, I.getlname());
			stmt.setString(4, I.getStreet());
			stmt.setString(5, I.getCity());
			stmt.setString(6, I.getState());
			stmt.setString(7, I.getZip();
			stmt.setString(8, I.getDepartment());
			stmt.setString(9, I.getCCompleted());
			stmt.setString(10, I.getCEnrolled());
				
							
				res1 = stmt.executeUpdate();
				
				
				
				// updating contents of Cache
				if(isObjectCachingUsed)
				{
					InstructorCacheRecord record = new InstructorCacheRecord();
				
					record.setFName(I.getfname());
						record.setFName(I.getfname());
							record.setLName(I.getlname());
							record.setStreet(I.getStreet());
							record.setCity(I.getCity());
							record.setState(I.getState());
							record.setZip(I.getZip());
							record.setDepartment(I.getDept());
							record.setOfHrs(I.getccmpleted());
							record.setCInstructed(I.getctaken());
					instructorCache.put(i.getSJSUID(), record);
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(isPoolingUsed)	
			ConnectionPool.returnConnectionInstanceToPool();
		
			return String.valueOf(res1);
			
		
	}
	
		
			
		public String login(int id, String pwd){
			String result = "";
			String query = "SELECT * from logintable where SJSUID='"+id+"' and Password=AES_ENCRYPT('"+pwd+"', 'mykey013');
			try {
				
					ResultSet rs1 = stmt2.executeQuery(query);
					while(rs1.next()){
						if(password.equals(rs1.getString("password"))){
							result = "Login successful!";
						}
						else
							result = "Login failed!";
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
}
