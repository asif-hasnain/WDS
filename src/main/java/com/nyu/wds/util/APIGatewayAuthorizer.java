package com.nyu.wds.util;

import java.sql.Connection;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nyu.wds.dbmapper.Authentication;

public class APIGatewayAuthorizer implements RequestHandler<TokenAuthorizerContext, AuthPolicy> {
	Connection con = null;

	@Override
	public AuthPolicy handleRequest(TokenAuthorizerContext input, Context context) {
		String token = input.getAuthorizationToken();
		String methodArn = input.getMethodArn();
		String[] arnPartials = methodArn.split(":");
		String region = arnPartials[3];
		String awsAccountId = arnPartials[4];
		String[] apiGatewayArnPartials = arnPartials[5].split("/");
		String restApiId = apiGatewayArnPartials[0];
		String stage = apiGatewayArnPartials[1];
		System.out.println("token :" + input.getAuthorizationToken());
		try {
			if (CommonUtil.isValidString(token)) {
				String[] split = token.split("#");
				String methodSplit[] = input.getMethodArn().split("/");
				String api = methodSplit[methodSplit.length - 1];
				if (split != null && split.length == 3) {
					con = JDBCConnection.getJDBCCOnnection(con, 0);
					Authentication auth = DBUtil.getAuthenticationDetails(split[1], con);
					int userId = Integer.parseInt(split[2]);
					if (auth != null && auth.getUser_id() == userId) {
						if (split[0].equalsIgnoreCase("c") && DBUtil.getRestrictedFeature(api, con) == null) {
							return new AuthPolicy("1234", AuthPolicy.PolicyDocument.getAllowAllPolicy(region,
									awsAccountId, restApiId, stage));
						} else if (split[0].equalsIgnoreCase("e") && DBUtil.getAccessByUserId(userId, api, con)) {

							return new AuthPolicy("1234", AuthPolicy.PolicyDocument.getAllowAllPolicy(region,
									awsAccountId, restApiId, stage));

						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AuthPolicy("1234",
				AuthPolicy.PolicyDocument.getDenyAllPolicy(region, awsAccountId, restApiId, stage));

	}
}