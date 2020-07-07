package com.app.group15.SurveyManagement;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.Persistence.InvokeStoredProcedure;
import com.app.group15.Persistence.Persistence;
import com.app.group15.Utility.GroupFormationToolLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class SurveyDao extends SurveyAbstractDao implements ISurveyQuestionMapperInjector {

    private SurveyQuestionMapperAbstractDao surveyQuestionMapperDao;

    @Override
    public Persistence get(int id) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spGetSurvey(?)");
            invokeStoredProcedure.setParameter(1, id);
            ResultSet results = invokeStoredProcedure.executeWithResults();
            Survey survey = new Survey();
            if (results != null) {
                while (results.next()) {
                    survey.setId(results.getInt("id"));
                    survey.setPublishState(results.getInt("publish_state"));
                    survey.setCourseId(results.getInt("course_id"));
                }
            }
            return survey;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }

    public Persistence getSurveyByCourseID(int courseID) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spGetSurveyByCourseID(?)");
            invokeStoredProcedure.setParameter(1, courseID);
            ResultSet results = invokeStoredProcedure.executeWithResults();
            Survey survey = new Survey();
            if (results != null) {
                while (results.next()) {
                    survey.setId(results.getInt("id"));
                    survey.setPublishState(results.getInt("publish_state"));
                    survey.setCourseId(results.getInt("course_id"));
                }
            }
            return survey;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }

    @Override
    public List getAll() throws SQLException, AwsSecretsManagerException {
        return null;
    }

    @Override
    public int save(Persistence persistence) throws SQLException, AwsSecretsManagerException {
        return 0;
    }

    @Override
    public void update(Persistence persistence, int id) throws SQLException, AwsSecretsManagerException {

    }

    @Override
    public void delete(int id) throws SQLException, AwsSecretsManagerException {

    }

    @Override
    public void injectSurveyQuestionMapperDao(SurveyQuestionMapperDao surveyQuestionMapperDao) {
        this.surveyQuestionMapperDao = surveyQuestionMapperDao;
    }
}
