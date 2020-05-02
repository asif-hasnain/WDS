package com.nyu.wds;


public class ProfileDetailsResponse {
	private Response response;
	private String firstName;
	private String lastName;
	private String gender;
	private String marital_status;
	private String st_address;
	private String city;
	private String state;
	private String zipcode;
	private String userType;
	private String designation;
	private String department;
	private String emailId;
	public ProfileDetailsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProfileDetailsResponse(Response response) {
		super();
		this.response = response;
	}

	public ProfileDetailsResponse(Response response, String firstName, String lastName, String gender,
			String maritalStatus, String streetAddress, String city, String state, String zipcode, String userType,
			String designation, String department, String emailId) {
		super();
		this.response = response;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.marital_status = maritalStatus;
		this.st_address = streetAddress;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.userType = userType;
		this.designation = designation;
		this.department = department;
		this.emailId = emailId;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "ProfileDetailsResponse [response=" + response + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", marital_status=" + marital_status + ", st_address=" + st_address + ", city="
				+ city + ", state=" + state + ", zipcode=" + zipcode + ", userType=" + userType + ", designation="
				+ designation + ", department=" + department + ", emailId=" + emailId + "]";
	}

	
}
