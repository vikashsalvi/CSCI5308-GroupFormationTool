package com.app.group15.SurveyManagement;

import com.app.group15.Config.AppConfig;
import com.app.group15.QuestionManager.IQuestionManagerAbstractFactory;

public class SurveyServiceInjector {
    private SurveyService surveyService;

    ISurveyManagementAbstractFactory surveyManagementAbstractFactory = AppConfig.getInstance().getSurveyManagementAbstractFactory();
    IQuestionManagerAbstractFactory questionManagerAbstractFactory = AppConfig.getInstance().getQuestionManagerAbstractFactory();

    public SurveyServiceInjector() {
        surveyService = (SurveyService) surveyManagementAbstractFactory.getSurveyService();
        surveyService.injectSurveyDao(surveyManagementAbstractFactory.getSurveyDao());
        surveyService.injectSurveyQuestionMapperDao(surveyManagementAbstractFactory.getSurveyQuestionMapperDao());
        surveyService.injectQuestionManagerDao(questionManagerAbstractFactory.getQuestionManagerDao());
    }

    public SurveyService getSurveyService() {
        return surveyService;
    }

}
