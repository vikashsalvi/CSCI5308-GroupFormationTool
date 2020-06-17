package com.app.group15.QuestionManager;

import com.app.group15.UserManagement.User;

import java.util.List;

public interface IQuestionManagerService {

	public List<QuestionType> getQuestionType();

	public boolean addQuestion(String questionTitle, String questionText, int questionTypeId);

	public Question formQuestion(String questionTitle, String questionText, int selectedOption);

	public List<String> formPreview(Question question);

	public boolean insertQuestion(Question question, User user);

	public List<Question> getAllQuestionsOfInstructor(int instructorId, String sortColumn);

	public List<String> getAllQuestionsType(List<Question> questionList);

	public Question getQuestion(int questionId);

	public List<String> getOptions(int questionId);

	public void deleteByQuestionId(int questionId);
}
