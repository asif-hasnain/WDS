package com.nyu.wds.dbmapper;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserFeature {

	@Column(name = "feature_id")
	private int feature_id;
	@Column(name = "user_id")
	private int user_id;
	public UserFeature(int feature_id, int user_id) {
		super();
		this.feature_id = feature_id;
		this.user_id = user_id;
	}
	public UserFeature() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getFeature_id() {
		return feature_id;
	}
	public void setFeature_id(int feature_id) {
		this.feature_id = feature_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "UserFeature [feature_id=" + feature_id + ", user_id=" + user_id + "]";
	}
}
