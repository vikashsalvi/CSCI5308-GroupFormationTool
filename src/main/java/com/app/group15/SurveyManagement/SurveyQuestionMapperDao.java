package com.app.group15.SurveyManagement;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.Persistence.InvokeStoredProcedure;
import com.app.group15.Utility.GroupFormationToolLogger;

import java.sql.ResultSet;
import java.sql.SQLException;
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
	public void getAllSurveyQuestions(int surveyId) throws SQLException, AwsSecretsManagerException {

	}

	@Override
	public void deleteSurveyQuestion(int id) throws SQLException, AwsSecretsManagerException {

	}
}
