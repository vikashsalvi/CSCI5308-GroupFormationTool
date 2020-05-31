package com.app.group15.persistence;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {

	private static MysqlDataSource dataSource;
	private static Connection connection;
	final static String URL = "db.url";
	final static String PSWD = "db.password";
	final static String USER_NAME = "db.user";

	private final static Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());


	public DatabaseManager() {

	}

	private static MysqlDataSource getDataSource() {

		try {

			if (dataSource == null) {
				dataSource = new MysqlDataSource();
				dataSource.setUrl(JDBCProperties.getConnectionData().getProperty(URL));
				dataSource.setPassword(JDBCProperties.getConnectionData().getProperty(PSWD));
				dataSource.setUser(JDBCProperties.getConnectionData().getProperty(USER_NAME));
			}

		}

		catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

		return dataSource;

	}

	public static Connection getConnection() {
		try {
			if (connection == null) {
				connection = getDataSource().getConnection();
			}
		}

		catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		return connection;
	}

}
