package com.nyu.wds.dbmapper;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Home {

	@Column(name = "home_id")
	private int home_id;
	@Column(name = "purchase_date")
	private Date purchase_date;
	@Column(name = "purchase_value")
	private double purchase_value;
	@Column(name = "area")
	private double area;
	@Column(name = "home_type")
	private String home_type;
	@Column(name = "auto_fire_notification")
	private int auto_fire_notification;
	@Column(name = "home_security_system")
	private int home_security_system;
	@Column(name = "swimming_pool")
	private String swimming_pool;
	@Column(name = "basement")
	private int basement;
	@Column(name = "policy_id")
	private int policy_id;
	@Column(name = "st_address")
	private String st_address;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "zipcode")
	private String zipcode;
	
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Home(int home_id, Date purchase_date, double purchase_value, double area, String home_type,
			int auto_fire_notification, int home_security_system, String swimming_pool, int basement, String st_address,
			String city, String state, String zipcode) {
		super();
		this.home_id = home_id;
		this.purchase_date = purchase_date;
		this.purchase_value = purchase_value;
		this.area = area;
		this.home_type = home_type;
		this.auto_fire_notification = auto_fire_notification;
		this.home_security_system = home_security_system;
		this.swimming_pool = swimming_pool;
		this.basement = basement;
		this.st_address = st_address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
	}




	public int getHome_id() {
		return home_id;
	}
	public void setHome_id(int home_id) {
		this.home_id = home_id;
	}
	public Date getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}
	public double getPurchase_value() {
		return purchase_value;
	}
	public void setPurchase_value(double purchase_value) {
		this.purchase_value = purchase_value;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public String getHome_type() {
		return home_type;
	}
	public void setHome_type(String home_type) {
		this.home_type = home_type;
	}
	public int getAuto_fire_notification() {
		return auto_fire_notification;
	}
	public void setAuto_fire_notification(int auto_fire_notification) {
		this.auto_fire_notification = auto_fire_notification;
	}
	public int getHome_security_system() {
		return home_security_system;
	}
	public void setHome_security_system(int home_security_system) {
		this.home_security_system = home_security_system;
	}
	public String getSwimming_pool() {
		return swimming_pool;
	}
	public void setSwimming_pool(String swimming_pool) {
		this.swimming_pool = swimming_pool;
	}
	public int getBasement() {
		return basement;
	}
	public void setBasement(int basement) {
		this.basement = basement;
	}
	public int getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(int policy_id) {
		this.policy_id = policy_id;
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

	@Override
	public String toString() {
		return "Home [home_id=" + home_id + ", purchase_date=" + purchase_date + ", purchase_value=" + purchase_value
				+ ", area=" + area + ", home_type=" + home_type + ", auto_fire_notification=" + auto_fire_notification
				+ ", home_security_system=" + home_security_system + ", swimming_pool=" + swimming_pool + ", basement="
				+ basement + ", policy_id=" + policy_id + ", st_address=" + st_address + ", city=" + city + ", state="
				+ state + ", zipcode=" + zipcode + "]";
	}


}
