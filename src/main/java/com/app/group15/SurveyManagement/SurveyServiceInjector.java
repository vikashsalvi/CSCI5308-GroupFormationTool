package com.app.group15.SurveyManagement;

public class SurveyServiceInjector {
    private SurveyService surveyService;

    public SurveyServiceInjector() {
        surveyService = new SurveyService();
    }

    public SurveyService getSurveyService() {
        return surveyService;
    }

}
