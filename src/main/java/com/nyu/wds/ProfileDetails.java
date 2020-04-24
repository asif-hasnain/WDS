package com.nyu.wds;

import java.sql.Connection;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.dbmapper.User;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.DBUtil;
import com.nyu.wds.util.JDBCConnection;

public class ProfileDetails implements RequestHandler<ProfileDetailsRequest, ProfileDetailsResponse>{
	Connection con = null;
	@Override
	public ProfileDetailsResponse handleRequest(ProfileDetailsRequest input, Context context) {
		System.out.println("input : " + input);
		if(input == null|| input.getUserId() == 0) {
			return new ProfileDetailsResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		User user = DBUtil.getUserByUserId(input.getUserId(), con);
		if(user == null) {
			return new ProfileDetailsResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		}
		return new ProfileDetailsResponse(new Response(Constant.SUCCESS, Constant.SUCCESS_MSG),
				user.getFirst_name(), user.getLast_name(), user.getGender(), user.getMarital_status(), 
				user.getSt_address(), user.getCity(), user.getState(), user.getZipcode(), user.getUser_type()
				, user.getDesignation(), user.getEmp_department(), user.getEmail_id());		
	}

}
