package com.app.group15.SurveyManagement.student;

public class SurveyStudentInjectorService {
    private SurveyStudentService surveyStudentService;

    public SurveyStudentInjectorService() {
        surveyStudentService = new SurveyStudentService();
        surveyStudentService.injectSurveyStudentDao(new SurveyStudentDao());
    }

    public SurveyStudentService getSurveyStudentService() {
        return surveyStudentService;
    }
}
