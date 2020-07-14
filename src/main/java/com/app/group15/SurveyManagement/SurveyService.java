package com.app.group15.SurveyManagement;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.QuestionManager.Question;
import com.app.group15.QuestionManager.QuestionManagerAbstractDao;
import com.app.group15.UserManagement.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyService implements ISurveyService, ISurveyServiceInjector {

	private SurveyAbstractDao surveyDao;
	private SurveyQuestionMapperAbstractDao surveyQuestionMapperDao;
	private QuestionManagerAbstractDao questionManagerDao;

	@Override
	public List<Question> getSurveyQuestionByInstructorID(int instructorID) throws SQLException, AwsSecretsManagerException {
		return surveyQuestionMapperDao.getSurveyQuestionByInstructorID(instructorID);
	}

	@Override
	public Survey getSurveyByCourseId(int courseId) throws SQLException, AwsSecretsManagerException {
		return surveyDao.getSurveyByCourseID(courseId);
	}

	public List<Question> getSurveyQuestionByCourseID(int courseID) throws SQLException, AwsSecretsManagerException {
		return surveyQuestionMapperDao.getSurveyQuestionByCourseID(courseID);
	}

	@Override
	public List<Question> getRemainingQuestionsForSurvey(int courseId, int instructorId) throws SQLException, AwsSecretsManagerException {
		return this.surveyQuestionMapperDao.getRemainingQuestionsForSurvey(courseId, instructorId);
	}

	@Override
	public SurveyFormResponse getSurveyQuestionWithOptions(int courseId) throws SQLException, AwsSecretsManagerException {
		List<Question> questionList = this.surveyQuestionMapperDao.getSurveyQuestionWithCourseByCourseID(courseId);
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
	public void createSurveyIfNotExists(int courseId) throws SQLException, AwsSecretsManagerException {
		Survey survey = surveyDao.getSurveyByCourseID(courseId);
		if (survey.getId() == 0) {
			survey.setCourseId(courseId);
			survey.setPublishState(0);
			surveyDao.saveSurvey(survey);
		}
	}

	@Override
	public int addQuestionToSurvey(int courseId, int questionId, String rule, int ruleValue) throws SQLException, AwsSecretsManagerException {
		createSurveyIfNotExists(courseId);
		Question question = (Question) questionManagerDao.get(questionId);
		int ruleId=surveyDao.getRuleIdByRuleAndQuestionType(rule, question.getQuestionTypeId());
		SurveyQuestionMapper surveyQuestionMapper = new SurveyQuestionMapper();
		int surveyId = surveyDao.getSurveyByCourseID(courseId).getId();
		surveyQuestionMapper.setSurveyId(surveyId);
		surveyQuestionMapper.setQuestionId(questionId);
		surveyQuestionMapper.setRuleId(ruleId);
		surveyQuestionMapper.setRuleValue(ruleValue);
		return surveyQuestionMapperDao.addQuestion(surveyQuestionMapper);
	}

	@Override
	public void deleteSurveyQuestion(int questionId, int courseId) throws SQLException, AwsSecretsManagerException {
		Survey survey=surveyDao.getSurveyByCourseID(courseId);
		surveyQuestionMapperDao.deleteSurveyQuestion(questionId,survey.getId());
	}

	@Override
	public void publishSurvey(int courseId) throws SQLException, AwsSecretsManagerException {
		Survey survey = surveyDao.getSurveyByCourseID(courseId);
		surveyDao.publishSurvey(survey);
	}

	@Override
	public void unPublishSurvey(int courseId) throws SQLException, AwsSecretsManagerException {
		Survey survey = surveyDao.getSurveyByCourseID(courseId);
		surveyDao.unPublishSurvey(survey);
	}

	@Override
	public void submitResponse(User user, SurveyFormResponse surveyFormResponse) throws SQLException, AwsSecretsManagerException {

		for (SurveyResponse surveyResponse : surveyFormResponse.getSurveyResponse()) {
			if (surveyResponse.getQuestionTypeId() == 1) {
				surveyDao.saveNumericResponse(surveyResponse.getQuestionId(), surveyFormResponse.getSurveyId(), surveyResponse.getNumericResponse(), user.getId());
			} else if (surveyResponse.getQuestionTypeId() == 2) {
				surveyDao.saveChoiceResponse(surveyResponse.getQuestionId(), surveyFormResponse.getSurveyId(), surveyResponse.getChoiceId(), user.getId());
			} else if (surveyResponse.getQuestionTypeId() == 3) {
				String[] choices = surveyResponse.getChoiceId().split(",");
				for (String choice : choices) {
					int chose = Integer.parseInt(choice);
					if (chose > 0) {
						surveyDao.saveChoiceResponse(surveyResponse.getQuestionId(), surveyFormResponse.getSurveyId(), choice, user.getId());
					}
				}
			} else {
				surveyDao.saveTextResponse(surveyResponse.getQuestionId(), surveyFormResponse.getSurveyId(), surveyResponse.getTextResponse(), user.getId());
			}
		}
	}

	@Override
	public void injectSurveyDao(SurveyAbstractDao surveyDao) {
		this.surveyDao = surveyDao;

	}

	@Override
	public void injectSurveyQuestionMapperDao(SurveyQuestionMapperAbstractDao surveyQuestionDao) {
		this.surveyQuestionMapperDao = surveyQuestionDao;
		try {
			surveyDao.getSurveyByCourseID(1).getId();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} catch (AwsSecretsManagerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void injectQuestionManagerDao(QuestionManagerAbstractDao questionManagerDao) {
		this.questionManagerDao = questionManagerDao;
	}


}
