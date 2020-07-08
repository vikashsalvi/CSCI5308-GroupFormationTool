package com.app.group15.SurveyManagement;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.QuestionManager.Question;

import java.sql.SQLException;
import java.util.List;

public interface ISurveyService {

    List<Question> getSurveyQuestionByInstructorID(int instructorID) throws SQLException, AwsSecretsManagerException;
}
