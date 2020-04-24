package com.nyu.wds;

public class ProfileDetailsRequest {

	private int userId;

	public ProfileDetailsRequest(int userId) {
		super();
		this.userId = userId;
	}
	public ProfileDetailsRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ProfileDetailsRequest [userId=" + userId + "]";
	}
	
}
