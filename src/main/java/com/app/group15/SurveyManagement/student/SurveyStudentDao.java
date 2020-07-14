package com.app.group15.SurveyManagement.student;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.Persistence.InvokeStoredProcedure;
import com.app.group15.QuestionManager.Options;
import com.app.group15.QuestionManager.Question;
import com.app.group15.Utility.GroupFormationToolLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class SurveyStudentDao extends SurveyStudentAbstractDao implements ISurveyStudentDaoInjector {

    @Override
    public void saveNumericResponse(int questionId, int surveyId, int numericResponse, int userId) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;

        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spAddSurveyNumericResponse(?,?,?,?)");
            invokeStoredProcedure.setParameter(1, questionId);
            invokeStoredProcedure.setParameter(2, surveyId);
            invokeStoredProcedure.setParameter(3, numericResponse);
            invokeStoredProcedure.setParameter(4, userId);


            invokeStoredProcedure.execute();

        } catch (SQLException | AwsSecretsManagerException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }

    @Override
    public void saveTextResponse(int questionId, int surveyId, String textResponse, int userId) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;

        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spAddSurveyTextResponse(?,?,?,?)");
            invokeStoredProcedure.setParameter(1, questionId);
            invokeStoredProcedure.setParameter(2, surveyId);
            invokeStoredProcedure.setParameter(3, textResponse);
            invokeStoredProcedure.setParameter(4, userId);


            invokeStoredProcedure.execute();
            //insertedSurveyId = invokeStoredProcedure.getOutputParameter(2);

        } catch (SQLException | AwsSecretsManagerException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }

    @Override
    public void saveChoiceResponse(int questionId, int surveyId, String choiceId, int userId) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;

        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spAddSurveyChoiceResponse(?,?,?,?)");
            invokeStoredProcedure.setParameter(1, questionId);
            invokeStoredProcedure.setParameter(2, surveyId);
            invokeStoredProcedure.setParameter(3, choiceId);
            invokeStoredProcedure.setParameter(4, userId);


            invokeStoredProcedure.execute();

        } catch (SQLException | AwsSecretsManagerException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }

    @Override
    public List<Question> getSurveyQuestionWithCourseByCourseID(int courseID) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;

        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spGetSurveyQuestionByCourseID(?)");
            invokeStoredProcedure.setParameter(1, courseID);
            ResultSet results = invokeStoredProcedure.executeWithResults();
            List<Question> questionList = new ArrayList<>();
            int surveyId;
            if (results != null) {
                while (results.next()) {
                    Question question = new Question();
                    results.getInt("id");
                    question.setQuestionId(results.getInt("id"));

                    question.setQuestionTitle(results.getString("title"));
                    question.setQuestionTypeId(results.getInt("type_id"));
                    question.setQuestionInstructorId(results.getInt("instructor_id"));
                    question.setQuestionText(results.getString("question_text"));
                    question.setOptions(getQuestionOptionsByQuestionId(results.getInt("id")));
                    question.setQuestionAddedDate(results.getString("question_date"));
                    question.setSurveyId(results.getInt("survey_id"));
                    questionList.add(question);

                }
            }
            return questionList;
        } catch (SQLException | AwsSecretsManagerException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }

    @Override
    public List<Options> getQuestionOptionsByQuestionId(int questionId) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;

        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spFindQuestionChoices(?)");
            invokeStoredProcedure.setParameter(1, questionId);
            ResultSet results = invokeStoredProcedure.executeWithResults();
            List<Options> optionsList = new ArrayList<>();
            if (results != null) {
                while (results.next()) {

                    Options options = new Options();
                    options.setId(results.getInt("choice_id"));
                    options.setOption(results.getString("choice"));
                    options.setValue(results.getString("stored_as"));
                    optionsList.add(options);
                }
            }
            return optionsList;
        } catch (SQLException | AwsSecretsManagerException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }


}
