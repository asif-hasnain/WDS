package com.nyu.wds;

public class ApplyInsuranceResponse {
	private Response response;
	private int policy_id;
	public ApplyInsuranceResponse() {
		super();
	}
	public ApplyInsuranceResponse(Response response) {
		super();
		this.response = response;
	}
	public ApplyInsuranceResponse(Response response, int policy_id) {
		super();
		this.response = response;
		this.policy_id = policy_id;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public int getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(int policy_id) {
		this.policy_id = policy_id;
	}
	@Override
	public String toString() {
		return "ApplyInsuranceResponse [response=" + response + ", policy_id=" + policy_id + "]";
	}
}
