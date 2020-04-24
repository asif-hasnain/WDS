package com.nyu.wds.dbmapper;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AutoInsurance {

	@Column(name = "policy_id")
	private int policy_id;
	@Column(name = "no_claim_bonus")
	private double no_claim_bonus;
	public AutoInsurance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AutoInsurance(int policy_id, double no_claim_bonus) {
		super();
		this.policy_id = policy_id;
		this.no_claim_bonus = no_claim_bonus;
	}
	public int getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(int policy_id) {
		this.policy_id = policy_id;
	}
	public double getNo_claim_bonus() {
		return no_claim_bonus;
	}
	public void setNo_claim_bonus(double no_claim_bonus) {
		this.no_claim_bonus = no_claim_bonus;
	}
}
