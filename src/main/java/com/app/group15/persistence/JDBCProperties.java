package com.app.group15.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCProperties {

	private static Properties connectionProperties;
	private final static Logger LOGGER = Logger.getLogger(JDBCProperties.class.getName());

	public static Properties getConnectionData() {

		connectionProperties = new Properties();

		final String fileName = "src/main/resources/application.properties";

		try (FileInputStream in = new FileInputStream(fileName)) {
			connectionProperties.load(in);
		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}

		return connectionProperties;
	}
}
