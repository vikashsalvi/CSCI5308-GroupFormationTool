package com.app.group15.model;

import java.io.Serializable;
import java.util.Date;

public class Question extends Persistence implements Serializable {
    private Date questionDate;
    private int questionId;
    private String questionTitle;
    private int questionTypeId;
    private int questionInstructorId;
    private String questionText;

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public int getQuestionInstructorId() {
        return questionInstructorId;
    }

    public void setQuestionInstructorId(int questionInstructorId) {
        this.questionInstructorId = questionInstructorId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionDate=" + questionDate +
                ", questionId=" + questionId +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionTypeId=" + questionTypeId +
                ", questionInstructorId=" + questionInstructorId +
                ", questionText='" + questionText + '\'' +
                '}';
    }
}
