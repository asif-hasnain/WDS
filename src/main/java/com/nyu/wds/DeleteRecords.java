package com.nyu.wds;

import java.sql.Connection;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.util.CommonUtil;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.DBUtil;
import com.nyu.wds.util.JDBCConnection;

public class DeleteRecords implements RequestHandler<DeleteRecordsRequest, Response>{
	Connection con = null;
	@Override
	public Response handleRequest(DeleteRecordsRequest input, Context context) {
		
		System.out.println("input : " + input);
		if (input == null || (input.getIntId1() ==0 && input.getIntId2()==0 && !CommonUtil.isValidString(input.getStrId1())&& !CommonUtil.isValidString(input.getStrId2())) ) {
			return new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG);
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		boolean deleteRecord = DBUtil.deleteRecord(input.getTableName(), input.getIntId1(), input.getIntId2(),
				input.getStrId1(), input.getStrId2(), con);
		if(deleteRecord) {
			return new Response(Constant.SUCCESS, Constant.SUCCESS_MSG);
		}
		return new Response(Constant.DATA_NOT_AVAILABLE, Constant.DATA_NOT_AVAILABE_MSG);
	}

	
}
