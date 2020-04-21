package com.nyu.wds;

public class LoginResponse {
	private Response response;
	private String name;
	private int userId;
	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "LoginResponse [response=" + response + ", name=" + name + ", userId=" + userId + "]";
	}
}
