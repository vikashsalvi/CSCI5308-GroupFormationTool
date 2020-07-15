package com.app.group15.GroupFormationManagement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.UserManagement.IUserService;
import com.app.group15.UserManagement.User;

public class GroupFormationService implements IGroupFormationService,IGroupFormationServiceInjector{
	
	private GroupFormationAlgorithm groupFormationAlgorithm;
	private IUserService userService;

	@Override
	public ArrayList<ArrayList<User>> formGroups(int courseId, int groupSize) throws SQLException, AwsSecretsManagerException {
		ArrayList<ArrayList<Integer>> groupList=groupFormationAlgorithm.template(courseId, groupSize);
		ArrayList<ArrayList<User>> groupedUserList=new ArrayList();
		for(int i=0;i<groupList.size();i++) {
			ArrayList<User> userList=new ArrayList();
			for(int j=0;j<groupList.get(i).size();j++) {
				userList.add(userService.getUser(groupList.get(i).get(j)));
			}
			groupedUserList.add(userList);
		}
		return groupedUserList;
	}

	@Override
	public void injectGroupFormationAlgorithm(GroupFormationAlgorithm groupFormationAlgorithm) {
		this.groupFormationAlgorithm=groupFormationAlgorithm;
		
	}

	@Override
	public void injectUserService(IUserService userService) {
		this.userService=userService;
		
	}

}
