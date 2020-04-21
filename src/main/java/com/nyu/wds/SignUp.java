package com.nyu.wds;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.dbmapper.User;
import com.nyu.wds.util.CommonUtil;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.JDBCConnection;
import com.nyu.wds.util.ResultSetObjectMapper;

public class SignUp implements RequestHandler<SignUpRequest, LoginResponse>{
	Connection con = null;
	@Override
	public LoginResponse handleRequest(SignUpRequest input, Context context) {
		if(input == null) {
			 return new LoginResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		}
		System.out.println("input : " + input);
		LoginResponse response = input.validateInput();
		if(response == null || response.getResponse() == null || response.getResponse().getCode() != Constant.SUCCESS) {
			return response;
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		 try {
			 String query = "{call " + Constant.GET_USER_BY_EMAIL_ID + "}";
			 System.out.println("Query: "+ query);
			 CallableStatement statement = con.prepareCall(query);
			 statement.setString(1, input.getEmailId());
			 boolean statementResultType = statement.execute();
			 System.out.println("statementResultType : "+statementResultType);
			 ResultSet resultSet = statement.getResultSet();
			 List<User> userList = ResultSetObjectMapper.mapRersultSetToObject(resultSet, User.class);
			 if(userList != null && userList.size() > 0) {
				 return new LoginResponse(new Response(Constant.EMAIL_ADDRESS_ALREADY_EXIST, Constant.EMAIL_ADDRESS_ALREADY_EXIST_MSG));
			 } 
			 statement.close();
			 
			 String updateQuery = "{call " + Constant.ADD_NEW_USER + "}";
			 CallableStatement statement1 = con.prepareCall(updateQuery);
			 Random rand = new Random(); 
			 int user_id = rand.nextInt(999999999);
			 while(!checkUniquenessOfUserId(user_id, con)) {
				 user_id = rand.nextInt(999999999);
			 }
			 
			 statement1.setInt(1,user_id);
			 statement1.setString(2,input.getFirstName());
			 statement1.setString(3,input.getLastName());
			 statement1.setString(4,input.getGender());
			 statement1.setString(5,input.getMarital_status());
			 statement1.setString(6,input.getSt_address());
			 statement1.setString(7,input.getCity());
			 statement1.setString(8,input.getState());
			 statement1.setString(9,input.getZipcode());
			 statement1.setString(10,input.getUser_type());
			 statement1.setString(11,input.getDesignation());
			 statement1.setString(12,input.getEmp_department());
			 statement1.setString(13,input.getEmailId());
			 statement1.setString(14,CommonUtil.hash256Calculator(input.getPassword()));
			 System.out.println(statement1.toString());
			 boolean statementResultType1 = statement1.execute();
			 System.out.println("statementResultType1 :" + statementResultType1 + "  "+ statement1.getUpdateCount());
			 statement1.close();
			 return new LoginResponse(new Response(Constant.SUCCESS, 
						Constant.SUCCESS_MSG), input.getFirstName()+" "+ input.getLastName(),user_id);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return new LoginResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
	}
	
	public boolean checkUniquenessOfUserId(int userId, Connection con) {
		 try {
			String query = "{call " + Constant.GET_USER_BY_USER_ID + "}";
			 System.out.println("Query: "+ query);
			 CallableStatement statement = con.prepareCall(query);
			 statement.setInt(1, userId);
			 statement.execute();
			 ResultSet resultSet = statement.getResultSet();
			 if(resultSet.first()) {
				 return false;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
