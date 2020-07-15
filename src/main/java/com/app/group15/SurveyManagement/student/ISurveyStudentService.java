package com.app.group15.SurveyManagement.student;

import com.app.group15.CourseManagement.Course;
import com.app.group15.ExceptionHandler.AwsSecretsManagerException;
import com.app.group15.UserManagement.User;

import java.sql.SQLException;
import java.util.List;

public interface ISurveyStudentService {
    void submitResponse(User user, SurveyFormResponse surveyFormResponse) throws SQLException, AwsSecretsManagerException;

    SurveyFormResponse getSurveyQuestionWithOptions(int courseId) throws SQLException, AwsSecretsManagerException;


    List<Boolean> validateIfUserHasSubmittedSurveyBefore(List<Course> courseList, int userId) throws SQLException, AwsSecretsManagerException;
    
    public List<StudentResponseNumeric> getNumericStudentResponsesForASurvey(int surveyId) throws SQLException, AwsSecretsManagerException ;
    public List<StudentResponseText> getTextStudentResponsesForASurvey(int surveyId) throws SQLException, AwsSecretsManagerException ;
    public List<StudentResponseChoice> getChoiceStudentResponsesForASurvey(int surveyId) throws SQLException, AwsSecretsManagerException ;

}
