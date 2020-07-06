package com.app.group15.QuestionManager;

import java.sql.SQLException;

import com.app.group15.ExceptionHandler.AwsSecretsManagerException;

public class QuestionChoiceMapperService implements IQuestionChoiceMapperInjectorService, IQuestionChoiceMapperService {

    private QuestionChoiceMapperAbstractDao questionChoiceMapperDao;

    @Override
    public void injectQuestionChoiceMapperDao(QuestionChoiceMapperDao questionChoiceMapperDao) {
        this.questionChoiceMapperDao = questionChoiceMapperDao;
    }

    @Override
    public void deleteByQuestionId(int questionId) throws SQLException, AwsSecretsManagerException {
        questionChoiceMapperDao.deleteByQuestionId(questionId);
    }
}
