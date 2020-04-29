package com.nyu.wds;

public class ForgortPasswordRequest {

	private String emailId;
	private String code;
	private String password;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "ForgorPasswordRequest [emailId=" + emailId + ", code=" + code + ", password=" + password + "]";
	}
	
	
}
