package com.nyu.wds;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;
import java.util.Random;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.dbmapper.Driver;
import com.nyu.wds.dbmapper.Home;
import com.nyu.wds.dbmapper.Vehicle;
import com.nyu.wds.util.CommonUtil;
import com.nyu.wds.util.Constant;
import com.nyu.wds.util.DBUtil;
import com.nyu.wds.util.JDBCConnection;

public class ApplyInsurance implements RequestHandler<ApplyInsuranceRequest, ApplyInsuranceResponse>{
	Connection con = null;
	@Override
	public ApplyInsuranceResponse handleRequest(ApplyInsuranceRequest input, Context context) {
		System.out.println("input : " + input);
		if(input == null || !CommonUtil.isValidString(input.getPolicy_type())|| !(input.getPolicy_type().equalsIgnoreCase(DBUtil.Home) || input.getPolicy_type().equalsIgnoreCase(DBUtil.Vehicle))) {
			return new ApplyInsuranceResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		}
		con = JDBCConnection.getJDBCCOnnection(con, 0);
		try {
			con.setAutoCommit(false);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		Random rand = new Random();
		if(input.getPolicy_type().equalsIgnoreCase(DBUtil.Home)) {
			if(input.getHomeList() != null && input.getHomeList().size()>0) {
				double totalPurchaseValue = 0;
				for(Home home : input.getHomeList()) {
					if(home == null || home.getPurchase_value() == 0 || home.getArea() == 0 && !CommonUtil.isValidString(home.getHome_type())
							|| !(home.getHome_type().equalsIgnoreCase("S") ||home.getHome_type().equalsIgnoreCase("M") || home.getHome_type().equalsIgnoreCase("C") || home.getHome_type().equalsIgnoreCase("T"))
							|| !CommonUtil.isValidString(home.getSt_address()) || !CommonUtil.isValidString(home.getCity()) || !CommonUtil.isValidString(home.getState())
							|| !CommonUtil.isValidString(home.getZipcode())) {
						return new ApplyInsuranceResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
					}
					totalPurchaseValue += home.getPurchase_value();
				}
				
				int policyId = rand.nextInt(899999999)+100000000;
				Calendar nextYear = Calendar.getInstance();
				nextYear.add(Calendar.YEAR, 1);
				boolean addInsurance = DBUtil.addInsurance(policyId, new java.sql.Date(Calendar.getInstance().getTimeInMillis()), 
						new java.sql.Date(nextYear.getTimeInMillis()), totalPurchaseValue/50, DBUtil.Home, input.getUserId(), 
						0, totalPurchaseValue/500, con);
				if(!addInsurance) {
					try {
						System.out.println("Roll back due to error while saving insurance");
						con.rollback();
						con.setAutoCommit(true);
					} catch (SQLException e) {
						e.printStackTrace();
						try {
							con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					return new ApplyInsuranceResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
				}
				boolean addHome = DBUtil.addHome(input.getHomeList(), policyId, con);
				if(!addHome) {
					try {
						System.out.println("Roll back due to error while saving home");
						con.rollback();
						con.setAutoCommit(true);
					} catch (SQLException e) {
						e.printStackTrace();
						try {
							con.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					return new ApplyInsuranceResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
			}
				
				try {
					con.commit();
					con.setAutoCommit(true);
					System.out.println("Commited successfully and connection set to autoCommit :" + con.getAutoCommit());

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return new ApplyInsuranceResponse(new Response(Constant.SUCCESS, Constant.SUCCESS_MSG),policyId);			

			}
		} else if(input.getPolicy_type().equalsIgnoreCase(DBUtil.Vehicle)) {
			if(input.getVehicle()!= null && input.getVehicle().size()>0) {
				int count  = 0;
				for(Vehicle vehicle : input.getVehicle()) {
					if(vehicle == null || !CommonUtil.isValidString(vehicle.getVin()) || !CommonUtil.isValidString(vehicle.getMake()) ||
							!CommonUtil.isValidString(vehicle.getModel()) || vehicle.getYear() == 0 ||
							!CommonUtil.isValidString(vehicle.getVehicle_status())) {
						return new ApplyInsuranceResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
					}
					if(vehicle.getDriverList() != null && vehicle.getDriverList().size()>0) {
						for(Driver driver : vehicle.getDriverList()) {
							if(driver == null || !CommonUtil.isValidString(driver.getLicence_number()) || !CommonUtil.isValidString(driver.getFirst_name())||
									!CommonUtil.isValidString(driver.getLast_name())) {
								return new ApplyInsuranceResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
							}
						}
					}
					count++;
				}
					int policyId = rand.nextInt(899999999)+100000000;
					Calendar nextYear = Calendar.getInstance();
					nextYear.add(Calendar.YEAR, 1);
					boolean addInsurance = DBUtil.addInsurance(policyId, new java.sql.Date(Calendar.getInstance().getTimeInMillis()), 
							new java.sql.Date(nextYear.getTimeInMillis()), 100*count, DBUtil.Vehicle, input.getUserId(), 
							0, 10*count, con);
					if(!addInsurance) {
						try {
							System.out.println("Roll back due to error while saving insurance");
							con.rollback();
							con.setAutoCommit(true);
						} catch (SQLException e) {
							e.printStackTrace();
							try {
								con.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						return new ApplyInsuranceResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
					}
					
					boolean addVehicle = DBUtil.addVehicleAndDriver(input.getVehicle(), policyId, con);
					if(!addVehicle) {
						try {
							System.out.println("Roll back due to error while saving Vehicle");
							con.rollback();
							con.setAutoCommit(true);
						} catch (SQLException e) {
							e.printStackTrace();
							try {
								con.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						return new ApplyInsuranceResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG));
				}
					try {
						con.commit();
						con.setAutoCommit(true);
						System.out.println("Commited successfully and connection set to autoCommit :" + con.getAutoCommit());

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return new ApplyInsuranceResponse(new Response(Constant.SUCCESS, Constant.SUCCESS_MSG),policyId);			
					
			}
			return new ApplyInsuranceResponse(new Response(Constant.INVALID_INPUT, Constant.INVALID_INPUT_MSG));
		}
		return new ApplyInsuranceResponse(new Response(Constant.DEFAULT_ERROR, Constant.DEFAULT_ERROR_MSG)); 
	}

}
