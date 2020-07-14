package com.app.group15.SurveyManagement;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SurveyDaoMock extends SurveyAbstractDao {

	@Override
	public Survey getSurvey(int id) throws SQLException, AwsSecretsManagerException {
		Survey survey=new Survey();
		survey.setId(1);
		survey.setPublishState(1);
		survey.setCourseId(15);
		return survey;
	}

	@Override
	public Survey getSurveyByCourseID(int courseID) throws SQLException, AwsSecretsManagerException {
		Survey survey=new Survey();
		survey.setId(1);
		survey.setPublishState(1);
		survey.setCourseId(courseID);
		return survey;
	}

	@Override
	public List getRuleByQuestionType(int questionType) throws SQLException, AwsSecretsManagerException {
		List<String> ruleList;
		if (questionType == 1) {
			ruleList = new ArrayList<String>(Arrays.asList("SIMILAR", "DISSIMILAR", "GREATER", "LESS"));
		} else {
			ruleList = new ArrayList<String>(Arrays.asList("SIMILAR", "DISSIMILAR"));
		}
		return ruleList;
	}

	@Override
	public int saveSurvey(Survey survey) throws SQLException, AwsSecretsManagerException {
		return 1;
	}

	@Override
	public void publishSurvey(Survey survey) throws SQLException, AwsSecretsManagerException {

	}

	@Override
	public void unPublishSurvey(Survey survey) throws SQLException, AwsSecretsManagerException {

	}

	@Override
	public int getRuleIdByRuleAndQuestionType(String rule, int questionType) throws SQLException, AwsSecretsManagerException {
		return 1;
	}
}
