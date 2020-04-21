package com.nyu.wds;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.dbmapper.User;
import com.nyu.wds.util.CommonUtil;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.JDBCConnection;
import com.nyu.wds.util.ResultSetObjectMapper;

public class Login implements RequestHandler<LoginRequest, LoginResponse>{
	Connection con = null;
	@Override
	public LoginResponse handleRequest(LoginRequest input, Context context) {
		if(input == null || !CommonUtil.isValidString(input.getPassword())) {
			return new LoginResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
		} else if(!CommonUtil.isValidEmail(input.getEmailId())) {
			return new LoginResponse(new Response(Constant.INVALID_EMAIL_ADDRESS, Constant.INVALID_EMAIL_ADDRESS_MSG));
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		 try {
			 String query = "{call " + Constant.GET_USER_BY_EMAIL_ID + "}";
			 System.out.println("Query: "+ query);
			 CallableStatement statement = con.prepareCall(query);
			 statement.setString(1, input.getEmailId());
			 boolean statementResultType = statement.execute();
			 System.out.println("statementResultType : "+statementResultType);
			 if(statementResultType) {
				 ResultSet resultSet = statement.getResultSet();
				 List<User> userList = ResultSetObjectMapper.mapRersultSetToObject(resultSet, User.class);
				 if(userList == null || userList.size() == 0) {
					return new LoginResponse(new Response(Constant.INCORRECT_EMAIL_ADDRESS_OR_PASSWORD, Constant.INCORRECT_EMAIL_ADDRESS_OR_PASSWORD_MSG));
				 } else if(userList.size() > 1) {
						return new LoginResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
				 }else if(CommonUtil.isValidString(userList.get(0).getPassword()) && checkLoginCredentials(userList.get(0).getPassword(), input.getPassword())) {
						return new LoginResponse(new Response(Constant.SUCCESS, 
								Constant.SUCCESS_MSG), userList.get(0).getFirst_name()+" "+userList.get(0).getLast_name(),userList.get(0).getUser_id());
				 }else {
						return new LoginResponse(new Response(Constant.INCORRECT_EMAIL_ADDRESS_OR_PASSWORD, Constant.INCORRECT_EMAIL_ADDRESS_OR_PASSWORD_MSG));
				 }
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return new LoginResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
	}
	public boolean checkLoginCredentials(String dbPassword, String password) {
		String calculatedKey = CommonUtil.hash256Calculator(password);
		
		if(CommonUtil.isValidString(calculatedKey) && CommonUtil.isValidString(dbPassword) && calculatedKey.equals(dbPassword)) {
			return true;
		} else {
			return false;
		}
		
	}

}
