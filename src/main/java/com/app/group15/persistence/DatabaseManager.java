package com.app.group15.persistence;

import java.sql.Connection;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DatabaseManager {

	private static MysqlDataSource dataSource;
	private static Connection connection;
	final static String URL = "db.url";
	final static String PSWD = "db.password";
	final static String USER_NAME = "db.user";

	private final static Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());

	public DatabaseManager() {

	}

	public static MysqlDataSource getDataSource() {

		try {
			dataSource = new MysqlDataSource();
			dataSource.setUrl(JdbcProperties.getConnectionData().getProperty(URL));
			dataSource.setPassword(JdbcProperties.getConnectionData().getProperty(PSWD));
			dataSource.setUser(JdbcProperties.getConnectionData().getProperty(USER_NAME));

		}

		catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

		return dataSource;

	}

	public static Connection getConnection() {
		try {
			connection = getDataSource().getConnection();

		}

		catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		return connection;
	}

}
