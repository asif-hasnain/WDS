package com.nyu.wds.dbmapper;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Invoice {
	@Column(name ="invoice_id")
	private int invoice_id;
	@Column(name = "invoice_date")
	private Date invoice_date;
	@Column(name = "payment_due_date")
	private Date payment_due_date;
	@Column(name = "invoice_amount")
	private double invoice_amount;
	@Column(name = "policy_id")
	private int policy_id;
	public Invoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Invoice(int invoice_id, Date invoice_date, Date payment_due_date, double invoice_amount, int policy_id) {
		super();
		this.invoice_id = invoice_id;
		this.invoice_date = invoice_date;
		this.payment_due_date = payment_due_date;
		this.invoice_amount = invoice_amount;
		this.policy_id = policy_id;
	}
	public int getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(int invoice_id) {
		this.invoice_id = invoice_id;
	}
	public Date getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}
	public Date getPayment_due_date() {
		return payment_due_date;
	}
	public void setPayment_due_date(Date payment_due_date) {
		this.payment_due_date = payment_due_date;
	}
	public double getInvoice_amount() {
		return invoice_amount;
	}
	public void setInvoice_amount(double invoice_amount) {
		this.invoice_amount = invoice_amount;
	}
	public int getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(int policy_id) {
		this.policy_id = policy_id;
	}
	@Override
	public String toString() {
		return "Invoice [invoice_id=" + invoice_id + ", invoice_date=" + invoice_date + ", payment_due_date="
				+ payment_due_date + ", invoice_amount=" + invoice_amount + ", policy_id=" + policy_id + "]";
	}
}
