package com.nyu.wds;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class WDSTest implements RequestHandler<WDSTestRequest, WDSTestResponse>{
	Connection con = null;
	public WDSTestResponse handleRequest(WDSTestRequest input, Context context) {
		
		 try {			
			 if(con == null) {
				 System.out.println("conn null");
			   con = DriverManager.getConnection(
			     "jdbc:mysql://wds.crpjfc57axcp.us-east-2.rds.amazonaws.com/wds", "asif", "asif54321");
			 }
			 
			 CallableStatement statement = con.prepareCall("{call get_customer_details(11111716)}");
			 
			// setting input parameters on the statement object
			// statement.setString(parameterIndex, parameterValue);
			 
			 statement.execute(); 
			 ResultSet resultSet = statement.getResultSet();
			 while (resultSet.next()) {
			        // retrieve values of fields
			String fName = resultSet.getString("first_name");
			System.out.println(fName);
			    }
			 
//			   context.getLogger().log("Test Started");
//			   String query = input.getQuery();
//			   Statement statement = con.createStatement();
//			   //System.out.println(statement.execute(query));
//			   ResultSet resultSet = statement.executeQuery(query); 
//			   System.out.println(resultSet.toString());
//			   if (resultSet.next()) {
//			    return resultSet.toString();
//			   }
			  } catch (SQLException e) {
			   e.printStackTrace();
			  }

			  return null;
	}

}
class WDSTestRequest{
	String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public WDSTestRequest(String query) {
		super();
		this.query = query;
	}

	public WDSTestRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "WDSTestRequest [query=" + query + "]";
	}
	
}
class WDSTestResponse{
	String firstName;
	String lastName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public WDSTestResponse(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public WDSTestResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WDSTestResponse [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}