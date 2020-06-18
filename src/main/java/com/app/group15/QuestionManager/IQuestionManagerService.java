package com.app.group15.QuestionManager;

import com.app.group15.UserManagement.User;

import java.util.List;

public interface IQuestionManagerService {

	public List<QuestionType> getQuestionType();

	public Question formQuestion(String questionTitle, String questionText, int selectedOption);

	public List<String> formPreview(Question question);

	public boolean insertQuestion(Question question, User user);

	public List<Question> getAllQuestionsOfInstructor(int instructorId, String sortColumn);


	public Question getQuestion(int questionId);

	public List<String> getOptions(int questionId);
}
