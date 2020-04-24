package com.nyu.wds.util;

import java.util.HashMap;
import java.sql.Connection;
import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.ProfileDetailsResponse;
import com.nyu.wds.Response;
import com.nyu.wds.dbmapper.Authentication;
import com.nyu.wds.dbmapper.User;

public class APIGatewayAuthorizer implements RequestHandler<TokenAuthorizerContext, AuthPolicy> {
	Connection con = null;
	@Override
	public AuthPolicy handleRequest(TokenAuthorizerContext input, Context context) {
		String token = input.getAuthorizationToken();
		String inputUserSearch = "";
		String methodArn = input.getMethodArn();
		String[] arnPartials = methodArn.split(":");
		String region = arnPartials[3];
		String awsAccountId = arnPartials[4];
		String[] apiGatewayArnPartials = arnPartials[5].split("/");
		String restApiId = apiGatewayArnPartials[0];
		String stage = apiGatewayArnPartials[1];
		String httpMethod = apiGatewayArnPartials[2];
		String resource = "";
		System.out.println("token :" + input.getAuthorizationToken());
		try {
			if(CommonUtil.isValidString(token)) {
				String[] split = token.split("#");
				if(split!=null && split.length ==3) {
					con = JDBCConnection.getJDBCCOnnection(con, 0);
					Authentication auth = DBUtil.getAuthenticationDetails(split[1], con);
					int userId = Integer.parseInt(split[2]);
					if(auth != null && auth.getUser_id() == userId) {
						if(split[0].equalsIgnoreCase("c")) {
							return new AuthPolicy("1234", AuthPolicy.PolicyDocument.getAllowAllPolicy(region, awsAccountId, restApiId, stage));
						} else if(split[0].equalsIgnoreCase("e")) {
							
						}
					}
				}
			}


//			token = input.getAuthorizationToken();
//			System.out.println("Hello in  APIGatewayAuthorizer: "+ token);
//			String principalId = "";
//			User user = null;
//			String split[] = input.getMethodArn().split("/");
//			if(split != null && split.length != 0)
//				System.out.println("MethodARN user " + split[split.length-1]);
//			if(!CommonUtil.isValidString(token)) {
//					System.out.println("Dashboard");
//					DB_AuthenticatedDevice dbAuth = new DB_AuthenticatedDevice().fetchDB_AuthenticatedDeviceByRefreshToken(token);
//					if(dbAuth == null) {
//						System.out.println("Authorization Failed");
//						return new AuthPolicy("1234", AuthPolicy.PolicyDocument.getDenyAllPolicy(region, awsAccountId, restApiId, stage));
//					}
//					dbOpsUser = new DB_OpsUser().fetchOpsUser(dbAuth.getUserID());
//
//			}
//
//
//			if(dbOpsUser == null) {
//				System.out.println("User not found in OPSUser Table for : " + inputUserSearch );
//				throw new RuntimeException("Unauthorized");
//			}
//			System.out.println("DBOPS user " + dbOpsUser);
//
//			DB_OpsFeature opsFeature = new DB_OpsFeature().fetchFeatureByAPI(split[split.length-1]);
//			DB_OpsAccess opsAccess = DB_OpsAccess.fetchOpsAccessFromOpsIDAndFeatureID(dbOpsUser.getOpsID(), opsFeature.getFeatureID());
//			if (opsAccess == null) {
//				System.out.println("Access denied for user : " + dbOpsUser.getName() + " Method : " + split[split.length-1]  );
//				throw new RuntimeException("Unauthorized");
//			}
//
//			// Create the principal user identifier associated with the token
//			// if the token is valid, a policy should be generated which will allow or deny access to the client
//			principalId = dbOpsUser.getOpsID();
//			if(token.startsWith(Constants.DSA_HOMECAPITAL)) {
//				if(user == null)throw new RuntimeException("Unauthorized");
//				else principalId = user.getUserID();
//			}
//
//			if (apiGatewayArnPartials.length == 4) {
//				resource = apiGatewayArnPartials[3];
//			}
//			return new AuthPolicy(principalId, AuthPolicy.PolicyDocument.getAllowAllPolicy(region, awsAccountId, restApiId, stage));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new AuthPolicy("1234", AuthPolicy.PolicyDocument.getDenyAllPolicy(region, awsAccountId, restApiId, stage));

	}
}