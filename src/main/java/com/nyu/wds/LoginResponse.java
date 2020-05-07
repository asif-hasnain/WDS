package com.nyu.wds;

public class LoginResponse {
	private Response response;
	private String name;
	private int userId;
	private String auth_key;
	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginResponse(Response response, String name, int userId, String auth_key) {
		super();
		this.response = response;
		this.name = name;
		this.userId = userId;
		this.auth_key = auth_key;
	}
	
	public LoginResponse(Response response, String name, int userId) {
		super();
		this.response = response;
		this.name = name;
		this.userId = userId;
	}
	public LoginResponse(Response response) {
		super();
		this.response = response;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getAuth_key() {
		return auth_key;
	}
	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}
	@Override
	public String toString() {
		return "LoginResponse [response=" + response + ", name=" + name + ", userId=" + userId + ", auth_key="
				+ auth_key + "]";
	}
}
