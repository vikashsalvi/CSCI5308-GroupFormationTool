package com.app.group15.SurveyManagement;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.QuestionManager.Question;

import java.sql.SQLException;
import java.util.List;

public class SurveyService implements ISurveyService, ISurveyServiceInjector {

    private SurveyAbstractDao surveyDao;
    private SurveyQuestionMapperAbstractDao surveyQuestionMapperDao;

    @Override
    public List<Question> getSurveyQuestionByInstructorID(int instructorID) throws SQLException, AwsSecretsManagerException {
        return surveyQuestionMapperDao.getSurveyQuestionByInstructorID(instructorID);
    }

    public List<Question> getSurveyQuestionByCourseID(int courseID) throws SQLException, AwsSecretsManagerException {
        return surveyQuestionMapperDao.getSurveyQuestionByCourseID(courseID);
    }

    @Override
    public void injectSurveyDao(SurveyAbstractDao surveyDao) {
        this.surveyDao = surveyDao;

    }

    @Override
    public void injectSurveyQuestionMapperDao(SurveyQuestionMapperAbstractDao surveyQuestionDao) {
        this.surveyQuestionMapperDao = surveyQuestionDao;
    }
}
