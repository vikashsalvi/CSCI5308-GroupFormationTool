package com.app.group15.QuestionManager;

import com.app.group15.persistence.IDao;
import com.app.group15.persistence.Persistence;

import java.util.List;

public abstract class QuestionManagerAbstractDao implements IDao {
    @Override
    public Persistence get(int id) {
        return null;
    }

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

    public abstract List getAllQuestionTypes();

    public abstract int saveQuestion(Question question);

    public abstract int saveChocie(Options choice);

    public abstract void saveQuestionChoiceMapping(int insertedQuestionId, Integer insertedChoiceId);
}
