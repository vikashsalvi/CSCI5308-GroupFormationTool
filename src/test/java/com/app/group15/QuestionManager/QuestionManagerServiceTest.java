package com.app.group15.QuestionManager;

import com.app.group15.userManagement.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionManagerServiceTest {

    QuestionManagerDaoMock questionManagerDaoMock = new QuestionManagerDaoMock();

    @Test
    public void getQuestionTypeTest() {
        List<QuestionType> allQuestionType = questionManagerDaoMock.getAllQuestionType();
        assertEquals(allQuestionType.get(0).getId(), 2);
        assertNotNull(allQuestionType);
    }

    @Test
    public void formQuestionTest() {
        Question question = questionManagerDaoMock.formQuestion();
        assertEquals(question.getQuestionText(), "This is question");
        assertNotEquals(question.getQuestionTitle(), "This is question");
    }

    @Test
    public void formPreviewTest() {
        List<Options> options = questionManagerDaoMock.formPreview();
        assertNotNull(options);

        assertEquals(options.get(0).getOption(), "option 1");
        assertEquals(options.get(1).getOption(), "option 2");
        assertNotEquals(options.get(0).getValue(), "2");
        assertNotEquals(options.get(1).getValue(), "1");
    }

    @Test
    public void insertQuestionTest() {
        Question qst = questionManagerDaoMock.formQuestion();
        User user = new User();
        user.setId(1);
        Question inssertedQuestion = questionManagerDaoMock.insertQuestion(qst, user);
        assertNotNull(inssertedQuestion);
        assertEquals(inssertedQuestion.getQuestionInstructorId(), 1);
    }

}
