package com.app.group15.UserManagement;

import java.sql.SQLException;
import java.util.ArrayList;

import com.app.group15.ExceptionHandler.AllowedRolesNotSetException;
import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.Persistence.Persistence;

public class UserDaoMock extends UserAbstractDao{

    public User getUserByBannerIdMock(String bannerId) {
        User user = new User();
        if (bannerId.equals("B00843468")) {
            user.setFirstName("Daksh");
            user.setLastName("Patel");
            user.setEmail("daksh.patel@dal.ca");
            user.setBannerId("B00843468");
            user.setPassword("passwordTest");
            return user;
        } else {
            return user;
        }
    }

    public int saveUserMock(User user, String role) {
        return 0;
    }

    public ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setFirstName("Daksh");
        user1.setLastName("Patel");
        user1.setEmail("daksh.patel@dal.ca");
        user1.setBannerId("B00843468");
        user1.setBannerId("passwordTest");
        User user2 = new User();
        user2.setFirstName("Daksh");
        user2.setLastName("Patel");
        user2.setEmail("daksh.patel@dal.ca");
        user2.setBannerId("B00843468");
        user2.setBannerId("admin");

        userList.add(user2);
        userList.add(user1);
        return userList;

    }

	@Override
	public Persistence get(int id) throws SQLException, AwsSecretsManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByBannerId(String bannerId) throws SQLException, AwsSecretsManagerException {
		User user=new User();
		user.setBannerId(bannerId);
		return user;
	}

	@Override
	public ArrayList getAll() throws SQLException, AwsSecretsManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserPassword(int userId) throws SQLException, AwsSecretsManagerException {
		
		return "pass 5";
	}

	@Override
	public int saveUser(User user, String role)
			throws AllowedRolesNotSetException, AwsSecretsManagerException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Persistence persistence, int id) throws SQLException, AwsSecretsManagerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUserRole(int userId, String role) throws SQLException, AwsSecretsManagerException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserByEmailId(String emailId) throws SQLException, AwsSecretsManagerException {
		// TODO Auto-generated method stub
		return null;
	}
}
