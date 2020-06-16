package com.app.group15.QuestionManager;

public class QuestionChoiceMapper {

    private int id;
    private int questionId;
    private int questionChoiceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionChoiceId() {
        return questionChoiceId;
    }

    public void setQuestionChoiceId(int questionChoiceId) {
        this.questionChoiceId = questionChoiceId;
    }


    @Override
    public String toString() {
        return "QuestionChoiceMapper{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", questionChoiceId=" + questionChoiceId +
                '}';
    }
}
