package com.nyu.wds;

public class InsuranceDetailsRequest {

	public int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "InsuranceDetailsRequest [userId=" + userId + "]";
	}
	
	
}
