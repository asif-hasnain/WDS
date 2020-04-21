package com.nyu.wds.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import org.bouncycastle.util.encoders.Hex;

public class CommonUtil {
	public static String hash256Calculator(String inputString) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		byte[] hash = digest.digest(
				inputString.getBytes(StandardCharsets.UTF_8));
		return new String(Hex.encode(hash));
	}
	
	public static boolean isValidString(String input){
		return (input != null && !input.equals("") && !input.equals("null") && !input.trim().isEmpty());
	}
	
	public static boolean isValidName(String name) {
		try {
			if(!isValidString(name))return false;
			Matcher matcher = Constant.VALID_NAME_REGEX.matcher(name);
			return matcher.find();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isValidEmail(String email) {
		try {
			if(!isValidString(email)) return false;
			Matcher matcher = Constant.VALID_EMAIL_ADDRESS_REGEX.matcher(email);
			return matcher.find();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
}
