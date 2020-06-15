package com.app.group15.passwordPolicyManagement;

import java.util.List;

import com.app.group15.persistence.IDao;
import com.app.group15.persistence.Persistence;

public class UserPasswordHistoryAbstractDao implements IDao{

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

}
