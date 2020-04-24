package com.nyu.wds.dbmapper;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class HomeInsurance {

	@Column(name = "policy_id")
	private int policy_id;
	@Column(name = "discount")
	private double discount;
	public HomeInsurance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HomeInsurance(int policy_id, double discount) {
		super();
		this.policy_id = policy_id;
		this.discount = discount;
	}
	public int getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(int policy_id) {
		this.policy_id = policy_id;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "HomeInsurance [policy_id=" + policy_id + ", discount=" + discount + "]";
	}
}
