package com.app.group15.GroupFormationManagement;

import com.app.group15.SurveyManagement.ISurveyService;
import com.app.group15.SurveyManagement.student.ISurveyStudentService;

public interface IGroupFormationServiceInjector {
	public void injectSurveyService(ISurveyService surveyService);
	public void injectSurveyStudentService(ISurveyStudentService surveyStudentService);

}
