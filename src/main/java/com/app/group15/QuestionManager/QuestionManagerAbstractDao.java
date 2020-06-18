package com.app.group15.QuestionManager;

import com.app.group15.Persistence.IDao;
import com.app.group15.Persistence.Persistence;

import java.util.List;

public abstract class QuestionManagerAbstractDao implements IDao {
    @Override
	public abstract Persistence get(int id);

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public int save(Persistence persistence) {
        return 0;
    }

	@Override
	public void update(Persistence persistence, int id) {

	}

	@Override
	public void delete(int id) {

	}

	public abstract List<Question> getAllQuestionsOfInstructor(int instructorId);

	public abstract List getAllQuestionTypes();

	public abstract int saveQuestion(Question question);

	public abstract int saveOption(Options option);

	public abstract List<Options> getOptions(int questionId);

	public abstract void saveQuestionOptionMapping(int insertedQuestionId, Integer insertedChoiceId);

	public abstract void deleteByQuestionId(int questionId);
}
