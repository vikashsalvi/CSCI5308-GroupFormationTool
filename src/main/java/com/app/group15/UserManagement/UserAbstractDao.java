package com.app.group15.UserManagement;

import com.app.group15.Persistence.IDao;
import com.app.group15.Persistence.Persistence;

import java.util.ArrayList;

public abstract class UserAbstractDao<T> implements IDao {
    @Override
    public void delete(int id) {
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
    
    public  abstract String getUserPassword(int userId);

    public abstract int saveUser(User user, String role);

    @Override
    public abstract void update(Persistence persistence, int id);

    public abstract void updateUserRole(int userId, String role);

    public abstract User getUserByEmailId(String emailId);
}
