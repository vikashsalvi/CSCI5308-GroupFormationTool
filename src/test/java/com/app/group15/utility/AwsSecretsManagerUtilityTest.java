package com.app.group15.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;

import com.app.group15.persistence.AwsSecretKey;
import com.app.group15.persistence.Environments;

public class AwsSecretsManagerUtilityTest {

	@Test
	public void getKeyFromEnvProperties() {

		Properties prop = new Properties();
		String propertyFilePath = "src/main/resources/application.properties";
		try (FileInputStream in = new FileInputStream(propertyFilePath)) {
			prop.load(in);
		} catch (IOException ex) {
			GroupFormationToolLogger.log(Level.SEVERE, ex.getMessage(), ex);
		}

		if (prop.get("spring.profiles.active").equals(Environments.DEV.getEnvironment())) {
			assertEquals(AwsSecretKey.DEVINT.getKey(), "qa-dev-secret");
		} else if (prop.get("spring.profiles.active").equals(Environments.TEST.getEnvironment())) {
			assertEquals(AwsSecretKey.TEST.getKey(), "qa-test-secret");
		} else if (prop.get("spring.profiles.active").equals(Environments.PROD.getEnvironment())) {
			assertEquals(AwsSecretKey.PROD.getKey(), "qa-prod-secret");
		}

	}
}
