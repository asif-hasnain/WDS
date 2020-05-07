package com.nyu.wds.util;

import java.io.UnsupportedEncodingException;

import org.acegisecurity.util.EncryptionUtils.EncryptionException;

public class TestMain {

	public static void main(String[] args) {
//		 String query = "{call " + Constant.TEST_STORE_PROCEDURE + "}";
//		 query = query.replaceFirst("#", "11111716");
//		 System.out.println("Query: "+ query);
		 
		try {
			String calculatedKey = CommonUtil.encrypt("c#12345");
			System.out.println(calculatedKey);
			String recalculatedKey = CommonUtil.decryptKey(calculatedKey);
			System.out.println(recalculatedKey);
		} catch (EncryptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
