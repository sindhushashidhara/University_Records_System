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

public class InstructorDaoImpl implements IDao {
	
	Connection conn = null;
	Statement stmt2 = null;
	ResultSet rs = null;
	java.sql.PreparedStatement stmt = null;
	boolean isPoolingUsed = true;
	static boolean  isObjectCachingUsed = true;
	static boolean  isCacheUpdated = false;
	
	public static HashMap<String,InstructorCacheRecord> instructorCache = new HashMap<String,InstructorCacheRecord>();
	
	public InstructorDaoImpl()
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
	
	private void loadInstructorRecordCache()
	{
		// Loads the cache with the current database 
		System.out.println("Loading cache from DB");
		findAll(); 
		isCacheUpdated = true;
	}
	
	
	public String add(Object object)
	{
		
		Instructor I = (Instructor) object;
		String instructorId = I.getInstructorEmpId();
		String department = I.getDepartment();
	//	DateFormat officeHrs = I.getOfficeHours();
		int personId = I.getPersonId();
		
		String result = "";
		int rowcount = 0;
		int second = 0;

		try {
			/*String query = "Insert into instructor (instructorId,department,personId) values"
					+ " ('"
					+ instructorId
					+ "', '"
					+ department
					+ "', '"
				    + personId + "')";*/
			String query = "Insert into instructor (instructorId,department,personId) values (?,?,?)";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, instructorId);
			stmt.setString(2, department);
			stmt.setInt(3, personId);
			
			//rowcount = stmt.executeUpdate(query);
			rowcount = stmt.executeUpdate();
			//query = "Insert into instructortiming (instructorId, day, time) values ('"+instructorId+"','"+I.getDays()+"','"+I.getTiming()+"')";
			query = "Insert into instructortiming (instructorId, day, time) values (?, ? ,?)";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, instructorId);
			stmt.setString(2, I.getDays());
			stmt.setString(3, I.getTiming());
			second = stmt.executeUpdate();
			//second = stmt.executeUpdate(query);
			if (rowcount > 0 && second >0) {
				result = "true";
				System.out.println("Instructor added successful");
				
				if(isObjectCachingUsed)
				{
						// Adding entry to cache
						InstructorCacheRecord record = new InstructorCacheRecord();
						record.setFirstName(I.getFirstName());
						record.setLastName(I.getLastName());
						record.setAddress(I.getAddress());
						record.setCity(I.getCity());
						record.setDepartment(I.getDepartment());
						record.setState(I.getState());
						record.setZipCode(I.getZipCode());
						record.setDays(I.getDays());
						record.setTiming(I.getTiming());
						
						System.out.println("Stoing value in cache");
						instructorCache.put(instructorId, record);
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

	
	public String delete(Object object)
	{
		// TODO Auto-generated method stub
		
		Instructor I = (Instructor) object;
		String  instructorId = I.getInstructorEmpId();
		
		PersonDaoImpl pdao = new PersonDaoImpl();
		Person p = new Person();
		
		p.setPersonId(getPersonIdForInstructor(instructorId));
		System.out.println("Instructor Deleted Successfully");
		
		// return connection instance to the pool
				if(isPoolingUsed)	
					ConnectionPool.returnConnectionInstanceToPool();
		
				if(isObjectCachingUsed)
				//Remove the entry from cache
				instructorCache.remove(instructorId);
				
		return pdao.delete(p);		
		
	}

	
	public int getPersonIdForInstructor(String instructorId)
	{
		
				int personId = 0;
				ResultSet rs;
		
				try {
					/*String query = "Select personId from instructor where instructorId =" + "'"
							+ instructorId + "'"; */
					String query = "Select personId from instructor where instructorId =?";
					stmt = conn.prepareStatement(query);
					stmt.setString(1, instructorId);
					
					//rs = stmt.executeQuery(query);
					rs = stmt.executeQuery();
					while(rs.next())
					{
						personId =	rs.getInt(1);
						System.out.println(personId);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				// return connection instance to the pool
						if(isPoolingUsed)	
							ConnectionPool.returnConnectionInstanceToPool();
						
				return personId;
		
	}

	
	public String findById(Object object) 
	{
		
		// TODO Auto-generated method stub
				
		Instructor I = (Instructor) object;
		String  instructorId = I.getInstructorEmpId();
		boolean found = false;
		String result="";
	
		if(isObjectCachingUsed == true && instructorCache.get(instructorId) != null)
		{	System.out.println("Cache hit");
			InstructorCacheRecord r = instructorCache.get(instructorId);
			result =( r.getFirstName() + "/"+ r.getLastName()+ "/"+ r.getAddress() +"/"+ r.getCity() + "/"+ r.getState()  + 
					"/"+ r.getZipCode()+"/"+ r.getDays()+"/"+ r.getTiming());
			return result;
		}
		else
		{	System.out.println("Cache not hit");
				try {
					//String query = "Select * from 	person  where personId = (Select personId from instructor where instructorId =" + "'"
					//		+ instructorId + "')";
					/*String query = "Select * from 	person INNER JOIN instructor  ON  person.personId = instructor.personId INNER JOIN instructortiming ON instructor.instructorId = instructortiming.instructorId"
							+" and instructor.instructorId = '"+instructorId+"'";*/
					String query = "Select * from 	person INNER JOIN instructor  ON  person.personId = instructor.personId INNER JOIN instructortiming ON instructor.instructorId = instructortiming.instructorId"
							+" and instructor.instructorId = ?";
					stmt = conn.prepareStatement(query);
					stmt.setString(1, instructorId);
					
					//rs = stmt.executeQuery(query);
					rs = stmt.executeQuery();
					
					while(rs.next())
					{
						found = true;
						System.out.println("name:"+ rs.getString("firstName")+ " "+ rs.getString("lastName") );
						result =(rs.getString("firstName")+ "/"+ rs.getString("lastName")+ "/"+ rs.getString("address")+ "/"+ rs.getString("city") + "/"+ rs.getString("state") + 
								"/"+ rs.getString("zipCode")+"/"+ rs.getString("day")+"/"+ rs.getString("time"));
		
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
				// return connection instance to the pool
						if(isPoolingUsed)	
							ConnectionPool.returnConnectionInstanceToPool();
						
					if(found)	
						//return rs.toString();
						return result;
					else
						return "false:Not Found";
		}
		
	}

	
	public String find(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String findAll() 
	{	String result = "";
		boolean success = false;
		if(isObjectCachingUsed && isCacheUpdated)
		{	
			System.out.println("Getting entries from cache");
			for(String key: instructorCache.keySet() )
			{success = true;
				result += key+ "/"+instructorCache.get(key).getFirstName()+ "/"+ instructorCache.get(key).getLastName()+ "/"+ 
						instructorCache.get(key).getAddress()+ "/"+ instructorCache.get(key).getCity() + "/"+ instructorCache.get(key).getState() + 
					"/"+ instructorCache.get(key).getZipCode()+"/"+ instructorCache.get(key).getDepartment()+"/"+ instructorCache.get(key).getDays()+"/"+ instructorCache.get(key).getTiming();
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
					String query = "Select * from 	person INNER JOIN instructor  ON  person.personId = instructor.personId INNER JOIN instructortiming ON instructor.instructorId = instructortiming.instructorId";
					stmt = conn.prepareStatement(query);
					
					//rs = stmt.executeQuery(query);
					rs = stmt.executeQuery();
					
					while(rs.next())
					{	success1 = true;
						System.out.println(rs.getString("instructorId")+ " "+rs.getString("firstName")+ " "+ rs.getString("lastName")+ " "+ rs.getString("address")+ " "+ rs.getString("city") + " "+ rs.getString("state") + 
								" "+ rs.getString("zipCode")+" "+ rs.getString("department")+ " "+ rs.getString("day")+" "+ rs.getString("time"));
						result += rs.getString("instructorId")+ "/"+rs.getString("firstName")+ "/"+ rs.getString("lastName")+ "/"+ 
								rs.getString("address")+ "/"+ rs.getString("city") + "/"+ rs.getString("state") + 
							"/"+ rs.getString("zipCode")+"/"+ rs.getString("department")+"/"+ rs.getString("day")+"/"+ rs.getString("time");
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
							record.setFirstName(rs.getString("firstName"));
							record.setLastName(rs.getString("lastName"));
							record.setAddress(rs.getString("address"));
							record.setCity(rs.getString("city"));
							record.setDepartment(rs.getString("department"));
							record.setState(rs.getString("state"));
							record.setZipCode(rs.getString("zipCode"));
							record.setDays(rs.getString("day"));
							record.setTiming(rs.getString("time"));
							instructorCache.put(rs.getString("instructorId"), record);
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

	public String getAssignedCoursesForInstructor(String instructorId)
	{
		boolean success = false;
		String result="";
		try {
				
			/*	String query = "Select * from 	course INNER JOIN courseInstructorMap  ON  (course.courseId = courseInstructorMap.courseId and course.section = courseInstructorMap.section)" +
						" INNER JOIN coursetiming ON  (courseInstructorMap.courseId = coursetiming.courseId and coursetiming.section = courseInstructorMap.section)AND courseInstructorMap.instructorId= '"+instructorId+"'"; */
				
				String query = "Select * from 	course INNER JOIN courseInstructorMap  ON  (course.courseId = courseInstructorMap.courseId and course.section = courseInstructorMap.section)" +
						" INNER JOIN coursetiming ON  (courseInstructorMap.courseId = coursetiming.courseId and coursetiming.section = courseInstructorMap.section)AND courseInstructorMap.instructorId= ?";
				stmt = conn.prepareStatement(query);
				stmt.setString(1, instructorId);
				
				//rs = stmt.executeQuery(query);
				rs = stmt.executeQuery();
				
				while(rs.next())
				{success = true;
					System.out.println(rs.getString("instructorId")+ " "+rs.getString("courseId")+ " "+ rs.getString("courseName")+ " "+ rs.getString("location")+ " "+ rs.getString("day")+ " "+ rs.getString("time"));
					result += instructorId+ "/"+rs.getString("courseId")+ "/"+ rs.getString("courseName")+ "/"+ 
							rs.getString("location")+ "/"+ rs.getString("day") + "/"+ rs.getString("time") ;
					result += "!";
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "false: SQL Exception occured";
		}
		
		// return connection instance to the pool
				if(isPoolingUsed)	
					ConnectionPool.returnConnectionInstanceToPool();
				
				if(success)
					return result;
				else
					return "false: No Records found";
	}
	
	public String assignInstructor(String courseId,  String section,
			String instructorId)
	{
		String result = "";
		int rowcount;

		try {
		/*	String query = "Insert into courseInstructorMap (courseId, section, instructorId) values"
					+ " ("
					+ "(select courseId from course where courseId ='" +courseId+"' and section = '" +section+"')"
					+ ", "
					+ "(select section from course where courseId ='" +courseId+"' and section ='" +section+"')"
					+ ", "
					+ "(select instructorId from instructor where instructorId ='" +instructorId+"')"
					+ ")"; */
			String query = "Insert into courseInstructorMap (courseId, section, instructorId) values"
					+ " ("
					+ "(select courseId from course where courseId = ? and section = ?)"
					+ ", "
					+ "(select section from course where courseId = ? and section = ?)"
					+ ", "
					+ "(select instructorId from instructor where instructorId = ?)"
					+ ")";
			
			stmt = conn.prepareStatement(query);
			stmt.setString(1, courseId);
			stmt.setString(2, section);
			stmt.setString(3, courseId);
			stmt.setString(4, section);
			stmt.setString(5, instructorId);
			
			//rowcount = stmt.executeUpdate(query);
			rowcount = stmt.executeUpdate();
			if (rowcount > 0) {
				result = "true";
				System.out.println("Instructor assigned successful");
			} else {
				result = "false:The data could not be inserted in the databse";
			}
		} catch (SQLException e) {
			result = "false:The data could not be inserted in the databse due to SQL Exception";
			e.printStackTrace();
		}

		// return connection instance to the pool
				if(isPoolingUsed)	
					ConnectionPool.returnConnectionInstanceToPool();
		return result;
	}
	
	public String unAssignInstructor(String instructorId, String courseId, String section)
	{
		String result = "";
		int rowcount;

		try {
		/*	String query = "Delete from courseInstructorMap where instructorId =" + "'"
					+ instructorId + "' and courseId ='"+courseId+"' and section = '"+section+"'"; */
			String query = "Delete from courseInstructorMap where instructorId =? " 
					+ " and courseId = ? and section = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, instructorId);
			stmt.setString(2, courseId);
			stmt.setString(3, section);
			
			//rowcount = stmt.executeUpdate(query);
			rowcount = stmt.executeUpdate();
			
			if (rowcount > 0) {
				result = "true";
				System.out.println("Instructor unassigned successful");
			} else {
				result = "false:The data could not be deleted in the databse";
			}
		} catch (SQLException e) {
			result = "false:The data could not be deleted in the databse due to SQL exception";
			e.printStackTrace();
		}
		
		// return connection instance to the pool
				if(isPoolingUsed)	
					ConnectionPool.returnConnectionInstanceToPool();
		return result;
	}

	
	public String update(Object object) {
	
		Instructor i = (Instructor) object;
		int res1=0, res2 = 0;
		PersonDaoImpl pimpl = new PersonDaoImpl();
		Person p = new Person();
		p.setAddress(i.getAddress());
		p.setCity(i.getCity());
		p.setFirstName(i.getFirstName());
		p.setLastName(i.getLastName());
		p.setState(i.getState());
		p.setZipCode(i.getZipCode());
		p.setPersonId(i.getPersonId());
		pimpl.update(p);
		
		try {
				//String query = "Update instructor set department = '"+i.getDepartment()+"' where instructorId=" + i.getInstructorEmpId();
				String query = "Update instructor set department = ? where instructorId= ?" ;
				stmt = conn.prepareStatement(query);
				stmt.setString(1, i.getDepartment());
				stmt.setString(2,  i.getInstructorEmpId());
				//res = stmt.executeUpdate(query);
				res1 = stmt.executeUpdate();
				//query = "Update instructortiming set day = '"+i.getDays()+"', time = '"+i.getTiming()+"' where instructorId=" + i.getInstructorEmpId();
				query = "Update instructortiming set day = ?, time = ? where instructorId=?";
				stmt = conn.prepareStatement(query);
				stmt.setString(1, i.getDays());
				stmt.setString(2,  i.getTiming());
				stmt.setString(3,  i.getInstructorEmpId());
				//res = stmt.executeUpdate(query);
				res2 = stmt.executeUpdate();
				
				// updating contents of Cache
				if(isObjectCachingUsed)
				{
					InstructorCacheRecord record = new InstructorCacheRecord();
				
					record.setFirstName(i.getFirstName());
					record.setLastName(i.getLastName());
					record.setAddress(i.getAddress());
					record.setCity(i.getCity());
					record.setDepartment(i.getDepartment());
					record.setState(i.getState());
					record.setZipCode(i.getZipCode());
					record.setDays(i.getDays());
					record.setTiming(i.getTiming());
					instructorCache.put(i.getInstructorEmpId(), record);
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(isPoolingUsed)	
			ConnectionPool.returnConnectionInstanceToPool();
		
		if(res2 == 1 && res2 == 1){
			System.out.println("Data Updated");
			return String.valueOf(res1);
			
		}
		else
			return "false: Data not Updated";
	}
	
		
	public String search(String columnName, String keyword) {
		String result = "";
		boolean success = false;
		try {
			String query = "SELECT p.firstName,p.lastName,p.address,p.city,p.state,p.zipCode,i.instructorId,i.department,it.day,it.time FROM person AS p left JOIN instructor AS i ON p.personId = i.personId left JOIN instructortiming AS it ON i.instructorId = it.instructorId where "
					+ columnName + " LIKE " + "'%" +keyword+"%'";
			rs = stmt2.executeQuery(query);

			while (rs.next()) {
				success = true;
				System.out.println(rs.getString("instructorId") + " "
						+ rs.getString("firstName") + " "
						+ rs.getString("lastName") + " "
						+ rs.getString("address") + " " + rs.getString("city")
						+ " " + rs.getString("state") + " "
						+ rs.getString("zipCode") + " "
						+ rs.getString("department") + " "
						+ rs.getString("day") + " " + rs.getString("time"));
				result += rs.getString("instructorId") + "/"
						+ rs.getString("firstName") + "/"
						+ rs.getString("lastName") + "/"
						+ rs.getString("address") + "/" + rs.getString("city")
						+ "/" + rs.getString("state") + "/"
						+ rs.getString("zipCode") + "/"
						+ rs.getString("department") + "/"
						+ rs.getString("day") + "/" + rs.getString("time");
				result += "!";

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// return connection instance to the pool
		if (isPoolingUsed)
			ConnectionPool.returnConnectionInstanceToPool();

	if(success)
			return result;
		else
			return "false: No Records found";
	}
		
		public String login(String id,String password){
			String result = "";
			String query = "Select personId from instructor where instructorId='" + id + "'";
			try {
				rs = stmt2.executeQuery(query);
				if(!rs.first()){
					result = "Please enter a valid instructor id.";
				}
				else
				{
					query = "Select password from person where personId='" + rs.getString("personId") +"'";
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
