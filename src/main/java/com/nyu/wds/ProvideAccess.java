package com.nyu.wds;

import java.sql.Connection;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.DBUtil;
import com.nyu.wds.util.JDBCConnection;

public class ProvideAccess implements RequestHandler<ProvideAccessRequest, Response>{
	Connection con = null;
	@Override
	public Response handleRequest(ProvideAccessRequest input, Context context) {
		System.out.println("input : " + input);
		if (input == null || input.getFeatureId() ==0 || input.getUserId()==0) {
			return new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG);
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		boolean provideAccess = DBUtil.provideAccess(input.getFeatureId(), input.getUserId(), con);
		if(provideAccess) {
			return new Response(Constant.SUCCESS, Constant.SUCCESS_MSG);
		}
		return new Response(Constant.DATA_NOT_AVAILABLE, Constant.DATA_NOT_AVAILABE_MSG);
	}

}
