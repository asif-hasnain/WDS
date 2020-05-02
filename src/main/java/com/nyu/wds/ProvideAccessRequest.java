package com.nyu.wds;

public class ProvideAccessRequest {
	private int featureId;
	private int userId;
	public int getFeatureId() {
		return featureId;
	}
	public void setFeatureId(int featureId) {
		this.featureId = featureId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "ProvideAccessRequest [featureId=" + featureId + ", userId=" + userId + "]";
	}
	
}
