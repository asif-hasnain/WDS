package com.nyu.wds;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Random;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.util.CommonUtil;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.DBUtil;
import com.nyu.wds.util.JDBCConnection;

public class UpdatePayment implements RequestHandler<UpdatePaymentRequest, UpdatePaymentResponse>{
	Connection con = null;
	@Override
	public UpdatePaymentResponse handleRequest(UpdatePaymentRequest input, Context context) {
		System.out.println("Input :" + input);
		if(input == null || input.getInvoiceId()==0 || input.getPaymentAmount() == 0||
				!CommonUtil.isValidString(input.getPaymentMethod()) ||!(input.getPaymentMethod().equalsIgnoreCase("Credit")
						|| input.getPaymentMethod().equalsIgnoreCase("Debit")
						|| input.getPaymentMethod().equalsIgnoreCase("Cheque"))) {
			return new UpdatePaymentResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		Random rand = new Random();
		int paymentId = rand.nextInt(899999999)+100000000;
		boolean paymentUpdated = DBUtil.updatePayment(paymentId, new java.sql.Date(Calendar.getInstance().getTimeInMillis())
				, input.getPaymentMethod(), input.getPaymentAmount(), input.getInvoiceId(), con);
		if(paymentUpdated) {
			return new UpdatePaymentResponse(new Response(Constant.SUCCESS, Constant.SUCCESS_MSG), paymentId);
		}
		return new UpdatePaymentResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
	}

}
