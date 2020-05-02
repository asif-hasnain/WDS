package com.nyu.wds.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import com.nyu.wds.dbmapper.Authentication;
import com.nyu.wds.dbmapper.Driver;
import com.nyu.wds.dbmapper.Feature;
import com.nyu.wds.dbmapper.Vehicle;
import com.nyu.wds.dbmapper.InsurancePolicy;
import com.nyu.wds.dbmapper.Invoice;
import com.nyu.wds.dbmapper.Payment;
import com.nyu.wds.dbmapper.User;
import com.nyu.wds.dbmapper.UserFeature;
import com.nyu.wds.dbmapper.Home;

public class DBUtil {

	public static String GET_USER_BY_USER_ID = "get_user_details(?)";
	public static String GET_USER_BY_EMAIL_ID = "get_user_by_emailid(?)";
	public static String ADD_NEW_USER = "add_new_user(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String ADD_AUTHENTICATION_DETAILS = "add_authentication_details(?,?)";
	public static String GET_ATUTHENTICATION_DETAILS = "get_authentication_details(?)";
	public static String GET_INSURANCE_DETAILS_BY_USER_ID = "get_insurance_details(?)";
	public static String ADD_INSURANCE = "add_insurance(?,?,?,?,?,?,?,?)";
	public static String ADD_HOME = "add_home(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static String ADD_VEHICLE = "add_vehicle(?,?,?,?,?,?)";
	public static String ADD_DRIVER = "add_driver(?,?,?,?,?)";
	public static String GET_INVOICE_DETAILS = "get_invoice_detail(?)";
	public static String UPDATE_PAYMENT = "update_payment(?,?,?,?,?)";
	public static String GET_ACCESS_DETAILS = "get_access_details(?,?)";
	public static String GET_RESTRICTED_FEATURE = "get_restricted_feature(?)";
	public static String UPDATE_PASSWORD = "update_password(?,?)";
	public static String PROVIDE_ACCESS = "provide_access(?,?)";
	public static String DELETE_RECORD = "delete_record(?,?,?,?,?)";
	public static String GET_CUSTOMER_LIST = "get_customer_list()";
	public static String UPDATE_USER_DETAILS = "update_user_details(?,?,?,?,?,?,?,?,?)";

	
	public static final String Home = "H";
	public static final String Vehicle = "A";

