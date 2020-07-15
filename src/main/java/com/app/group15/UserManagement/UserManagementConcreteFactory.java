package com.app.group15.UserManagement;

import com.app.group15.Config.AppConfig;
import com.app.group15.Persistence.Persistence;

public class UserManagementConcreteFactory implements IUserManagementAbstractFactory{

	private static IUserManagementAbstractFactory userManagementConcreteFactory;

	public static IUserManagementAbstractFactory getInstance(){
		if (null==UserManagementConcreteFactory.getUniqueInstance()){
			userManagementConcreteFactory=new UserManagementConcreteFactory();
		}
		return UserManagementConcreteFactory.userManagementConcreteFactory;
	}

	private static IUserManagementAbstractFactory getUniqueInstance() {
		return userManagementConcreteFactory;
	}

	@Override
	public Persistence getUserModel() {
		return new User();
	}

	@Override
	public Persistence getUserRoleModel() {
		return new UserRoles();
	}

	@Override
	public Persistence getUserRoleMapperModel() {
		return new UserRoleMapper();
	}

	@Override
	public UserAbstractDao getUserDao() {
		return new UserDao();
	}

	@Override
	public UserRoleAbstractDao getUserRoleDao() {
		return new UserRoleDao();
	}

	@Override
	public IUserService getUserService() {
		return new UserServiceInjector().getUserService();
	}
}
