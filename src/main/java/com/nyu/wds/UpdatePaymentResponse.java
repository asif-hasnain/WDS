package com.nyu.wds;

public class UpdatePaymentResponse {
	private Response response;
	private int paymentId;
	public UpdatePaymentResponse(Response response, int paymentId) {
		super();
		this.response = response;
		this.paymentId = paymentId;
	}
	public UpdatePaymentResponse(Response response) {
		super();
		this.response = response;
	}
	public UpdatePaymentResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	@Override
	public String toString() {
		return "UpdatePaymentResponse [response=" + response + ", paymentId=" + paymentId + "]";
	}
}
