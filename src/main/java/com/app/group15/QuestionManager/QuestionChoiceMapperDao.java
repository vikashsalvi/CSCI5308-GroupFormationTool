package com.app.group15.QuestionManager;

import com.app.group15.Persistence.InvokeStoredProcedure;
import com.app.group15.Utility.GroupFormationToolLogger;

import java.util.logging.Level;

public class QuestionChoiceMapperDao extends QuestionChoiceMapperAbstractDao {

	@Override
	public void deleteByQuestionId(int questionId) {
		InvokeStoredProcedure invokeStoredProcedure = null;
		try {
			invokeStoredProcedure = new InvokeStoredProcedure("spDeleteQuestionChoiceMapper(?)");

			invokeStoredProcedure.setParameter(1, questionId);
			invokeStoredProcedure.execute();
		} catch (Exception e) {
			GroupFormationToolLogger.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			invokeStoredProcedure.closeConnection();
		}
	}
}
