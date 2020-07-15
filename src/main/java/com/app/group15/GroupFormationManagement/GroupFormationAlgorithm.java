package com.app.group15.GroupFormationManagement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;

public abstract class GroupFormationAlgorithm {

	private IGroupFormationAlgorithmService groupFormationService = new GroupFormationAlgorithmServiceInjector().getGroupFormationService();	
	
	public final ArrayList<ArrayList<Integer>> template(int courseId,int groupSize) throws SQLException, AwsSecretsManagerException {
		int surveyId=getSurveyIdForACourse(courseId);
		HashMap<Integer, String> questionCriteria=getQuestionCriteriaOfSurveyInOrder(surveyId);
		ArrayList<StudentResponseMaintainer> studentResponses=getStudentResponsesSortedInQuestionOrder(surveyId);
		return formGroups(studentResponses, groupSize, questionCriteria);
	}

	public abstract ArrayList<ArrayList<Integer>> formGroups(ArrayList<StudentResponseMaintainer> studentResponseList,
			int groupSize, HashMap<Integer, String> questionCriteria);

	
	public int getSurveyIdForACourse(int courseId) throws SQLException, AwsSecretsManagerException {
		return groupFormationService.getSurveyIdForACourse(courseId);
	}

	public HashMap<Integer, String> getQuestionCriteriaOfSurveyInOrder(int surveyId)
			throws SQLException, AwsSecretsManagerException {
		return groupFormationService.getQuestionCriteriaOfSurveyInOrder(surveyId);
	}

	public ArrayList<StudentResponseMaintainer> getStudentResponsesSortedInQuestionOrder(int surveyId)
			throws SQLException, AwsSecretsManagerException {
		return groupFormationService.getStudentResponsesSortedInQuestionOrder(surveyId);
	}
}
