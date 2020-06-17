package com.app.group15.passwordPolicyManagement;

import com.app.group15.persistence.IDao;
import com.app.group15.persistence.Persistence;

import java.util.List;

public abstract class PasswordPolicyAbstractDao<T> implements IDao {

    @Override
    public Persistence get(int id) {
        return null;
    }

    @Override
    public abstract List<PasswordPolicy> getAll();

    @Override
    public int save(Persistence persistence) {
        return 0;
    }

    @Override
    public void update(Persistence persistence, int id) {

    }

    @Override
    public void delete(int id) {

    }


    public abstract List<PasswordPolicy> getActivePasswordPolicy();

}
