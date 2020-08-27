package com.nyu.wds.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;

<<<<<<< HEAD
import org.acegisecurity.util.EncryptionUtils;
import org.acegisecurity.util.EncryptionUtils.EncryptionException;
=======
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
>>>>>>> eb870273ea50d1c2acde64e9a396df908e76c002
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
<<<<<<< HEAD
	
	public static String encrypt(String value) throws EncryptionException, UnsupportedEncodingException {
		return URLEncoder.encode(EncryptionUtils.encrypt("987654321012345678909876", value), "UTF-8");
	}

	public static String decryptKey(String encryptedKey) throws EncryptionException, UnsupportedEncodingException {
		return (EncryptionUtils.decrypt("987654321012345678909876", URLDecoder.decode(encryptedKey)));
	}
=======
	public static String getSecret(String secretName, String region) {


		// Create a Secrets Manager client
		AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
				.withRegion(region)
				.build();

		// In this sample we only handle the specific exceptions for the 'GetSecretValue' API.
		// See https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
		// We rethrow the exception by default.

		String secret = "", decodedBinarySecret="";
		GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
				.withSecretId(secretName);
		GetSecretValueResult getSecretValueResult = null;

		try {
			getSecretValueResult = client.getSecretValue(getSecretValueRequest);
		} catch (DecryptionFailureException e) {
			// Secrets Manager can't decrypt the protected secret text using the provided KMS key.
			// Deal with the exception here, and/or rethrow at your discretion.
			throw e;
		} catch (InternalServiceErrorException e) {
			// An error occurred on the server side.
			// Deal with the exception here, and/or rethrow at your discretion.
			throw e;
		} catch (InvalidParameterException e) {
			// You provided an invalid value for a parameter.
			// Deal with the exception here, and/or rethrow at your discretion.
			throw e;
		} catch (InvalidRequestException e) {
			// You provided a parameter value that is not valid for the current state of the resource.
			// Deal with the exception here, and/or rethrow at your discretion.
			throw e;
		} catch (ResourceNotFoundException e) {
			// We can't find the resource that you asked for.
			// Deal with the exception here, and/or rethrow at your discretion.
			throw e;
		}

		// Decrypts secret using the associated KMS CMK.
		// Depending on whether the secret is a string or binary, one of these fields will be populated.
		if (getSecretValueResult.getSecretString() != null) {
			secret = getSecretValueResult.getSecretString();
			return secret;
		}
		else {
			decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
			return decodedBinarySecret;
		}


	}
	
>>>>>>> eb870273ea50d1c2acde64e9a396df908e76c002
	
}
