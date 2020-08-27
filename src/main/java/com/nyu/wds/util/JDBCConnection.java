package com.nyu.wds.util;

import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

	public static Connection getJDBCCOnnection(Connection con, int retry) {
		if(retry == Constant.JDBC_CONNECTION_MAX_RETRY) {
			return null;
		}
		if(con == null) {
			 System.out.println("Creating new JDBC Connection");
		   try {
		   	String secret = CommonUtil.getSecret("wds", "us-east-2");
		   	DBCredMapper cred = new Gson().fromJson(secret,DBCredMapper.class);
			   con = DriverManager.getConnection(
					   "jdbc:" + cred.getEngine()+"://"+cred.getHost()+"/"+cred.getDbInstanceIdentifier(), cred.getUsername(), cred.getPassword());
			} catch (SQLException e) {
				System.out.println("Error while creating new JDBC Connection");
				e.printStackTrace();
			}
		 }
			 
		System.out.println("Testing JDBC connection");
		try {
			if(!con.isValid(0)) {
				getJDBCCOnnection(con, retry+1);
			}
		} catch (SQLException e) {
			System.out.println("Error while validating JDBC Connection");
			e.printStackTrace();
		}
		return con;
		
	}
	
}
