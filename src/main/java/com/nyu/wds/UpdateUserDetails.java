package com.nyu.wds;

import java.sql.Connection;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.util.CommonUtil;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.DBUtil;
import com.nyu.wds.util.JDBCConnection;

public class UpdateUserDetails implements RequestHandler<UpdateUserDetailsRequest, Response> {
	Connection con = null;

	@Override
	public Response handleRequest(UpdateUserDetailsRequest input, Context context) {
		System.out.println("input : " + input);
		if (input == null || input.getUserId() == 0
				|| (CommonUtil.isValidString(input.getGender())
						&& !(input.getGender().equalsIgnoreCase("F") || input.getGender().equalsIgnoreCase("M")))
				|| !CommonUtil.isValidName(input.getFirstName()) || !CommonUtil.isValidName(input.getLastName())
				|| !CommonUtil.isValidString(input.getMarital_status())
				|| !(input.getMarital_status().equalsIgnoreCase("M") || input.getMarital_status().equalsIgnoreCase("S")
						|| input.getMarital_status().equalsIgnoreCase("W"))
				|| !CommonUtil.isValidString(input.getSt_address()) || !CommonUtil.isValidString(input.getCity())
				|| !CommonUtil.isValidString(input.getState()) || input.getState().length() != 2
				|| !CommonUtil.isValidString(input.getZipcode()) || input.getZipcode().length() != 5) {
			return new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG);
		}
		try {
			Integer.parseInt(input.getZipcode());
		} catch (NumberFormatException e) {
			return new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG);
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		boolean userDetails = DBUtil.updateUserDetails(input.getUserId(),input.getFirstName(), input.getLastName(), input.getGender(),
				input.getMarital_status(), input.getSt_address(), input.getCity(), input.getState(), input.getZipcode(),
				con);

		if (userDetails) {
			return new Response(Constant.SUCCESS, Constant.SUCCESS_MSG);
		}
		return new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG);
	}

}
