package com.nyu.wds;

import java.util.List;

import com.nyu.wds.dbmapper.User;

public class ListCustomersResponse {
	private Response response;
	private List<User> userList;
	public ListCustomersResponse(Response response, List<User> userList) {
		super();
		this.response = response;
		this.userList = userList;
	}
	public ListCustomersResponse(Response response) {
		super();
		this.response = response;
	}
	public ListCustomersResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	@Override
	public String toString() {
		return "ListCustomersResponse [response=" + response + ", userList=" + userList + "]";
	}
}
