package com.app.group15.persistence;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {

	private static MysqlDataSource dataSource;
	private static String URL;
	private static String PSWD;
	private static String USER_NAME;

	private final static Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());

	public DatabaseManager() {

	}

	private static void getDetails() {
		DatabaseDetails databaseDetails = AwsSecretsManagerUtility.getDatabaseDetails();
		URL = "jdbc:mysql://" + databaseDetails.getHost() + ":" + databaseDetails.getPort() + "/"
				+ databaseDetails.getDbName() + "?useSSL=false&serverTimezone=UTC";
		PSWD = databaseDetails.getPassword();
		USER_NAME = databaseDetails.getUserName();
	}

	public static MysqlDataSource getDataSource() {

		try {

			if (dataSource == null) {
				getDetails();
				dataSource = new MysqlDataSource();
				dataSource.setUrl(URL);
				dataSource.setPassword(PSWD);
				dataSource.setUser(USER_NAME);
			}

		}

		catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

		return dataSource;

	}


}
