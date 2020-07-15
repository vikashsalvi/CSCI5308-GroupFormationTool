package com.app.group15.SurveyManagement;

import com.app.group15.Config.AppConfig;
import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.QuestionManager.Question;
import com.app.group15.QuestionManager.QuestionManagerAbstractDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyService implements ISurveyService, ISurveyServiceInjector {

    ISurveyManagementAbstractFactory surveyManagementAbstractFactory = AppConfig.getInstance().getSurveyManagementAbstractFactory();

    private SurveyAbstractDao surveyDao;
    private SurveyQuestionMapperAbstractDao surveyQuestionMapperDao;
    private QuestionManagerAbstractDao questionManagerDao;

    @Override
    public List<Question> getSurveyQuestionByInstructorID(int instructorID) throws SQLException, AwsSecretsManagerException {
        return surveyQuestionMapperDao.getSurveyQuestionByInstructorID(instructorID);
    }

    @Override
    public Survey getSurveyByCourseId(int courseId) throws SQLException, AwsSecretsManagerException {
        return surveyDao.getSurveyByCourseID(courseId);
    }

    public List<Question> getSurveyQuestionByCourseID(int courseID) throws SQLException, AwsSecretsManagerException {
        return surveyQuestionMapperDao.getSurveyQuestionByCourseID(courseID);
    }

    @Override
    public List<Question> getRemainingQuestionsForSurvey(int courseId, int instructorId) throws SQLException, AwsSecretsManagerException {
        return this.surveyQuestionMapperDao.getRemainingQuestionsForSurvey(courseId, instructorId);
    }


    @Override
    public void createSurveyIfNotExists(int courseId) throws SQLException, AwsSecretsManagerException {
        Survey survey = surveyDao.getSurveyByCourseID(courseId);
        if (survey.getId() == 0) {
            survey.setCourseId(courseId);
            survey.setPublishState(0);
            surveyDao.saveSurvey(survey);
        }
    }

    @Override
    public int addQuestionToSurvey(int courseId, int questionId, String rule, int ruleValue) throws SQLException, AwsSecretsManagerException {
        createSurveyIfNotExists(courseId);
        Question question = (Question) questionManagerDao.get(questionId);
        int ruleId = surveyDao.getRuleIdByRuleAndQuestionType(rule, question.getQuestionTypeId());
        SurveyQuestionMapper surveyQuestionMapper = new SurveyQuestionMapper();
        int surveyId = surveyDao.getSurveyByCourseID(courseId).getId();
        surveyQuestionMapper.setSurveyId(surveyId);
        surveyQuestionMapper.setQuestionId(questionId);
        surveyQuestionMapper.setRuleId(ruleId);
        surveyQuestionMapper.setRuleValue(ruleValue);
        return surveyQuestionMapperDao.addQuestion(surveyQuestionMapper);
    }

    @Override
    public void deleteSurveyQuestion(int questionId, int courseId) throws SQLException, AwsSecretsManagerException {
        Survey survey = surveyDao.getSurveyByCourseID(courseId);
        surveyQuestionMapperDao.deleteSurveyQuestion(questionId, survey.getId());
    }

    @Override
    public void publishSurvey(int courseId) throws SQLException, AwsSecretsManagerException {
        Survey survey = surveyDao.getSurveyByCourseID(courseId);
        surveyDao.publishSurvey(survey);
    }

    @Override
    public void unPublishSurvey(int courseId) throws SQLException, AwsSecretsManagerException {
        Survey survey = surveyDao.getSurveyByCourseID(courseId);
        surveyDao.unPublishSurvey(survey);
    }


    @Override
    public void injectSurveyDao(SurveyAbstractDao surveyDao) {
        this.surveyDao = surveyDao;

    }

    @Override
    public void injectSurveyQuestionMapperDao(SurveyQuestionMapperAbstractDao surveyQuestionDao) {
        this.surveyQuestionMapperDao = surveyQuestionDao;
        try {
            surveyDao.getSurveyByCourseID(1).getId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (AwsSecretsManagerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void injectQuestionManagerDao(QuestionManagerAbstractDao questionManagerDao) {
        this.questionManagerDao = questionManagerDao;
    }

    @Override
    public ArrayList<SurveyQuestionMapper> getQuestionsOfASurveySortedByOrder(int surveyId)
            throws SQLException, AwsSecretsManagerException {
        return this.surveyDao.getQuestionsOfASurveySortedByOrder(surveyId);
    }

    @Override
    public int getSurveyIdOfACourse(int courseId) throws SQLException, AwsSecretsManagerException {
        return this.surveyDao.getSurveyIdOfACourse(courseId);
    }

    @Override
    public String getRuleFromId(int ruleId) throws SQLException, AwsSecretsManagerException {
        return this.surveyDao.getRuleFromId(ruleId);
    }


}
