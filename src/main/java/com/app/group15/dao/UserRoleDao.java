package com.app.group15.dao;

import com.app.group15.ExceptionHandler.AdminNotAllowedException;
import com.app.group15.model.Persistence;
import com.app.group15.model.UserRoles;
import com.app.group15.persistence.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("rawtypes")
public class UserRoleDao implements Dao {

	private Connection connection;

	public UserRoleDao() {

	}

	private final static Logger LOGGER = Logger.getLogger(UserRoleDao.class.getName());

	@Override
	public UserRoles get(int id) {
		String query = "SELECT * FROM table_user_roles WHERE id=?";
		UserRoles roleEntity = new UserRoles();
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {

					roleEntity.setId(result.getInt("id"));
					roleEntity.setRole(result.getString("role"));

				}
			}

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

		return roleEntity;

	}

	public int getRoleId(String role) {
		String query = "SELECT * FROM table_user_roles WHERE role=?";
		int roleId = 0;
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, role);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {

					roleId = result.getInt("id");

				}
			}

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		return roleId;

	}

	public void addRole(int userId, String role) {
		String query = "INSERT INTO table_user_role_mapper(user_id,role_id) VALUES(?,?)";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			connection.setAutoCommit(false);
			statement.setInt(1, userId);
			if (role.equals(UserRole.ADMIN.getRole())) {
				throw new AdminNotAllowedException("Admin User Cannot be created");
			}
			statement.setInt(2, getRoleId(role));
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

	public void updateRole(int userId, String role) {
		String query = "UPDATE table_user_role_mapper SET role_id=? WHERE user_id=?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			connection.setAutoCommit(false);
			if (role.equals(UserRole.ADMIN.getRole())) {
				throw new AdminNotAllowedException("Admin User Cannot be created");
			}
			statement.setInt(1, getRoleId(role));
			statement.setInt(2, userId);
			statement.executeUpdate();
			connection.commit();
		}

		catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				LOGGER.log(Level.SEVERE, e.getMessage(), e);
			}
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

	}

	@Override
	public ArrayList<UserRoles> getAll() {
		String query = "SELECT * FROM table_user_roles";
		ArrayList<UserRoles> userRolesList = new ArrayList<UserRoles>();
		try (PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				UserRoles role = new UserRoles();
				role.setId(result.getInt("id"));
				role.setRole(result.getString("role"));
				userRolesList.add(role);

			}
		} catch (Exception e) {

			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}

		return userRolesList;
	}

	@Override
	public void injectConnection(Connection connection) {
		this.connection = connection;

	}

	@Override
	public int save(Persistence t) {
		return 0;
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Persistence t, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
