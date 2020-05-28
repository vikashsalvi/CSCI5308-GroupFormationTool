package com.app.group15.persistence.injectors;

import com.app.group15.persistence.dao.Dao;

public interface UserRoleDaoInjectorInterface {
	
	public void injectUserRoleDao(@SuppressWarnings("rawtypes") Dao userRoleDao);

}
