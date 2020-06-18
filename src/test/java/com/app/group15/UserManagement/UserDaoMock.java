package com.app.group15.UserManagement;

import java.util.ArrayList;

public class UserDaoMock {

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
}
