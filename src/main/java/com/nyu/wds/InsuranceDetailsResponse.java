package com.nyu.wds;

import java.util.Map;

import com.nyu.wds.dbmapper.InsurancePolicy;

public class InsuranceDetailsResponse {

	private Response response;
	private Map<Integer, InsurancePolicy> policyMap;
	
	public InsuranceDetailsResponse(Response response, Map<Integer, InsurancePolicy> policyMap) {
		super();
		this.response = response;
		this.policyMap = policyMap;
	}
	public InsuranceDetailsResponse(Response response) {
		super();
		this.response = response;
	}
	public InsuranceDetailsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public Map<Integer, InsurancePolicy> getPolicyMap() {
		return policyMap;
	}
	public void setPolicyMap(Map<Integer, InsurancePolicy> policyMap) {
		this.policyMap = policyMap;
	}
	@Override
	public String toString() {
		return "InsuranceDetailsResponse [response=" + response + ", policyMap=" + policyMap + "]";
	}
	
}
