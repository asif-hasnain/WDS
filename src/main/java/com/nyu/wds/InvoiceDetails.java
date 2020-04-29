package com.nyu.wds;

import java.sql.Connection;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.dbmapper.Invoice;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.DBUtil;
import com.nyu.wds.util.JDBCConnection;

public class InvoiceDetails implements RequestHandler<InvoiceDetailsRequest, InvoiceDetailsResponse>{
	Connection con = null;
	@Override
	public InvoiceDetailsResponse handleRequest(InvoiceDetailsRequest input, Context context) {
		System.out.println("input : " + input);
		if(input == null|| input.getPolicy_id() == 0) {
			return new InvoiceDetailsResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		List<Invoice> invoiceList = DBUtil.getInvoiceDetails(input.getPolicy_id(), con);
		if(invoiceList != null && invoiceList.size()>0) {
			return new InvoiceDetailsResponse(new Response(Constant.SUCCESS, Constant.SUCCESS_MSG),invoiceList);
		}
		
		return new InvoiceDetailsResponse(new Response(Constant.NO_INVOICE_FOUND, Constant.NO_INVOICE_FOUND_MSG));
	}

}
