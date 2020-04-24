package com.nyu.wds.dbmapper;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Driver {

	@Column(name = "licence_number")
	private String licence_number;
	@Column(name = "first_name")
	private String first_name;
	@Column(name = "last_name")
	private String last_name;
	@Column(name = "dob")
	private Date dob;
	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Driver(String licence_number, String first_name, String last_name, Date dob) {
		super();
		this.licence_number = licence_number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.dob = dob;
	}
	
	public Driver(String licence_number, String first_name, String last_name) {
		super();
		this.licence_number = licence_number;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	public String getLicence_number() {
		return licence_number;
	}
	public void setLicence_number(String licence_number) {
		this.licence_number = licence_number;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "Driver [licence_number=" + licence_number + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", dob=" + dob + "]";
	}
	
	
}
