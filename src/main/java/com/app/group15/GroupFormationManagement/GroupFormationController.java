package com.app.group15.GroupFormationManagement;

import com.app.group15.Config.AppConfig;

public class GroupFormationController {
	private IGroupFormationAlgorithmAbstractFactory groupAbstractFactory=AppConfig.getInstance().getGroupAlgorithmAbstractFactory();
	
	private IGroupFormationService groupFormationService=groupAbstractFactory.getGroupFormationService();

}
