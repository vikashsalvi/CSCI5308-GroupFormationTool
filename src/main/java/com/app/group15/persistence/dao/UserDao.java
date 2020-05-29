package com.app.group15.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.app.group15.persistence.entity.PersistenceEntity;
import com.app.group15.persistence.entity.UserEntity;
import com.app.group15.persistence.injectors.UserRoleDaoInjectorInterface;

@SuppressWarnings("rawtypes")
public class UserDao implements Dao, UserRoleDaoInjectorInterface {
	private final static Logger LOGGER = Logger.getLogger(UserDao.class.getName());
	private Connection connection;
	private UserRoleDao userRoleDao;

	public UserDao() {

	}

	@Override
	public UserEntity get(int id) {
		String query = "SELECT * FROM table_users WHERE id=?";
		UserEntity user = new UserEntity();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					user.setBannerId(result.getString("banner_id"));
					user.setEmail(result.getString("email"));
					user.setFirstName(result.getString("first_name"));
					user.setLastName(result.getString("last_name"));
					user.setId(id);

				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return user;
	}

	public UserEntity getUserByBannerId(String bannerId) {
		String query = "SELECT * FROM table_users WHERE banner_id=?";
		UserEntity user = new UserEntity();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, bannerId);
			try (ResultSet result = statement.executeQuery()) {
				System.out.println(result);
				while (result.next()) {
					user.setBannerId(result.getString("banner_id"));
					user.setEmail(result.getString("email"));
					user.setFirstName(result.getString("first_name"));
					user.setLastName(result.getString("last_name"));
					user.setId(result.getInt("id"));
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return user;
	}

	@Override
	public ArrayList<UserEntity> getAll() {
		String query = "SELECT * FROM table_users";
		ArrayList<UserEntity> usersList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(query);
			 ResultSet result = statement.executeQuery()) {
			while (result.next()) {
				UserEntity user = new UserEntity();
				user.setBannerId(result.getString("banner_id"));
				user.setEmail(result.getString("email"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setId(result.getInt("id"));
				usersList.add(user);
			}

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return usersList;
	}

	@Override
	public void injectConnection(Connection connection) {
		this.connection = connection;

	}

	@Override
	public int save(PersistenceEntity user) {
		return 0;

	}

	public int saveUser(UserEntity user, String role) {
		String query = "INSERT INTO table_users(first_name,last_name,email, banner_id,password) " + "VALUES(?,?,?,?,?)";
		int userId = 0;
		try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			connection.setAutoCommit(false);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getBannerId());
			statement.setString(5, user.getPassword());
			statement.executeUpdate();
			connection.commit();
			try (ResultSet result = statement.getGeneratedKeys()) {

				if (result.first()) {

					userId = result.getInt(1);
				}
			}
			userRoleDao.addRole(userId, role);

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

		return userId;
	}

	@Override
	public void update(PersistenceEntity user, int id) {
		UserEntity userEntity = (UserEntity) user;
		String query = "UPDATE table_users SET first_name=?,last_name=?,email=? WHERE id=?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			connection.setAutoCommit(false);
			statement.setString(1, userEntity.getFirstName());
			statement.setString(2, userEntity.getLastName());
			statement.setString(3, userEntity.getEmail());
			statement.setInt(4, id);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

	}

	public void updateUserRole(int userId, String role) {
		String query = "UPDATE table_user_role_mapper SET role_id=? WHERE user_id=?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			connection.setAutoCommit(false);
			statement.setInt(1, userRoleDao.getRoleId(role));
			statement.setInt(2, userId);
			statement.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public void updateUserPassword(int userId, String password) {
		String query = "UPDATE table_users SET password=? WHERE id=?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			connection.setAutoCommit(false);
			statement.setString(1, password);
			statement.setInt(2, userId);
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}

		}

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void injectUserRoleDao(Dao userRoleDao) {
		this.userRoleDao = (UserRoleDao) userRoleDao;

	}

}
