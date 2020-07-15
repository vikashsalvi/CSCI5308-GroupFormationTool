package com.app.group15.GroupFormationManagement;

import java.sql.SQLException;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;

public interface IGroupFormationService {
	
	public int getSurveyIdForACourse(int courseId) throws SQLException, AwsSecretsManagerException;

}
