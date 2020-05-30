package com.app.group15.persistence;

import java.sql.Connection;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.app.group15.config.AppConfig;
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

	private MysqlDataSource getDataSource() {

		try {
			dataSource = new MysqlDataSource();
			dataSource.setUrl(JDBCProperties.getConnectionData().getProperty(URL));
			dataSource.setPassword(JDBCProperties.getConnectionData().getProperty(PSWD));
			dataSource.setUser(JDBCProperties.getConnectionData().getProperty(USER_NAME));
		}

		catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

		return dataSource;

	}

	public Connection getConnection() {
		try {
			connection = getDataSource().getConnection();

		}

		catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		return connection;
	}

}
