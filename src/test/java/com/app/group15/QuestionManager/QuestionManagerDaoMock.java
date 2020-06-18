package com.app.group15.QuestionManager;

import com.app.group15.UserManagement.User;

import java.util.ArrayList;
import java.util.List;

public class QuestionManagerDaoMock {


	public List<QuestionType> getAllQuestionType() {
		List<QuestionType> list = new ArrayList<>();
		QuestionType questionType = new QuestionType();
		questionType.setQuestionType("NUMERIC");
		questionType.setId(1);
		QuestionType questionType_2 = new QuestionType();
		questionType.setQuestionType("MCQ_SC");
		questionType.setId(2);
		list.add(questionType);
		list.add(questionType_2);
		return list;
	}

	public Question formQuestion() {
		Question question = new Question();
		question.setQuestionInstructorId(1);
		question.setQuestionTypeId(1);
		question.setQuestionText("This is question");
		question.setQuestionTitle("Question");
		return question;
	}

	public List<Options> formPreview() {
		List<Options> op = new ArrayList<>();
		Options options = new Options();
		options.setOption("option 1");
		options.setValue("1");
		Options options2 = new Options();
		options2.setOption("option 2");
		options2.setValue("2");
		op.add(options);
		op.add(options2);
		return op;
	}

	public Question insertQuestion(Question question, User user) {
		Question qut = question;
		question.setQuestionInstructorId(user.getId());
		return qut;
	}

	public List<Question> getAllQuestionsOfInstructor(int instructorId) {
		int i;
		List<Question> questionList = new ArrayList<>();
		for (i = 0; i < 5; i++) {
			Question question = new Question();
			String questionTitle = String.format("Question %d Title", i);
			String questionText = String.format("Question %d Text", i);
			int questionType = (i + 1) % 4;
			question.setQuestionId(i);
			question.setQuestionTitle(questionTitle);
			question.setQuestionTypeId(questionType);
			question.setQuestionText(questionText);
			questionList.add(question);
		}
		return questionList;
	}

	public Question get() {
		Question question = new Question();
		question.setQuestionInstructorId(1);
		question.setQuestionTypeId(1);
		question.setQuestionText("This is question");
		question.setQuestionTitle("Question");
		return question;
	}
}
