package com.app.group15.dao;

import com.app.group15.model.Persistence;
import com.app.group15.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class UserAbstractDao<T> implements Dao {
    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
    }


    @Override
    public int save(Persistence user) {
        return 0;

    }

    @Override
    public abstract Persistence get(int id);

    public abstract User getUserByBannerId(String bannerId);

    @Override
    public abstract ArrayList<User> getAll();

    public abstract int saveUser(User user, String role);

    @Override
    public abstract void update(Persistence persistence, int id);

    public abstract void updateUserRole(int userId, String role);

    public abstract boolean updateUserPassword(int userId, String password);

    public abstract User getUserByEmailId(String emailId);

    public abstract boolean insertForgotPasswordToken(int id, String token);

    public abstract boolean checkIfTokenAlreadyExists(int id);

    public abstract boolean deleteForgotPasswordToken(int id);

    public abstract Map<String, String> getUserFromToken(String token);
}
