package com.app.group15.SurveyManagement;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.Persistence.InvokeStoredProcedure;
import com.app.group15.Persistence.Persistence;
import com.app.group15.Utility.GroupFormationToolLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class SurveyDao extends SurveyAbstractDao implements ISurveyQuestionMapperInjector {

    private SurveyQuestionMapperAbstractDao surveyQuestionMapperDao;


    public Survey getSurvey(int id) throws SQLException, AwsSecretsManagerException {
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


    public Survey getSurveyByCourseID(int courseID) throws SQLException, AwsSecretsManagerException {
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
    public List getRuleByQuestionType(int questionType) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spGetRuleByID(?)");
            invokeStoredProcedure.setParameter(1, questionType);
            ResultSet results = invokeStoredProcedure.executeWithResults();
            List<String> ruleList = new ArrayList<>();
            if (results != null) {
                while (results.next()) {
                    ruleList.add(results.getString("rule"));
                }
            }
            return ruleList;
        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }

    @Override
    public Persistence get(int id) throws SQLException, AwsSecretsManagerException {
        return null;
    }

    @Override
    public List getAll() throws SQLException, AwsSecretsManagerException {
        return null;
    }

    @Override
    public int saveSurvey(Survey survey) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;
        int insertedSurveyId = -1;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spSaveSurvey(?,?)");
            invokeStoredProcedure.setParameter(1, survey.getCourseId());
            invokeStoredProcedure.registerOutputParameterLong(2);

            invokeStoredProcedure.execute();
            insertedSurveyId = invokeStoredProcedure.getOutputParameter(2);

        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
        return insertedSurveyId;
    }

    @Override
    public void publishSurvey(Survey survey) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spPublishSurvey(?)");
            invokeStoredProcedure.setParameter(1, survey.getId());
            invokeStoredProcedure.execute();

        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }

    @Override
    public void unPublishSurvey(Survey survey) throws SQLException, AwsSecretsManagerException {
        InvokeStoredProcedure invokeStoredProcedure = null;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spUnPublishSurvey(?)");
            invokeStoredProcedure.setParameter(1, survey.getId());
            invokeStoredProcedure.execute();

        } catch (SQLException e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            assert invokeStoredProcedure != null;
            invokeStoredProcedure.closeConnection();
        }
    }

	@Override
	public int getRuleIdByRuleAndQuestionType(String rule, int questionType) throws SQLException, AwsSecretsManagerException {
		InvokeStoredProcedure invokeStoredProcedure = null;
		try {
			invokeStoredProcedure = new InvokeStoredProcedure("spGetRuleIdByRuleAndQuestionType(?,?)");
			invokeStoredProcedure.setParameter(1, rule);
			invokeStoredProcedure.setParameter(2, questionType);
			ResultSet results = invokeStoredProcedure.executeWithResults();
			int ruleId = -1;
			if (results != null) {
				while (results.next()) {
					ruleId=results.getInt("id");
				}
			}
			return ruleId;
		} catch (SQLException e) {
			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
			throw e;
		} finally {
			assert invokeStoredProcedure != null;
			invokeStoredProcedure.closeConnection();
		}
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
