package com.nyu.wds;

public class UpdatePaymentRequest {

	private int invoiceId;
	private String paymentMethod;
	private double paymentAmount;

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	@Override
	public String toString() {
		return "UpdatePaymentRequest [invoiceId=" + invoiceId + ", paymentMethod=" + paymentMethod + ", paymentAmount="
				+ paymentAmount + "]";
	}

	
}
