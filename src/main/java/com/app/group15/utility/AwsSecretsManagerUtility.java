package com.app.group15.utility;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.logging.Level;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.DecryptionFailureException;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.amazonaws.services.secretsmanager.model.InternalServiceErrorException;
import com.amazonaws.services.secretsmanager.model.InvalidParameterException;
import com.amazonaws.services.secretsmanager.model.InvalidRequestException;
import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import com.app.group15.config.AppConfig;
import com.app.group15.model.DatabaseDetails;
import com.app.group15.persistence.AwsSecretKey;
import com.app.group15.persistence.Environments;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AwsSecretsManagerUtility {
	
	private static DatabaseDetails databaseDetails;
	
	private static String getKeyFromEnvProperties() {
		
		if(AppConfig.getSingletonAppConfig().getProperties().get("spring.profiles.active").equals(Environments.DEV.getEnvironment())) {
			return AwsSecretKey.DEVINT.getKey();
		} else if(AppConfig.getSingletonAppConfig().getProperties().get("spring.profiles.active").equals(Environments.TEST.getEnvironment())) {
			return AwsSecretKey.TEST.getKey();
		}
		else if(AppConfig.getSingletonAppConfig().getProperties().get("spring.profiles.active").equals(Environments.PROD.getEnvironment())) {
			return AwsSecretKey.PROD.getKey();
		}
		return null;
		
	}
	public static DatabaseDetails getDatabaseDetails() {

	    final String secretName = getKeyFromEnvProperties();
	    final String region = "us-east-2";
	    final String endPoints="secretsmanager.us-east-2.amazonaws.com";
	    BasicAWSCredentials basic=new BasicAWSCredentials("AKIAIFGQZFS4BYYNVAAQ","5cMkTXomxQkEAN3j9uGNFJm4OSMjR9iW19S9Cdjs");
	    AWSStaticCredentialsProvider prov =new AWSStaticCredentialsProvider(basic);
	    AwsClientBuilder.EndpointConfiguration  config  =  new  AwsClientBuilder.EndpointConfiguration(endPoints,region);
	    AWSSecretsManagerClientBuilder  clientBuilder  =  AWSSecretsManagerClientBuilder.standard();
	    clientBuilder.setEndpointConfiguration(config);
	    clientBuilder.setCredentials(prov);
	    AWSSecretsManager  client  =  clientBuilder.build();    
	    String secret, decodedBinarySecret;
	    GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
	                    .withSecretId(secretName);
	    GetSecretValueResult getSecretValueResult = null;
	    ObjectMapper  objectMapper  =  new  ObjectMapper();
	    JsonNode  secretsJson  =  null;
	     ByteBuffer  binarySecretData;
	    try {
	    	
	        getSecretValueResult = client.getSecretValue(getSecretValueRequest);
	    } catch (DecryptionFailureException e) {
	       GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
	    } catch (InternalServiceErrorException e) {
	    	 GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
	        
	    } catch (InvalidParameterException e) {
	    	 GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
	    } catch (InvalidRequestException e) {
	    	 GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
	    } catch (ResourceNotFoundException e) {
	    	 GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
	    }
	    if (getSecretValueResult.getSecretString() != null) {
	        secret = getSecretValueResult.getSecretString();
	        try {
				secretsJson  =  objectMapper.readTree(secret);
			} catch (JsonProcessingException e) {
				 GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
			}    
	    }   
	        
	    else {
	        decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
	    }
	    
	    databaseDetails=new DatabaseDetails();
	    databaseDetails.setDbName(secretsJson.get("dbname").textValue());
	    databaseDetails.setHost(secretsJson.get("host").textValue());
	    databaseDetails.setPassword( secretsJson.get("password").textValue());
	    databaseDetails.setPort(secretsJson.get("port").textValue());
	    databaseDetails.setUserName(secretsJson.get("username").textValue());
	    return databaseDetails;
	    
	}

}
