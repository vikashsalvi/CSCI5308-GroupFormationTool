package com.app.group15.SurveyManagement;

public interface ISurveyServiceInjector {
    
    void injectSurveyDao(SurveyAbstractDao surveyDao);

    void injectSurveyQuestionMapperDao(SurveyQuestionMapperAbstractDao surveyQuestionDao);
}
