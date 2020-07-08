package com.app.group15.SurveyManagement;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.Persistence.InvokeStoredProcedure;
import com.app.group15.QuestionManager.Question;
import com.app.group15.Utility.GroupFormationToolLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class SurveyQuestionMapperDao extends SurveyQuestionMapperAbstractDao {
    @Override
    public int getHighestOrderValue(int surveyId) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;
        int highestOrderValue = -1;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spGetHighestOrderValue(?)");
            invokeStoredProcedure.setParameter(1, surveyId);
            ResultSet results = invokeStoredProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    highestOrderValue = results.getInt(1);
                }
            }
            return highestOrderValue;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }

    @Override
    public int addQuestion(SurveyQuestionMapper surveyQuestionMapper) throws SQLException, AwsSecretsManagerException {
        int highestOrderValue = this.getHighestOrderValue(surveyQuestionMapper.getSurveyId());
        int newQuestionOrder = highestOrderValue + 1;
        surveyQuestionMapper.setQuestionOrder(newQuestionOrder);
        InvokeStoredProcedure invokeStoredProcedure = null;
        int insertedQuestionId = -1;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spAddQuestionToSurvey(?,?,?,?,?)");
            invokeStoredProcedure.setParameter(1, surveyQuestionMapper.getQuestionId());
            invokeStoredProcedure.setParameter(2, surveyQuestionMapper.getQuestionOrder());
            invokeStoredProcedure.setParameter(3, surveyQuestionMapper.getSurveyId());
            invokeStoredProcedure.setParameter(4, surveyQuestionMapper.getRuleId());
            invokeStoredProcedure.registerOutputParameterLong(6);

            invokeStoredProcedure.execute();
            insertedQuestionId = invokeStoredProcedure.getOutputParameter(5);

            return insertedQuestionId;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }

    @Override
    public List<Question> getAllSurveyQuestions(int surveyId) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;

        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spGetAllSurveyQuestionBySurveyID(?)");
            invokeStoredProcedure.setParameter(1, surveyId);
            ResultSet results = invokeStoredProcedure.executeWithResults();
            List<Question> questionList = new ArrayList<>();
            if (results != null) {
                while (results.next()) {
                    Question question = new Question();
                    question.setId(results.getInt("id"));
                    question.setQuestionTitle(results.getString("title"));
                    question.setQuestionTypeId(results.getInt("type_id"));
                    question.setQuestionInstructorId(results.getInt("instructor_id"));
                    question.setQuestionText(results.getString("question_text"));
                    question.setQuestionAddedDate(results.getString("question_date"));
                    questionList.add(question);
                }
            }
            return questionList;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }

    @Override
    public void deleteSurveyQuestion(int questionId) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;

        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spDeleteQuestionFromSurveyByQuestionID(?)");
            invokeStoredProcedure.setParameter(1, questionId);
            invokeStoredProcedure.execute();
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }


    public List<Question> getSurveyQuestionByInstructorID(int instructorID) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;

        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spGetSurveyQuestionByInstructorID(?)");
            invokeStoredProcedure.setParameter(1, instructorID);
            ResultSet results = invokeStoredProcedure.executeWithResults();
            List<Question> questionList = new ArrayList<>();
            if (results != null) {
                while (results.next()) {
                    Question question = new Question();
                    question.setId(results.getInt("id"));
                    question.setQuestionTitle(results.getString("title"));
                    question.setQuestionTypeId(results.getInt("type_id"));
                    question.setQuestionInstructorId(results.getInt("instructor_id"));
                    question.setQuestionText(results.getString("question_text"));
                    question.setQuestionAddedDate(results.getString("question_date"));
                    questionList.add(question);
                }
            }
            return questionList;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }
}
