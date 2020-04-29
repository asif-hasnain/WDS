package com.nyu.wds;

import java.sql.Connection;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.dbmapper.InsurancePolicy;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.DBUtil;
import com.nyu.wds.util.JDBCConnection;

public class InsuranceDetails implements RequestHandler<InsuranceDetailsRequest, InsuranceDetailsResponse>{
	Connection con = null;
	@Override
	public InsuranceDetailsResponse handleRequest(InsuranceDetailsRequest input, Context context) {
		System.out.println("input : " + input);
		if(input == null|| input.getUserId() == 0) {
			return new InsuranceDetailsResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		Map<Integer, InsurancePolicy> policyMap = DBUtil.getInsuranceDetails(input.getUserId(), con);
		if(policyMap == null || policyMap.size() ==0) {
			return new InsuranceDetailsResponse(new Response(Constant.NO_INSURANCE_DATA_FOUND, Constant.NO_INSURANCE_DATA_FOUND_MSG));
		}
		return new InsuranceDetailsResponse(new Response(Constant.SUCCESS, Constant.SUCCESS_MSG),
				policyMap);		
	}
}
