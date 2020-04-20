package com.nyu.wds.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.joda.time.Instant;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.CreateAliasRequest;
import com.amazonaws.services.lambda.model.FunctionConfiguration;
import com.amazonaws.services.lambda.model.ListFunctionsRequest;
import com.amazonaws.services.lambda.model.ListFunctionsResult;
import com.amazonaws.services.lambda.model.ResourceNotFoundException;
import com.amazonaws.services.lambda.model.UpdateAliasRequest;
import com.amazonaws.services.lambda.model.UpdateFunctionCodeRequest;
import com.amazonaws.services.lambda.model.UpdateFunctionCodeResult;
import com.amazonaws.services.lambda.model.UpdateFunctionConfigurationRequest;
import com.amazonaws.services.lambda.model.UpdateFunctionConfigurationResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class LambdaUpdateCode {

	private static String awsCredentialsProfileName = "default";

	//private static String lambdaLayer = "arn:aws:lambda:us-east-2:283790809010:layer:layer1:2";


	// From POM FILE
	private static String projectPOMArtifactId      = "wds";
	private static String projectPOMVersion         = "0.0.1-SNAPSHOT"; 
	private static String projectPOMPackaging       = "jar"; 
	private static String mavenPackagedZipFileName  = projectPOMArtifactId + "-" + projectPOMVersion + "." + projectPOMPackaging;
	private static String mavenPackagedZipFilePath  = "target/" + mavenPackagedZipFileName;


	private static List<String> lambdaFunctionsToupload = Arrays.asList("WDSTest");

	private static String s3BucketName           = "lambda-functions-287390809010";
	private static String lambdaCodeZipFileName  = Instant.now() + ".zip";
//	private static String aliasNameAndroid   			 = Constants.IS_PROD ? "Android-1-4"              : "Android-1-4";
//	private static String aliasNameIOS   			 = Constants.IS_PROD ? "iOS-Version-1-1"              : "iOS-Version-1-1";
	private static Regions region   			 = 	Regions.US_EAST_2;

	public static void main(String[] args) throws IOException, InterruptedException, MavenInvocationException {
		createLambdaCodeZipFile();
		System.out.println("*************************************************************");
		
		uploadZipFileToS3(lambdaCodeZipFileName, mavenPackagedZipFilePath);
		updateCodeAndAlias();
	}

	// Create
	private static void createLambdaCodeZipFile() throws IOException, InterruptedException, MavenInvocationException {
		InvocationRequest request = new DefaultInvocationRequest();
		request.setGoals(Arrays.asList("package"));
		request.setBaseDirectory(new File(System.getProperty("user.dir")));
		DefaultInvoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File(System.getenv("HOME") + "/Applications/apache-maven-3.6.2/"));
		InvocationResult result = invoker.execute(request);
		if (result.getExitCode() != 0) { throw new RuntimeException("Could not create lambda package file."); }
	}

	private static void uploadZipFileToS3(String fileName, String mvnRelativePath) throws FileNotFoundException {
		System.out.println("Uploading " + fileName);
		AmazonS3 awsLambdaClient = AmazonS3ClientBuilder.standard()
				.withRegion(region)
				.withCredentials(new AWSStaticCredentialsProvider(new ProfileCredentialsProvider(awsCredentialsProfileName).getCredentials()))
				.build();
		File file = new File(System.getProperty("home.dir"), mvnRelativePath);
		System.out.println("file.canRead() : "+file.canRead());
		int fileSize = (int) (file.length() / (1024.0));
		System.out.println("Upload size: "+fileSize + " KB");
		awsLambdaClient.putObject(s3BucketName, fileName, file);
		System.out.println("Uploaded " + fileName);
	}

	private static void updateCodeAndAlias() {




		AWSLambda awsLambdaClient = AWSLambdaClientBuilder.standard().withRegion(region).withCredentials(new AWSStaticCredentialsProvider(new ProfileCredentialsProvider(awsCredentialsProfileName).getCredentials())).build();

		ArrayList<FunctionConfiguration> lambdaFunctions = new ArrayList<FunctionConfiguration>();

		String nextMarker = null;

		boolean isDone = false;

		while (!isDone) {

			ListFunctionsResult listFunctionsResult = (nextMarker != null) ? awsLambdaClient.listFunctions(new ListFunctionsRequest().withMarker(nextMarker)) : awsLambdaClient.listFunctions();

			lambdaFunctions.addAll(listFunctionsResult.getFunctions());

			nextMarker = listFunctionsResult.getNextMarker();

			System.out.println("Size: "  + " " + listFunctionsResult.getFunctions().size());

			isDone = (nextMarker == null);

		}



		System.out.println("Total Size: " + lambdaFunctions.size());

		List<FunctionConfiguration> lambdaFunctionToUdpate = 

				lambdaFunctions.stream().filter( f -> { return lambdaFunctionsToupload.contains(f.getFunctionName()); }).collect(Collectors.toList());

		System.out.println("Update Size: " + lambdaFunctions.size());

		System.out.println("Lambda Functions To Udpate: " + lambdaFunctionToUdpate.size());



		lambdaFunctionToUdpate.forEach(lambdaFunction -> {

			System.out.print("Lambda function '" + lambdaFunction.getFunctionName() + "' ");

			try {
				UpdateFunctionConfigurationRequest updateFunctionConfigurationRequest = new UpdateFunctionConfigurationRequest().withFunctionName(lambdaFunction.getFunctionName());
				UpdateFunctionConfigurationResult updateFunctionConfigurationResult =  awsLambdaClient.updateFunctionConfiguration(updateFunctionConfigurationRequest);
				System.out.println(" Created Layer " + updateFunctionConfigurationResult.getLayers().toString());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			UpdateFunctionCodeRequest updateFunctionCodeRequest  = new UpdateFunctionCodeRequest().withFunctionName(lambdaFunction.getFunctionName()).withPublish(true).withS3Bucket(s3BucketName).withS3Key(lambdaCodeZipFileName);

			UpdateFunctionCodeResult updateCodeResult			 = awsLambdaClient.updateFunctionCode(updateFunctionCodeRequest);

			System.out.println("code updated with version : " + updateCodeResult.getVersion() + ", handler : " + updateCodeResult.getHandler());



//			try {
//				UpdateAliasRequest updateAliasRequest = new UpdateAliasRequest().withFunctionName(lambdaFunction.getFunctionName()).withFunctionVersion(updateCodeResult.getVersion()).withName(aliasNameAndroid);
//
//				awsLambdaClient.updateAlias(updateAliasRequest);
//
//				System.out.println(" Updated alias " + aliasNameAndroid  + " , Version: " + updateCodeResult.getVersion());
//
//			} catch (ResourceNotFoundException e) {
//
//				CreateAliasRequest createAliasRequest = new CreateAliasRequest().withFunctionName(lambdaFunction.getFunctionName()).withFunctionVersion(updateCodeResult.getVersion()).withName(aliasNameAndroid);
//
//				awsLambdaClient.createAlias(createAliasRequest);
//
//				System.out.println(" Created alias " + aliasNameAndroid  + " Version: " + updateCodeResult.getVersion());
//
//			}
//			try {
//
//				UpdateAliasRequest updateAliasRequest = new UpdateAliasRequest().withFunctionName(lambdaFunction.getFunctionName()).withFunctionVersion(updateCodeResult.getVersion()).withName(aliasNameIOS);
//
//				awsLambdaClient.updateAlias(updateAliasRequest);
//
//				System.out.println(" Updated alias " + aliasNameIOS  + " , Version: " + updateCodeResult.getVersion());
//
//			} catch (ResourceNotFoundException e) {
//
//				CreateAliasRequest createAliasRequest = new CreateAliasRequest().withFunctionName(lambdaFunction.getFunctionName()).withFunctionVersion(updateCodeResult.getVersion()).withName(aliasNameIOS);
//
//				awsLambdaClient.createAlias(createAliasRequest);
//
//				System.out.println(" Created alias " + aliasNameIOS  + " Version: " + updateCodeResult.getVersion());
//
//			}



			System.out.print("\n");

		});

	}

}