package com.app.group15.QuestionManager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionChoiceMapperServiceTest {
    QuestionChoiceMapperDaoMock questionChoiceMapperDaoMock = new QuestionChoiceMapperDaoMock();

    @Test
    void deleteByQuestionIdTest() {
        int resp = questionChoiceMapperDaoMock.deleteByQuestionId(1);
        assertEquals(resp, 0);
    }
}
