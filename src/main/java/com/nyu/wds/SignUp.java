package com.nyu.wds;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.dbmapper.User;
import com.nyu.wds.util.CommonUtil;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.DBUtil;
import com.nyu.wds.util.JDBCConnection;
import com.nyu.wds.util.ResultSetObjectMapper;

public class SignUp implements RequestHandler<SignUpRequest, LoginResponse>{
	Connection con = null;
	@Override
	public LoginResponse handleRequest(SignUpRequest input, Context context) {
		System.out.println("input : " + input);
		if (input == null || !CommonUtil.isValidString(input.getPassword())
				|| (CommonUtil.isValidString(input.getGender())
						&& !(input.getGender().equalsIgnoreCase("F") || input.getGender().equalsIgnoreCase("M")))
				|| !CommonUtil.isValidName(input.getFirstName()) || !CommonUtil.isValidName(input.getLastName())
				|| !CommonUtil.isValidString(input.getMarital_status())
				|| !(input.getMarital_status().equalsIgnoreCase("M") || input.getMarital_status().equalsIgnoreCase("S")
						||  input.getMarital_status().equalsIgnoreCase("W"))
				|| !CommonUtil.isValidString(input.getSt_address()) || !CommonUtil.isValidString(input.getCity())
				|| !CommonUtil.isValidString(input.getState()) || input.getState().length() != 2
				|| !CommonUtil.isValidString(input.getZipcode()) || input.getZipcode().length() != 5
				|| !CommonUtil.isValidString(input.getUser_type()) || (input.getUser_type().equalsIgnoreCase("E") && 
						(!CommonUtil.isValidString(input.getEmp_department()) || !CommonUtil.isValidString(input.getDesignation())))) {
			return new LoginResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		} else if (!CommonUtil.isValidEmail(input.getEmailId())) {
			return new LoginResponse(new Response(Constant.INVALID_EMAIL_ADDRESS, Constant.INVALID_EMAIL_ADDRESS_MSG));
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		 try {
			 String query = "{call " + DBUtil.GET_USER_BY_EMAIL_ID + "}";
			 System.out.println("Query: "+ query);
			 CallableStatement statement = con.prepareCall(query);
			 statement.setString(1, input.getEmailId());
			 System.out.println(statement.toString());
			 boolean statementResultType = statement.execute();
			 System.out.println("statementResultType : "+statementResultType);
			 ResultSet resultSet = statement.getResultSet();
			 List<User> userList = ResultSetObjectMapper.mapRersultSetToObject(resultSet, User.class);
			 if(userList != null && userList.size() > 0) {
				 return new LoginResponse(new Response(Constant.EMAIL_ADDRESS_ALREADY_EXIST, Constant.EMAIL_ADDRESS_ALREADY_EXIST_MSG));
			 } 
			 statement.close();
			 
			 String updateQuery = "{call " + DBUtil.ADD_NEW_USER + "}";
			 CallableStatement statement1 = con.prepareCall(updateQuery);
			 Random rand = new Random(); 
			 int user_id = rand.nextInt(899999999)+100000000;
			 while(!DBUtil.checkUniquenessOfUserId(user_id, con)) {
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
			 String authKey = UUID.randomUUID().toString();
			 statement1.setString(15, authKey);
			 System.out.println(statement1.toString());
			 boolean statementResultType1 = statement1.execute();
			 System.out.println("statementResultType1 :" + statementResultType1 + "  "+ statement1.getUpdateCount());
			 
			 if(!statementResultType1 && statement1.getUpdateCount() == 1) {
				 statement1.close();
				 return new LoginResponse(new Response(Constant.SUCCESS, 
						Constant.SUCCESS_MSG), input.getFirstName()+" "+ input.getLastName(),user_id,"c#"+authKey+"#"+user_id);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return new LoginResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
	}
	
}
