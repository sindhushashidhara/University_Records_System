/**
 * Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package connection;

import javax.jws.WebService;



@WebService
public class Service {
	

	public String login(String user, String pwd)
	{
		
		DatabaseConnection db=new DatabaseConnection();
		String result;
		
		int id=Integer.parseInt(user);
		System.out.println(id);
		result = db.login1(id,pwd);
		System.out.println(result);
		return result;
	}
	
	public String time(String user)
	{
		
		DatabaseConnection db=new DatabaseConnection();
		String result;
		int id=Integer.parseInt(user);
		System.out.println("----------"+id);
		result = db.time1(id);
		
		return result;
	}
	
}
