package com.app.group15.GroupFormationManagement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.SurveyManagement.ISurveyService;
import com.app.group15.SurveyManagement.SurveyQuestionMapper;
import com.app.group15.SurveyManagement.student.ISurveyStudentService;
import com.app.group15.SurveyManagement.student.StudentResponseChoice;
import com.app.group15.SurveyManagement.student.StudentResponseNumeric;
import com.app.group15.SurveyManagement.student.StudentResponseText;

public class GroupFormationService implements IGroupFormationService, IGroupFormationServiceInjector {

	private ISurveyService surveyService;
	private ISurveyStudentService surveyStudentService;
	

	@Override
	public int getSurveyIdForACourse(int courseId) throws SQLException, AwsSecretsManagerException {
		return surveyService.getSurveyIdOfACourse(courseId);

	}
	
	public List<Integer> getQuestionOrder(int surveyId) throws SQLException, AwsSecretsManagerException{
		ArrayList<SurveyQuestionMapper> list = surveyService.getQuestionsOfASurveySortedByOrder(surveyId);
		List<Integer> questionOrder=new ArrayList();
		
		
		for (int i = 0; i < list.size(); i++) {
			questionOrder.add(list.get(i).getQuestionId());
			
		}

		return questionOrder;
	}

	public HashMap<Integer, String> getQuestionCriteriaOfSurveyInOrder(int surveyId)
			throws SQLException, AwsSecretsManagerException {
		ArrayList<SurveyQuestionMapper> list = surveyService.getQuestionsOfASurveySortedByOrder(surveyId);
		HashMap<Integer, String> questionCriteriaMap = new HashMap<>();
		
		for (int i = 0; i < list.size(); i++) {
			questionCriteriaMap.put(list.get(i).getQuestionOrder(),
					surveyService.getRuleFromId(list.get(i).getRuleId()));
			
		}

		return questionCriteriaMap;

	}

	public ArrayList<StudentResponseMaintainer> getStudentResponsesSortedInQuestionOrder(int surveyId)
			throws SQLException, AwsSecretsManagerException {
		List<StudentResponseChoice> choiceResponseList = surveyStudentService
				.getChoiceStudentResponsesForASurvey(surveyId);
		List<StudentResponseNumeric> numericResponseList = surveyStudentService
				.getNumericStudentResponsesForASurvey(surveyId);
		List<StudentResponseText> textResponseList = surveyStudentService.getTextStudentResponsesForASurvey(surveyId);

		return null;

	}

	private ArrayList<StudentResponseMaintainer> arrangeStudentResponsesByQuestionOrder(List<StudentResponseChoice> choiceResponseList,
			List<StudentResponseNumeric> numericResponseList, List<StudentResponseText> textResponseList) {
		List<List<Object>> studentResponses=new ArrayList();
		
		HashMap<Integer,List<Object>> studentResponseMap=new HashMap();
		
		for(int i=0;i<choiceResponseList.size();i++) {
			if(studentResponseMap.get(choiceResponseList.get(i).getStudentId())==null) {
				ArrayList<Object> allResponsesOfAStudent=new ArrayList<>();
				allResponsesOfAStudent.add(choiceResponseList.get(i));
				studentResponseMap.put(choiceResponseList.get(i).getStudentId(),allResponsesOfAStudent);
			}else {
				studentResponseMap.get(choiceResponseList.get(i).getStudentId()).add(choiceResponseList.get(i));
			}
		}
		
		for(int i=0;i<numericResponseList.size();i++) {
			if(studentResponseMap.get(numericResponseList.get(i).getStudentId())==null) {
				ArrayList<Object> allResponsesOfAStudent=new ArrayList<>();
				allResponsesOfAStudent.add(numericResponseList.get(i));
				studentResponseMap.put(numericResponseList.get(i).getStudentId(),allResponsesOfAStudent);
			}else {
				studentResponseMap.get(numericResponseList.get(i).getStudentId()).add(numericResponseList.get(i));
			}
		}
		
		for(int i=0;i<textResponseList.size();i++) {
			if(studentResponseMap.get(textResponseList.get(i).getStudentId())==null) {
				ArrayList<Object> allResponsesOfAStudent=new ArrayList<>();
				allResponsesOfAStudent.add(textResponseList.get(i));
				studentResponseMap.put(textResponseList.get(i).getStudentId(),allResponsesOfAStudent);
			}else {
				studentResponseMap.get(textResponseList.get(i).getStudentId()).add(textResponseList.get(i));
			}
		}
		
		
		return null;

	}
	
	private List<Object> arrangeResponsesOfAStudentByQuestionOrder(List<Object> responseList) {
		//ArrayList<SurveyQuestionMapper> list = surveyService.getQuestionsOfASurveySortedByOrder();
		return responseList;
		
	}

	@Override
	public void injectSurveyService(ISurveyService surveyService) {
		this.surveyService = surveyService;

	}

	@Override
	public void injectSurveyStudentService(ISurveyStudentService surveyStudentService) {
		this.surveyStudentService = surveyStudentService;

	}

}
