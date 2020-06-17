package com.app.group15.userManagement;

import com.app.group15.persistence.IDao;
import com.app.group15.persistence.Persistence;

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
