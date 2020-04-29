package com.nyu.wds;

public class InvoiceDetailsRequest {

	private int policy_id;

	public int getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(int policy_id) {
		this.policy_id = policy_id;
	}

	@Override
	public String toString() {
		return "InvoiceDetailsRequest [policy_id=" + policy_id + "]";
	}
	
}
