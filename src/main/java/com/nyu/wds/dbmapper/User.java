package com.nyu.wds.dbmapper;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User {
	@Column(name="user_id")
	private int user_id;
	@Column(name="first_name")
	private String first_name;
	@Column(name="last_name")
	private String last_name;
	@Column(name="gender")
	private String gender;
	@Column(name="marital_status")
	private String marital_status;
	@Column(name="st_address")
	private String st_address;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="zipcode")
	private String zipcode;
	@Column(name="user_type")
	private String user_type;
	@Column(name="designation")
	private String designation;
	@Column(name="emp_department")
	private String emp_department;
	@Column(name="email_id")
	private String email_id;
	@Column(name="password")
	private String password;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int user_id, String first_name, String last_name, String gender, String marital_status, String st_address,
			String city, String state, String zipcode, String user_type, String designation, String emp_department,
			String email_id, String password) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.marital_status = marital_status;
		this.st_address = st_address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.user_type = user_type;
		this.designation = designation;
		this.emp_department = emp_department;
		this.email_id = email_id;
		this.password = password;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getSt_address() {
		return st_address;
	}
	public void setSt_address(String st_address) {
		this.st_address = st_address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmp_department() {
		return emp_department;
	}
	public void setEmp_department(String emp_department) {
		this.emp_department = emp_department;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", gender="
				+ gender + ", marital_status=" + marital_status + ", st_address=" + st_address + ", city=" + city
				+ ", state=" + state + ", zipcode=" + zipcode + ", user_type=" + user_type + ", designation="
				+ designation + ", emp_department=" + emp_department + ", email_id=" + email_id + ", password="
				+ password + "]";
	}
	
	
}
