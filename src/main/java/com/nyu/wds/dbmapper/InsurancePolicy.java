package com.nyu.wds.dbmapper;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class InsurancePolicy {

	@Column(name = "policy_id")
	private int policy_id;
	@Column(name = "start_date")
	private Date start_date;
	@Column(name = "end_date")
	private Date end_date;
	@Column(name = "premium_amount")
	private double premium_amount;
	@Column(name = "policy_type")
	private String policy_type;
	@Column(name = "user_id")
	private int user_id;
	@Column(name = "discount")
	private double discount;
	@Column(name = "no_claim_bonus")
	private double no_claim_bonus;
	
	private Map<Integer,Home> homeMap;
	private Map<String,Vehicle> vehicleMap;
	

	public InsurancePolicy(int policy_id, Date start_date, Date end_date, double premium_amount,
			String policy_type, double discount, double no_claim_bonus, Map<Integer, Home> homeMap,
			Map<String, Vehicle> vehicleMap) {
		super();
		this.policy_id = policy_id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.premium_amount = premium_amount;
		this.policy_type = policy_type;
		this.discount = discount;
		this.no_claim_bonus = no_claim_bonus;
		this.homeMap = homeMap;
		this.vehicleMap = vehicleMap;
	}
	public InsurancePolicy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(int policy_id) {
		this.policy_id = policy_id;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public double getPremium_amount() {
		return premium_amount;
	}
	public void setPremium_amount(double premium_amount) {
		this.premium_amount = premium_amount;
	}
	public String getPolicy_type() {
		return policy_type;
	}
	public void setPolicy_type(String policy_type) {
		this.policy_type = policy_type;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getNo_claim_bonus() {
		return no_claim_bonus;
	}
	public void setNo_claim_bonus(double no_claim_bonus) {
		this.no_claim_bonus = no_claim_bonus;
	}
	public Map<Integer, Home> getHomeMap() {
		return homeMap;
	}
	public void setHomeMap(Map<Integer, Home> homeMap) {
		this.homeMap = homeMap;
	}
	public Map<String, Vehicle> getVehicleMap() {
		return vehicleMap;
	}
	public void setVehicleMap(Map<String, Vehicle> vehicleMap) {
		this.vehicleMap = vehicleMap;
	}
	@Override
	public String toString() {
		return "InsurancePolicy [policy_id=" + policy_id + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", premium_amount=" + premium_amount + ", policy_type=" + policy_type + ", user_id=" + user_id
				+ ", discount=" + discount + ", no_claim_bonus=" + no_claim_bonus + ", homeMap=" + homeMap
				+ ", vehicleMap=" + vehicleMap + "]";
	}

	


	
}
