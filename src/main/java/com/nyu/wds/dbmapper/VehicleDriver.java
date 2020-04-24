package com.nyu.wds.dbmapper;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class VehicleDriver {

	@Column(name = "licence_number")
	private String licence_number;
	@Column(name = "vin")
	private String vin;
	public VehicleDriver(String licence_number, String vin) {
		super();
		this.licence_number = licence_number;
		this.vin = vin;
	}
	public VehicleDriver() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getLicence_number() {
		return licence_number;
	}
	public void setLicence_number(String licence_number) {
		this.licence_number = licence_number;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	@Override
	public String toString() {
		return "VehicleDriver [licence_number=" + licence_number + ", vin=" + vin + "]";
	}
}
