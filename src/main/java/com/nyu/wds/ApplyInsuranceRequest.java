package com.nyu.wds;

import java.util.List;

import com.nyu.wds.dbmapper.Home;
import com.nyu.wds.dbmapper.Vehicle;

public class ApplyInsuranceRequest {
	private int userId;
	private String policy_type;
	private List<Home> homeList;
	private List<Vehicle> vehicle;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPolicy_type() {
		return policy_type;
	}
	public void setPolicy_type(String policy_type) {
		this.policy_type = policy_type;
	}
	public List<Home> getHomeList() {
		return homeList;
	}
	public void setHomeList(List<Home> homeList) {
		this.homeList = homeList;
	}
	public List<Vehicle> getVehicle() {
		return vehicle;
	}
	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	@Override
	public String toString() {
		return "ApplyInsuranceRequest [userId=" + userId + ", policy_type=" + policy_type + ", homeList=" + homeList
				+ ", vehicle=" + vehicle + "]";
	}

}
