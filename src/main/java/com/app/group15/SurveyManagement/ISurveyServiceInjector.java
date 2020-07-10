package com.app.group15.SurveyManagement;

import com.app.group15.QuestionManager.QuestionManagerAbstractDao;

public interface ISurveyServiceInjector {

    void injectSurveyDao(SurveyAbstractDao surveyDao);

    void injectSurveyQuestionMapperDao(SurveyQuestionMapperAbstractDao surveyQuestionDao);

    void injectQuestionManagerDao(QuestionManagerAbstractDao questionManagerDao);
}
