package com.app.group15.UserManagement;

import com.app.group15.Persistence.IDao;
import com.app.group15.Persistence.Persistence;

import java.util.List;
import java.util.Set;

public abstract class UserRoleAbstractDao<T> implements IDao {


    @Override
    public abstract Persistence get(int id);

    public abstract int getRoleId(String role);

    public abstract Set<String> getRolesByBannerId(String bannerId);

    public abstract void addRole(int userId, String role);

    public abstract void updateRole(int userId, String role);

    @Override
    public abstract List<T> getAll();

    @Override
    public int save(Persistence t) {
        return 0;

    }

    @Override
    public void update(Persistence t, int id) {

    }

    @Override
    public void delete(int id) {

    }
}
