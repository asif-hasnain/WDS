package com.nyu.wds;

public class UpdateUserDetailsRequest {
	private int userId;
	private String firstName;
	private String lastName;
	private String gender;
	private String marital_status;
	private String st_address;
	private String city;
	private String state;
	private String zipcode;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	@Override
	public String toString() {
		return "UpdateUserDetailsRequest [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", marital_status=" + marital_status + ", st_address=" + st_address + ", city="
				+ city + ", state=" + state + ", zipcode=" + zipcode + "]";
	}


}
