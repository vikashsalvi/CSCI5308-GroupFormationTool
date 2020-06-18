package com.app.group15.QuestionManager;

public class QuestionChoiceMapperService implements IQuestionChoiceMapperInjectorService, IQuestionChoiceMapperService {

	private QuestionChoiceMapperAbstractDao questionChoiceMapperDao;

	@Override
	public void injectQuestionChoiceMapperDao(QuestionChoiceMapperDao questionChoiceMapperDao) {
		this.questionChoiceMapperDao = questionChoiceMapperDao;
	}

	@Override
	public void deleteByQuestionId(int questionId) {
		questionChoiceMapperDao.deleteByQuestionId(questionId);
	}
}
