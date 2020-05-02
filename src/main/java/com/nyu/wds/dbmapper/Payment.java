package com.nyu.wds.dbmapper;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Payment {

	@Column(name = "payment_id")
	private int payment_id;
	@Column(name = "payment_date")
	private Date payment_date;
	@Column(name = "payment_method")
	private String payment_method;
	@Column(name = "payment_amount")
	private double payment_amount;
	@Column(name = "invoice_id")
	private int invoice_id;
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(int payment_id, Date payment_date, String payment_method, double payment_amount, int invoice_id) {
		super();
		this.payment_id = payment_id;
		this.payment_date = payment_date;
		this.payment_method = payment_method;
		this.payment_amount = payment_amount;
		this.invoice_id = invoice_id;
	}

	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public Date getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	public double getPayment_amount() {
		return payment_amount;
	}
	public void setPayment_amount(double payment_amount) {
		this.payment_amount = payment_amount;
	}
	@Override
	public String toString() {
		return "Payment [payment_id=" + payment_id + ", payment_date=" + payment_date + ", payment_method="
				+ payment_method + ", payment_amount=" + payment_amount + ", invoice_id=" + invoice_id + "]";
	}

}
