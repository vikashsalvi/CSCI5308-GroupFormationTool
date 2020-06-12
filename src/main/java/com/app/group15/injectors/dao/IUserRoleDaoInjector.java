package com.app.group15.injectors.dao;


import com.app.group15.dao.IDao;

public interface IUserRoleDaoInjector {

	public void injectUserRoleDao(@SuppressWarnings("rawtypes") IDao userRoleDao);

}
