package com.app.group15.QuestionManager;

import com.app.group15.persistence.InvokeStoredProcedure;
import com.app.group15.utility.GroupFormationToolLogger;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class QuestionManagerDao extends QuestionManagerAbstractDao {

    QuestionManagerDao questionManagerDao;

    @Override
    public List<QuestionType> getAllQuestionTypes() {

        InvokeStoredProcedure invokeStoredProcedure = null;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spFindAllQuestionType()");
            ResultSet results = invokeStoredProcedure.executeWithResults();
            List<QuestionType> questionTypeList = new ArrayList<>();
            if (results != null) {
                while (results.next()) {
                    QuestionType type = new QuestionType();
                    type.setId(results.getInt("id"));
                    type.setQuestionType(results.getString("type"));
                    questionTypeList.add(type);
                }
            }
            return questionTypeList;
        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            invokeStoredProcedure.closeConnection();
        }
        return null;
    }

    @Override
    public int saveQuestion(Question question) {

        InvokeStoredProcedure invokeStoredProcedure = null;
        int insertedQuestionId = -1;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spAddQuestion(?,?,?,?,?,?)");
            invokeStoredProcedure.setParameter(1, question.getQuestionTitle());
            invokeStoredProcedure.setParameter(2, question.getQuestionTypeId());
            invokeStoredProcedure.setParameter(3, question.getQuestionInstructorId());
            invokeStoredProcedure.setParameter(4, question.getQuestionText());
            invokeStoredProcedure.setParameter(5, question.getQuestionAddedDate());
            invokeStoredProcedure.registerOutputParameterLong(6);

            invokeStoredProcedure.execute();
            insertedQuestionId = invokeStoredProcedure.getOutputParameter(6);

        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            invokeStoredProcedure.closeConnection();
        }
        return insertedQuestionId;
    }

    @Override
    public int saveChocie(Options choice) {
        InvokeStoredProcedure invokeStoredProcedure = null;
        int insertedChoiceId = -1;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spAddChoice(?,?,?)");
            invokeStoredProcedure.setParameter(1, choice.getOption());
            invokeStoredProcedure.setParameter(2, Integer.parseInt(choice.getValue()));
            invokeStoredProcedure.registerOutputParameterLong(3);

            invokeStoredProcedure.execute();
            insertedChoiceId = invokeStoredProcedure.getOutputParameter(3);

        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            invokeStoredProcedure.closeConnection();
        }


        return insertedChoiceId;
    }

    @Override
    public void saveQuestionChoiceMapping(int insertedQuestionId, Integer insertedChoiceId) {
        InvokeStoredProcedure invokeStoredProcedure = null;
        try {
            invokeStoredProcedure = new InvokeStoredProcedure("spMapQuestionChoice(?,?)");

            invokeStoredProcedure.setParameter(1, insertedQuestionId);
            invokeStoredProcedure.setParameter(2, insertedChoiceId);

            invokeStoredProcedure.execute();
        } catch (Exception e) {
            GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
        } finally {
            invokeStoredProcedure.closeConnection();
        }
    }
}
