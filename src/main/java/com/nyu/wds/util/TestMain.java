package com.nyu.wds.util;

public class TestMain {

	public static void main(String[] args) {
//		 String query = "{call " + Constant.TEST_STORE_PROCEDURE + "}";
//		 query = query.replaceFirst("#", "11111716");
//		 System.out.println("Query: "+ query);
		 
		String calculatedKey = CommonUtil.hash256Calculator("12345");
		System.out.println(calculatedKey);
	}

}
