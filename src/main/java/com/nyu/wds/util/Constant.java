package com.nyu.wds.util;

import java.util.regex.Pattern;

public class Constant {

	public static final int JDBC_CONNECTION_MAX_RETRY = 3;
	public static final int SUCCESS = 1001;
	public static final String SUCCESS_MSG = "Success";
	public static final int DEFAULT_ERROR = 9999;
	public static final String DEFAULT_ERROR_MSG = "Something went wrong";
	public static final int INVALID_INPUT = 9998;
	public static final String INVALID_INPUT_MSG = "Invalid input";
	
	public static final int INVALID_EMAIL_ADDRESS = 9997;
	public static final String INVALID_EMAIL_ADDRESS_MSG = "Invalid email address";
	public static final int INCORRECT_EMAIL_ADDRESS_OR_PASSWORD = 9996;
	public static final String INCORRECT_EMAIL_ADDRESS_OR_PASSWORD_MSG = "Incorrect email id or password";
	public static final int INVALID_NAME_FORMAT = 9995;
	public static final String INVALID_NAME_FORMAT_MSG = "Inavlid name format";
	public static final int EMAIL_ADDRESS_ALREADY_EXIST = 9994;
	public static final String EMAIL_ADDRESS_ALREADY_EXIST_MSG = "Email address already exist";
	public static final int NO_INSURANCE_DATA_FOUND = 9993;
	public static final String NO_INSURANCE_DATA_FOUND_MSG = "You do not have any insurance with us.";
	public static final int NO_INVOICE_FOUND = 9993;
	public static final String NO_INVOICE_FOUND_MSG = "Invoice not availble.";
	public static final int DATA_NOT_AVAILABLE = 9992;
	public static final String DATA_NOT_AVAILABE_MSG = "Data not availble.";

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[a-zA-Z0-9+_.-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_NAME_REGEX = 
			Pattern.compile("[A-Z]+([ '-][A-Z]+)*", Pattern.CASE_INSENSITIVE);

}
