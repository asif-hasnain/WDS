package com.nyu.wds.dbmapper;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Vehicle {

	@Column(name = "vin")
	private String vin;
	@Column(name = "make")
	private String make;
	@Column(name = "model")
	private String model;
	@Column(name= "year")
	private int year;
	@Column(name = "vehicle_status")
	private String vehicle_status;
	@Column(name= "policy_id")
	private int policy_id;
	
	private List<Driver> driverList;
	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vehicle(String vin, String make, String model, int year, String vehicle_status, List<Driver> driverList) {
		super();
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.year = year;
		this.vehicle_status = vehicle_status;
		this.driverList = driverList;
	}

	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getVehicle_status() {
		return vehicle_status;
	}
	public void setVehicle_status(String vehicle_status) {
		this.vehicle_status = vehicle_status;
	}
	public int getPolicy_id() {
		return policy_id;
	}
	public void setPolicy_id(int policy_id) {
		this.policy_id = policy_id;
	}
	
	public List<Driver> getDriverList() {
		return driverList;
	}
	public void setDriverList(List<Driver> driverList) {
		this.driverList = driverList;
	}

	@Override
	public String toString() {
		return "Vehicle [vin=" + vin + ", make=" + make + ", model=" + model + ", year=" + year + ", vehicle_status="
				+ vehicle_status + ", policy_id=" + policy_id + ", driverList=" + driverList + "]";
	}

}
