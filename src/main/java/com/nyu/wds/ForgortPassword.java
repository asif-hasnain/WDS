package com.nyu.wds;

import java.sql.Connection;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.util.CommonUtil;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.DBUtil;
import com.nyu.wds.util.JDBCConnection;

public class ForgortPassword implements RequestHandler<ForgortPasswordRequest, Response> {
	Connection con = null;
	@Override
	public Response handleRequest(ForgortPasswordRequest input, Context context) {
		System.out.println("Input :" + input);
		if (input == null || !CommonUtil.isValidString(input.getCode()) || !CommonUtil.isValidEmail(input.getEmailId())
				|| !CommonUtil.isValidString(input.getPassword())) {
			return new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG);
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		if(input.getCode().equals("12345")) {
			boolean updatePassword = DBUtil.updatePassword(input.getEmailId(), input.getPassword(), con);
			if(updatePassword) {
				return new Response(Constant.SUCCESS, Constant.SUCCESS_MSG);
			}
		}
		return new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG);
	}

}
