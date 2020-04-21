package com.nyu.wds;

import com.nyu.wds.util.CommonUtil;

public class LoginRequest {
	private String emailId;
	private String password;
	public LoginRequest() {
		super();
	}
	public LoginRequest(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginRequest [emailId=" + emailId + ", password=" + !password.isEmpty() + "]";
	}
}
