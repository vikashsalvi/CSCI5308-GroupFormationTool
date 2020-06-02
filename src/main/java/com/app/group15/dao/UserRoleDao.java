package com.app.group15.dao;

import com.app.group15.ExceptionHandler.AdminNotAllowedException;
import com.app.group15.model.UserRoles;
import com.app.group15.persistence.DatabaseManager;
import com.app.group15.persistence.UserRole;
import com.app.group15.utility.GroupFormationToolLogger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;


@SuppressWarnings("rawtypes")
public class UserRoleDao extends UserRoleAbstractDao {

    public UserRoleDao() {

    }

    @Override
    public UserRoles get(int id) {
        String query = "SELECT * FROM table_user_roles WHERE id=?";
        UserRoles roleEntity = new UserRoles();
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {

					roleEntity.setId(result.getInt("id"));
					roleEntity.setRole(result.getString("role"));

                }
            }

        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }

        return roleEntity;

    }

    @Override
    public int getRoleId(String role) {
        String query = "SELECT * FROM table_user_roles WHERE role=?";
        int roleId = 0;
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, role);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {

                    roleId = result.getInt("id");

                }
            }

        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }
        return roleId;

    }

    @Override
    public Set<String> getRolesByBannerId(String bannerId) {
        String query = "SELECT role FROM table_users tu\n" + "JOIN table_user_role_mapper trm ON tu.id=trm.user_id\n"
                + "JOIN table_user_roles tr ON trm.role_id=tr.id\n" + "WHERE tu.banner_id=?";
        Set<String> roles = new HashSet<String>();
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, bannerId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    String role = result.getString("role");
					roles.add(role);
                }
            }
        } catch (Exception e) {

            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }

        return roles;

    }

    @Override
    public void addRole(int userId, String role) {
        String query = "INSERT INTO table_user_role_mapper(user_id,role_id) VALUES(?,?)";
        try (Connection connection = DatabaseManager.getDataSource().getConnection()) {
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
                    GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
                }
                GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            }

        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    @Override
    public void updateRole(int userId, String role) {
        String query = "UPDATE table_user_role_mapper SET role_id=? WHERE user_id=?";
        try (Connection connection = DatabaseManager.getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                connection.setAutoCommit(false);
                if (role.equals(UserRole.ADMIN.getRole())) {
                    throw new AdminNotAllowedException("Admin User Cannot be created");
                }
                statement.setInt(1, getRoleId(role));
                statement.setInt(2, userId);
				statement.executeUpdate();
				connection.commit();
			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
				}
				GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}

	}

	@Override
	public ArrayList<UserRoles> getAll() {
		String query = "SELECT * FROM table_user_roles";
		ArrayList<UserRoles> userRolesList = new ArrayList<UserRoles>();
		try (Connection connection = DatabaseManager.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet result = statement.executeQuery()) {

			while (result.next()) {
				UserRoles role = new UserRoles();
				role.setId(result.getInt("id"));
				role.setRole(result.getString("role"));
				userRolesList.add(role);

			}
		} catch (Exception e) {

			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		}

		return userRolesList;
	}

}
