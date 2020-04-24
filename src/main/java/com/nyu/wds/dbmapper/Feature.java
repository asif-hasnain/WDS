package com.nyu.wds.dbmapper;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Feature {

	@Column(name= "feature_id")
	private int feature_id;
	@Column(name = "feature_name")
	private String feature_name;
	@Column(name = "api")
	private String api;
	public Feature() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Feature(int feature_id, String feature_name, String api) {
		super();
		this.feature_id = feature_id;
		this.feature_name = feature_name;
		this.api = api;
	}
	public int getFeature_id() {
		return feature_id;
	}
	public void setFeature_id(int feature_id) {
		this.feature_id = feature_id;
	}
	public String getFeature_name() {
		return feature_name;
	}
	public void setFeature_name(String feature_name) {
		this.feature_name = feature_name;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}
	@Override
	public String toString() {
		return "Feature [feature_id=" + feature_id + ", feature_name=" + feature_name + ", api=" + api + "]";
	}
}
