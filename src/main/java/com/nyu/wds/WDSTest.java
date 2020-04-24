package com.nyu.wds;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.DBUtil;
import com.nyu.wds.util.JDBCConnection;

public class WDSTest implements RequestHandler<WDSTestRequest, WDSTestResponse>{
	Connection con = null;
	public WDSTestResponse handleRequest(WDSTestRequest input, Context context) {
		
		 try {		
			 con = JDBCConnection.getJDBCCOnnection(con, 0);
			 con.setAutoCommit(false);
			 System.out.println("close");

			 String query = "{call " + DBUtil.GET_USER_BY_USER_ID + "}";
			 query = query.replaceFirst("#", "11111716");
			 System.out.println("Query: "+ query);
			 CallableStatement statement = con.prepareCall(query);
			 
			 
			// setting input parameters on the statement object
			// statement.setString(parameterIndex, parameterValue);
			 
			 statement.execute(); 
			 ResultSet resultSet = statement.getResultSet();
			 System.out.println(resultSet.first());
			 while (resultSet.next()) {
			        // retrieve values of fields
				String fName = resultSet.getString("first_name");
				System.out.println(fName);
			  }
			 System.out.println("open");
			 con.setAutoCommit(true);
			 System.out.println("open");
			 con.close();
			 
//			   context.getLogger().log("Test Started");
//			   String query = input.getQuery();
//			   Statement statement = con.createStatement();
//			   //System.out.println(statement.execute(query));
//			   ResultSet resultSet = statement.executeQuery(query); 
//			   System.out.println(resultSet.toString());
//			   if (resultSet.next()) {
//			    return resultSet.toString();
//			   }
			  } catch (Exception e) {
			   e.printStackTrace();
			  }

			  return null;
	}

}
class WDSTestRequest{
	String query;
	Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public WDSTestRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "WDSTestRequest [query=" + query + ", date=" + date + "]";
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