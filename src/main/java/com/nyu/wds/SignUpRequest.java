package com.nyu.wds;

import com.nyu.wds.util.CommonUtil;
import com.nyu.wds.util.Constant;

public class SignUpRequest {
	private String emailId;
	private String firstName;
	private String lastName;
	private String password;
	private String gender;
	private String marital_status;
	private String st_address;
	private String city;
	private String state;
	private String zipcode;
	private String user_type;
	private String designation;
	private String emp_department;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "SignUpRequest [emailId=" + emailId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", gender=" + gender + ", marital_status=" + marital_status
				+ ", st_address=" + st_address + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode
				+ ", user_type=" + user_type + ", designation=" + designation + ", emp_department=" + emp_department
				+ "]";
	}

	public LoginResponse validateInput() {

		if (this == null || !CommonUtil.isValidString(this.getPassword())
				|| (CommonUtil.isValidString(this.getGender())
						&& !(this.getGender().equalsIgnoreCase("F") || this.getGender().equalsIgnoreCase("M")))
				|| !CommonUtil.isValidName(this.getFirstName()) || !CommonUtil.isValidName(this.getLastName())
				|| !CommonUtil.isValidString(this.getMarital_status())
				|| !(this.getMarital_status().equalsIgnoreCase("M") || this.getMarital_status().equalsIgnoreCase("S")
						||  this.getMarital_status().equalsIgnoreCase("W"))
				|| !CommonUtil.isValidString(this.getSt_address()) || !CommonUtil.isValidString(this.getCity())
				|| !CommonUtil.isValidString(this.getState()) || this.getState().length() != 2
				|| !CommonUtil.isValidString(this.getZipcode()) || this.getZipcode().length() != 5
				|| !CommonUtil.isValidString(this.getUser_type())) {
			return new LoginResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		} else if (!CommonUtil.isValidEmail(this.getEmailId())) {
			return new LoginResponse(new Response(Constant.INVALID_EMAIL_ADDRESS, Constant.INVALID_EMAIL_ADDRESS_MSG));
		}
		return new LoginResponse(new Response(Constant.SUCCESS, Constant.SUCCESS_MSG));
	}
}
