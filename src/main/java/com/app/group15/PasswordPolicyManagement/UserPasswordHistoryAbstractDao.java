package com.app.group15.PasswordPolicyManagement;

import com.app.group15.Persistence.IDao;
import com.app.group15.Persistence.Persistence;

import java.util.List;

public class UserPasswordHistoryAbstractDao implements IDao {

    @Override
    public Persistence get(int id) {
        // TODO Auto-generated method stub
        return null;
    }

	public List getPasswordHistory(int userId) {
		return null;
	}
	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Persistence t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(Persistence t, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public void savePasswordHistory(UserPasswordHistory passwordHistory) {
		// TODO Auto-generated method stub
		
	}

}
