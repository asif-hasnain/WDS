package com.nyu.wds.dbmapper;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Authentication {
	@Column(name="auth_key")
	private String auth_key;
	@Column(name="login_time")
	private Timestamp login_time;
	@Column(name="user_id")
	private int user_id;
	public Authentication(String auth_key, Timestamp login_time, int user_id) {
		super();
		this.auth_key = auth_key;
		this.login_time = login_time;
		this.user_id = user_id;
	}
	public Authentication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAuth_key() {
		return auth_key;
	}
	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}
	public Timestamp getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Timestamp login_time) {
		this.login_time = login_time;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "Authentication [auth_key=" + auth_key + ", login_time=" + login_time + ", user_id=" + user_id + "]";
	}
}
