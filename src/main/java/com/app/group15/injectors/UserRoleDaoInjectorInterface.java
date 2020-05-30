package com.app.group15.injectors;


import com.app.group15.dao.Dao;

public interface UserRoleDaoInjectorInterface {
	
	public void injectUserRoleDao(@SuppressWarnings("rawtypes") Dao userRoleDao);

}