	public static boolean checkUniquenessOfUserId(int userId, Connection con) {
		try {
			String query = "{call " + GET_USER_BY_USER_ID + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			System.out.println(statement.toString());
			statement.setInt(1, userId);
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			if (resultSet.first()) {
				statement.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	public static User getUserByUserId(int userId, Connection con) {
		try {
			String query = "{call " + GET_USER_BY_USER_ID + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			System.out.println(statement.toString());
			statement.setInt(1, userId);
			System.out.println(statement.toString());
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			List<User> userList = ResultSetObjectMapper.mapRersultSetToObject(resultSet, User.class);
			statement.close();
			if (userList != null && userList.size() == 1) {
				return userList.get(0);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String addAuthenticationDetails(int userId, Connection con) {
		try {
			String query = "{call " + ADD_AUTHENTICATION_DETAILS + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			String authKey = UUID.randomUUID().toString();
			statement.setString(1, authKey);
			statement.setInt(2, userId);
			System.out.println(statement.toString());
			boolean statementResultType = statement.execute();
			System.out.println("statementResultType1 :" + statementResultType + "  " + statement.getUpdateCount());
			if (!statementResultType && statement.getUpdateCount() == 1) {
				statement.close();
				return authKey;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Authentication getAuthenticationDetails(String authKey, Connection con) {
		try {
			String query = "{call " + GET_ATUTHENTICATION_DETAILS + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			statement.setString(1, authKey);
			System.out.println(statement.toString());
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			List<Authentication> authList = ResultSetObjectMapper.mapRersultSetToObject(resultSet,
					Authentication.class);
			statement.close();
			if (authList != null && authList.size() == 1) {
				return authList.get(0);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Map<Integer, InsurancePolicy> getInsuranceDetails(int userId, Connection con) {
		try {
			String query = "{call " + GET_INSURANCE_DETAILS_BY_USER_ID + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			statement.setInt(1, userId);
			System.out.println(statement.toString());
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			Map<Integer, InsurancePolicy> policyMap = new HashMap<Integer, InsurancePolicy>();
			if (resultSet != null) {
				while (resultSet.next()) {
					int policy_id = resultSet.getInt("policy_id");
					Date start_date = resultSet.getDate("start_date");
					Date end_date = resultSet.getDate("end_date");
					double premium_amount = resultSet.getDouble("premium_amount");
					String policy_type = resultSet.getString("policy_type");
					double no_claim_bonus = resultSet.getDouble("no_claim_bonus");
					double discount = resultSet.getDouble("discount");
					int home_id = resultSet.getInt("home_id");
					Date purchase_date = resultSet.getDate("purchase_date");
					double purchase_value = resultSet.getDouble("purchase_value");
					double area = resultSet.getDouble("area");
					String home_type = resultSet.getString("home_type");
					int auto_fire_notification = resultSet.getInt("auto_fire_notification");
					int home_security_system = resultSet.getInt("home_security_system");
					String swimming_pool = resultSet.getString("swimming_pool");
					int basement = resultSet.getInt("basement");
					String st_address = resultSet.getString("st_address");
					String city = resultSet.getString("city");
					String state = resultSet.getString("state");
					String zipcode = resultSet.getString("zipcode");
					String vin = resultSet.getString("vin");
					String make = resultSet.getString("make");
					String model = resultSet.getString("model");
					int year = resultSet.getInt("year");
					String vehicle_status = resultSet.getString("vehicle_status");
					String licence_number = resultSet.getString("licence_number");
					String first_name = resultSet.getString("first_name");
					String last_name = resultSet.getString("last_name");

					InsurancePolicy insPol = policyMap.getOrDefault(policy_id,
							new InsurancePolicy(policy_id, start_date, end_date, premium_amount, policy_type, discount,
									no_claim_bonus, new HashMap<Integer, Home>(), new HashMap<String, Vehicle>()));
					if (CommonUtil.isValidString(policy_type)) {
						if (policy_type.equalsIgnoreCase(Home)) {
							Map<Integer, Home> homeMap = insPol.getHomeMap();
							Home home = homeMap.getOrDefault(home_id,
									new Home(home_id, purchase_date, purchase_value, area, home_type,
											auto_fire_notification, home_security_system, swimming_pool, basement,
											st_address, city, state, zipcode));
							homeMap.put(home_id, home);
							insPol.setHomeMap(homeMap);
						} else if (policy_type.equalsIgnoreCase(Vehicle)) {
							Map<String, Vehicle> vehicleMap = insPol.getVehicleMap();
							Vehicle vehicle = vehicleMap.getOrDefault(vin,
									new Vehicle(vin, make, model, year, vehicle_status, new ArrayList<Driver>()));
							Driver driver = new Driver(licence_number, first_name, last_name);
							List<Driver> driverList = vehicle.getDriverList();
							driverList.add(driver);
							vehicle.setDriverList(driverList);
							vehicleMap.put(vin, vehicle);
							insPol.setVehicleMap(vehicleMap);
						}
					}
					policyMap.put(policy_id, insPol);
				}
			}
			statement.close();
			if (policyMap != null && policyMap.size() > 0) {
				return policyMap;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean addInsurance(int policyId, Date startDate, Date endDate, double premiumAmount,
			String policyType, int userId, double noClaimBonus, double discountIn, Connection con) {
		try {
			String query = "{call " + ADD_INSURANCE + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			statement.setInt(1, policyId);
			statement.setDate(2, startDate);
			statement.setDate(3, endDate);
			statement.setDouble(4, premiumAmount);
			statement.setString(5, policyType);
			statement.setInt(6, userId);
			statement.setDouble(7, noClaimBonus);
			statement.setDouble(8, discountIn);
			System.out.println(statement.toString());
			boolean statementResultType = statement.execute();
			System.out.println("statementResultType1 :" + statementResultType + "  " + statement.getUpdateCount());
			if (!statementResultType && statement.getUpdateCount() == 1) {
				statement.close();
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean addHome(List<Home> homeList, int policyId, Connection con) {
		try {
			String query = "{call " + ADD_HOME + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			Random rand = new Random();
			for (Home home : homeList) {

				statement.setInt(1, rand.nextInt(899999999) + 100000000);
				statement.setDate(2, home.getPurchase_date());
				statement.setDouble(3, home.getPurchase_value());
				statement.setDouble(4, home.getArea());
				statement.setString(5, home.getHome_type());
				statement.setInt(6, home.getAuto_fire_notification());
				statement.setInt(7, home.getHome_security_system());
				statement.setString(8,
						CommonUtil.isValidString(home.getSwimming_pool()) && home.getSwimming_pool().length() == 1
								? home.getSwimming_pool()
								: null);
				statement.setInt(9, home.getBasement());
				statement.setInt(10, policyId);
				statement.setString(11, home.getSt_address());
				statement.setString(12, home.getCity());
				statement.setString(13, home.getState());
				statement.setString(14, home.getZipcode());
				System.out.println(statement.toString());
				statement.addBatch();
			}

			statement.executeBatch();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean addVehicleAndDriver(List<Vehicle> vehicleList, int policyId, Connection con) {
		try {
			String query1 = "{call " + ADD_VEHICLE + "}";
			System.out.println("Query1: " + query1);
			CallableStatement statement1 = con.prepareCall(query1);
			String query2 = "{call " + ADD_DRIVER + "}";
			System.out.println("Query2: " + query2);
			CallableStatement statement2 = con.prepareCall(query2);
			for (Vehicle vehicle : vehicleList) {

				statement1.setString(1, vehicle.getVin());
				statement1.setString(2, vehicle.getMake());
				statement1.setString(3, vehicle.getModel());
				statement1.setInt(4, vehicle.getYear());
				statement1.setString(5, vehicle.getVehicle_status());
				statement1.setInt(6, policyId);
				statement1.addBatch();
				for (Driver driver : vehicle.getDriverList()) {
					statement2.setString(1, driver.getLicence_number());
					statement2.setString(2, driver.getFirst_name());
					statement2.setString(3, driver.getLast_name());
					statement2.setDate(4, driver.getDob());
					statement2.setString(5, vehicle.getVin());
					statement2.addBatch();
				}
			}
			System.out.println("Add vehicle : " + statement1.toString());
			statement1.executeBatch();
			System.out.println("Add driver : " + statement2.toString());
			statement2.executeBatch();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static List<Invoice> getInvoiceDetails(int policyId, Connection con) {
		try {
			String query = "{call " + GET_INVOICE_DETAILS + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			statement.setInt(1, policyId);
			System.out.println(statement.toString());
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			Map<Integer, Invoice> invoiceMap = new HashMap<Integer, Invoice>();
			if (resultSet != null) {
				while (resultSet.next()) {
					int invoice_id = resultSet.getInt("invoice_id");
					Date invoice_date = resultSet.getDate("invoice_date");
					Date payment_due_date = resultSet.getDate("payment_due_date");
					double invoice_amount = resultSet.getDouble("invoice_amount");
					int policy_id = resultSet.getInt("policy_id");
					int payment_id = resultSet.getInt("payment_id");
					Date payment_date = resultSet.getDate("payment_date");
					String payment_method = resultSet.getString("payment_method");
					double payment_amount = resultSet.getDouble("payment_amount");

					Invoice invoice = invoiceMap.getOrDefault(invoice_id, new Invoice(invoice_id, invoice_date,
							payment_due_date, invoice_amount, policy_id, new ArrayList<Payment>(), invoice_amount));
					double paymentDue = invoice.getPaymentDue();
					Payment payment = new Payment(payment_id, payment_date, payment_method, payment_amount,invoice_id);
					paymentDue -= payment_amount;
					List<Payment> paymentList = invoice.getPaymentList();
					paymentList.add(payment);
					invoice.setPaymentList(paymentList);
					invoice.setPaymentDue(paymentDue);
					invoiceMap.put(invoice_id, invoice);
				}
			}
			statement.close();
			if (invoiceMap != null && invoiceMap.size() > 0) {
				return invoiceMap.values().stream().collect(Collectors.toList());
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean updatePayment(int paymentId, Date paymentDate, String paymentMethod, double paymentAmount,
			int invoiceId, Connection con) {
		try {
			String query = "{call " + UPDATE_PAYMENT + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			statement.setInt(1, paymentId);
			statement.setDate(2, paymentDate);
			statement.setString(3, paymentMethod);
			statement.setDouble(4, paymentAmount);
			statement.setInt(5, invoiceId);
			System.out.println(statement.toString());
			boolean statementResultType = statement.execute();
			System.out.println("statementResultType1 :" + statementResultType + "  " + statement.getUpdateCount());
			if (!statementResultType && statement.getUpdateCount() == 1) {
				statement.close();
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean getAccessByUserId(int userId, String api, Connection con) {
		try {
			String query = "{call " + GET_ACCESS_DETAILS + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			System.out.println(statement.toString());
			statement.setInt(1, userId);
			statement.setString(2, api);
			System.out.println(statement.toString());
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			List<UserFeature> userFeatureList = ResultSetObjectMapper.mapRersultSetToObject(resultSet, UserFeature.class);
			statement.close();
			if (userFeatureList != null && userFeatureList.size() == 1) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Feature getRestrictedFeature(String api, Connection con) {
		try {
			String query = "{call " + GET_RESTRICTED_FEATURE + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			System.out.println(statement.toString());
			statement.setString(1, api);
			System.out.println(statement.toString());
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			List<Feature> featureList = ResultSetObjectMapper.mapRersultSetToObject(resultSet, Feature.class);
			statement.close();
			if (featureList != null && featureList.size() == 1) {
				return featureList.get(0);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static boolean updatePassword(String emailId, String password, Connection con) {
		try {
			String query = "{call " + UPDATE_PASSWORD + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			statement.setString(1, emailId);
			statement.setString(2, CommonUtil.hash256Calculator(password));
			System.out.println(statement.toString());
			boolean statementResultType = statement.execute();
			System.out.println("statementResultType1 :" + statementResultType + "  " + statement.getUpdateCount());
			if (!statementResultType && statement.getUpdateCount() == 1) {
				statement.close();
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean provideAccess(int featureId, int userId, Connection con) {
		try {
			String query = "{call " + PROVIDE_ACCESS + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			statement.setInt(1, featureId);
			statement.setInt(2, userId);
			System.out.println(statement.toString());
			boolean statementResultType = statement.execute();
			System.out.println("statementResultType1 :" + statementResultType + "  " + statement.getUpdateCount());
			if (!statementResultType && statement.getUpdateCount() == 1) {
				statement.close();
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean deleteRecord(String tableName, int intId1, int intId2,String strId1, String strId2, Connection con) {
		try {
			String query = "{call " + DELETE_RECORD + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			statement.setString(1, tableName);
			statement.setInt(2, intId1);
			statement.setInt(3, intId2);
			statement.setString(4, strId1);
			statement.setString(5, strId2);
			System.out.println(statement.toString());
			boolean statementResultType = statement.execute();
			System.out.println("statementResultType1 :" + statementResultType + "  " + statement.getUpdateCount());
			if (!statementResultType && statement.getUpdateCount() >= 1) {
				statement.close();
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static List<User> getCustomerList(Connection con) {
		try {
			String query = "{call " + GET_CUSTOMER_LIST + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			System.out.println(statement.toString());
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			List<User> userList = ResultSetObjectMapper.mapRersultSetToObject(resultSet, User.class);
			statement.close();
			if (userList != null && userList.size() >= 1) {
				return userList;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static boolean updateUserDetails(int userId, String firstName, String lastName, String gender, String maritalStatus,
			String stAddress, String city, String state, String zipcode, Connection con) {
		try {
			String query = "{call " + UPDATE_USER_DETAILS + "}";
			System.out.println("Query: " + query);
			CallableStatement statement = con.prepareCall(query);
			statement.setInt(1, userId);
			statement.setString(2, firstName);
			statement.setString(3, lastName);
			statement.setString(4, gender);
			statement.setString(5, maritalStatus);
			statement.setString(6, stAddress);
			statement.setString(7, city);
			statement.setString(8, state);
			statement.setString(9, zipcode);
			System.out.println(statement.toString());
			boolean statementResultType = statement.execute();
			System.out.println("statementResultType1 :" + statementResultType + "  " + statement.getUpdateCount());
			if (!statementResultType && statement.getUpdateCount() == 1) {
				statement.close();
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
