package com.app.group15.SurveyManagement;

public class SurveyService implements ISurveyService, ISurveyServiceInjector {

    private SurveyAbstractDao surveyDao;
    private SurveyQuestionMapperAbstractDao surveyQuestionMapperDao;


    @Override
    public void injectSurveyDao(SurveyAbstractDao surveyDao) {
        this.surveyDao = surveyDao;

    }

    @Override
    public void injectSurveyQuestionMapperDao(SurveyQuestionMapperAbstractDao surveyQuestionDao) {
        this.surveyQuestionMapperDao = surveyQuestionDao;
    }
}
