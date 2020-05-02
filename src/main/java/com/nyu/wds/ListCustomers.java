package com.nyu.wds;

import java.sql.Connection;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.dbmapper.User;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.DBUtil;
import com.nyu.wds.util.JDBCConnection;

public class ListCustomers implements RequestHandler<Object, ListCustomersResponse>{
	Connection con = null;
	@Override
	public ListCustomersResponse handleRequest(Object input, Context context) {
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		List<User> customerList = DBUtil.getCustomerList(con);
		if(customerList != null) {
			return new ListCustomersResponse(new Response(Constant.SUCCESS, Constant.SUCCESS_MSG), customerList);
		}
		return new ListCustomersResponse(new Response(Constant.DATA_NOT_AVAILABLE, Constant.DATA_NOT_AVAILABE_MSG));
	}

}
