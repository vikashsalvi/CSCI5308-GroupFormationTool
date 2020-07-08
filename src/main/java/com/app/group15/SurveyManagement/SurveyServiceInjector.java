package com.app.group15.SurveyManagement;

public class SurveyServiceInjector {
    private SurveyService surveyService;

    public SurveyServiceInjector() {
        surveyService = new SurveyService();
        surveyService.injectSurveyDao(new SurveyDao());
        surveyService.injectSurveyQuestionMapperDao(new SurveyQuestionMapperDao());
    }

    public SurveyService getSurveyService() {
        return surveyService;
    }

}
