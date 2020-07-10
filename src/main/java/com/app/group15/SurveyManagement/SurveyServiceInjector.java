package com.app.group15.SurveyManagement;

import com.app.group15.QuestionManager.QuestionManagerDao;

public class SurveyServiceInjector {
    private SurveyService surveyService;

    public SurveyServiceInjector() {
        surveyService = new SurveyService();
        surveyService.injectSurveyDao(new SurveyDao());
        surveyService.injectSurveyQuestionMapperDao(new SurveyQuestionMapperDao());
        surveyService.injectQuestionManagerDao(new QuestionManagerDao());
    }

    public SurveyService getSurveyService() {
        return surveyService;
    }

}
