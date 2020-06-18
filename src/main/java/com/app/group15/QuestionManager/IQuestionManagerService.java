package com.app.group15.QuestionManager;

import com.app.group15.UserManagement.User;

import java.util.List;

public interface IQuestionManagerService {

	List<QuestionType> getQuestionType();

	Question formQuestion(String questionTitle, String questionText, int selectedOption);

	List<String> formPreview(Question question);

	boolean insertQuestion(Question question, User user);

	List<Question> getAllQuestionsOfInstructor(int instructorId, String sortColumn);

	Question getQuestion(int questionId);

	List<String> getOptions(int questionId);
}
