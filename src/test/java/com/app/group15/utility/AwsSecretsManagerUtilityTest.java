package com.app.group15.utility;

import com.app.group15.persistence.AwsSecretKey;
import com.app.group15.persistence.Environments;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		String springPropertyEnv = System.getProperty("spring.profiles.active");
		;
		if (null == springPropertyEnv) {
			springPropertyEnv = "DEVINT";
		}

		if (springPropertyEnv.equals(Environments.DEV.getEnvironment())) {
			assertEquals(AwsSecretKey.DEVINT.getKey(), "qa-dev-secret");
		} else if (springPropertyEnv.equals(Environments.TEST.getEnvironment())) {
			assertEquals(AwsSecretKey.TEST.getKey(), "qa-test-secret");
		} else if (springPropertyEnv.equals(Environments.PROD.getEnvironment())) {
			assertEquals(AwsSecretKey.PROD.getKey(), "qa-prod-secret");
		}

	}
}
