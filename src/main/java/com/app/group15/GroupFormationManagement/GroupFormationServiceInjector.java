package com.app.group15.GroupFormationManagement;

import com.app.group15.SurveyManagement.SurveyServiceInjector;
import com.app.group15.SurveyManagement.student.SurveyStudentInjectorService;

public class GroupFormationServiceInjector {
	
	private GroupFormationService groupFormationService;
	
	public GroupFormationServiceInjector() {
		groupFormationService=new GroupFormationService();
		groupFormationService.injectSurveyService(new SurveyServiceInjector().getSurveyService());
		groupFormationService.injectSurveyStudentService(new SurveyStudentInjectorService().getSurveyStudentService());
	}

	public GroupFormationService getGroupFormationService() {
		return groupFormationService;
	}

	
}
