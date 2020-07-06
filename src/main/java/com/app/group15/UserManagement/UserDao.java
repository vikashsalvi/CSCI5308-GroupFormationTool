package com.app.group15.UserManagement;

import com.app.group15.Persistence.DatabaseManager;
import com.app.group15.Persistence.IDao;
import com.app.group15.Persistence.Persistence;
import com.app.group15.Utility.GroupFormationToolLogger;
import com.app.group15.Utility.ServiceUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;

import static com.app.group15.Utility.DatabaseQueriesUtility.*;

@SuppressWarnings("rawtypes")
public class UserDao extends UserAbstractDao implements IUserRoleDaoInjector {

    private UserRoleAbstractDao userRoleDao;

    public UserDao() {

    }

    @Override
    public User get(int id) {
        String query = GET_A_USER;
        User user = new User();
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }
        return user;
    }

    @Override
    public User getUserByBannerId(String bannerId) {
        String query = GET_USER_BY_BANNER_ID;
        User user = new User();
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, bannerId);
            try (ResultSet result = statement.executeQuery()) {
                System.out.println(result);
                while (result.next()) {
                    user.setBannerId(result.getString("banner_id"));
                    user.setEmail(result.getString("email"));
                    user.setFirstName(result.getString("first_name"));
                    user.setLastName(result.getString("last_name"));
                    user.setId(result.getInt("id"));
                    user.setPassword(result.getString("password"));
                }
            }
        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }
        return user;
    }

    @Override
    public ArrayList<User> getAll() {
        String query = GET_ALL_USERS;
        ArrayList<User> usersList = new ArrayList<>();
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                User user = new User();
                user.setBannerId(result.getString("banner_id"));
                user.setEmail(result.getString("email"));
                user.setFirstName(result.getString("first_name"));
                user.setLastName(result.getString("last_name"));
                user.setId(result.getInt("id"));
                usersList.add(user);
            }

        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }
        return usersList;
    }

    @Override
    public int saveUser(User user, String role) {
        String query = SAVE_USER;
        int userId = 0;
        try (Connection connection = DatabaseManager.getDataSource().getConnection()) {
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
                    GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
                }
                GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            }

        } catch (SQLException e) {

            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }
        return userId;

    }

    @Override
    public void update(Persistence user, int id) {
        User userEntity = (User) user;
        String query = UPDATE_USER;
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            statement.setString(1, userEntity.getFirstName());
            statement.setString(2, userEntity.getLastName());
            statement.setString(3, userEntity.getEmail());
            statement.setInt(4, id);
            statement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    @Override
    public void updateUserRole(int userId, String role) {
        String query = UPDATE_USER_ROLE;
        try (Connection connection = DatabaseManager.getDataSource().getConnection()) {
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
                    GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
                }
                GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            }
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    @Override
    public String getUserPassword(int userId) {
        String query = GET_PASSWORD_OF_USER;
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                return result.getString("password");
            }

        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }
        return null;

    }


    @Override
    public void injectUserRoleDao(IDao userRoleDao) {
        if (ServiceUtility.isNotNull(userRoleDao)) {
            this.userRoleDao = (UserRoleDao) userRoleDao;
        } else {
            GroupFormationToolLogger.log(Level.SEVERE, " userRoleDao Not Injected");
        }
    }

    @Override
    public User getUserByEmailId(String emailId) {
        String query = GET_USER_BY_EMAIL;
        User user = new User();
        try (Connection connection = DatabaseManager.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, emailId);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    user.setBannerId(result.getString("banner_id"));
                    user.setEmail(result.getString("email"));
                    user.setFirstName(result.getString("first_name"));
                    user.setLastName(result.getString("last_name"));
                    user.setId(result.getInt("id"));
                }
            }
        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        }
        return user;
    }


}
