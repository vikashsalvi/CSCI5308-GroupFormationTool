package com.app.group15.SurveyManagement.student;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.QuestionManager.Question;
import com.app.group15.UserManagement.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyStudentService implements ISurveyStudentService, ISurveyStudentServiceInjector {

    private SurveyStudentAbstractDao surveyStudentDao;

    @Override
    public void submitResponse(User user, SurveyFormResponse surveyFormResponse) throws SQLException, AwsSecretsManagerException {

        for (SurveyResponse surveyResponse : surveyFormResponse.getSurveyResponse()) {
            if (surveyResponse.getQuestionTypeId() == 1) {
                surveyStudentDao.saveNumericResponse(surveyResponse.getQuestionId(), surveyFormResponse.getSurveyId(), surveyResponse.getNumericResponse(), user.getId());
            } else if (surveyResponse.getQuestionTypeId() == 2) {
                surveyStudentDao.saveChoiceResponse(surveyResponse.getQuestionId(), surveyFormResponse.getSurveyId(), surveyResponse.getChoiceId(), user.getId());
            } else if (surveyResponse.getQuestionTypeId() == 3) {
                String[] choices = surveyResponse.getChoiceId().split(",");
                for (String choice : choices) {
                    int chose = Integer.parseInt(choice);
                    if (chose > 0) {
                        surveyStudentDao.saveChoiceResponse(surveyResponse.getQuestionId(), surveyFormResponse.getSurveyId(), choice, user.getId());
                    }
                }
            } else {
                surveyStudentDao.saveTextResponse(surveyResponse.getQuestionId(), surveyFormResponse.getSurveyId(), surveyResponse.getTextResponse(), user.getId());
            }
        }
    }

    @Override
    public SurveyFormResponse getSurveyQuestionWithOptions(int courseId) throws SQLException, AwsSecretsManagerException {
        List<Question> questionList = surveyStudentDao.getSurveyQuestionWithCourseByCourseID(courseId);
        List<SurveyResponse> surveyResponse = new ArrayList<>();
        int surveyId = questionList.get(0).getSurveyId();
        for (Question qst : questionList) {
            SurveyResponse response = new SurveyResponse();
            response.setQuestionId(qst.getQuestionId());
            response.setQuestionTitle(qst.getQuestionTitle());
            response.setQuestionTypeId(qst.getQuestionTypeId());
            response.setQuestionInstructorId(qst.getQuestionInstructorId());
            response.setQuestionText(qst.getQuestionText());
            response.setOptions(qst.getOptions());
            response.setQuestionAddedDate(qst.getQuestionAddedDate());
            surveyResponse.add(response);
        }
        SurveyFormResponse surveyFormResponse = new SurveyFormResponse();
        surveyFormResponse.setSurveyId(surveyId);
        surveyFormResponse.setSurveyResponse(surveyResponse);
        return surveyFormResponse;
    }

    @Override
    public void injectSurveyStudentDao(SurveyStudentAbstractDao surveyStudentAbstractDao) {
        this.surveyStudentDao = surveyStudentAbstractDao;
    }
}
