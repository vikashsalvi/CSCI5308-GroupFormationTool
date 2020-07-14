package com.app.group15.SurveyManagement;

import com.app.group15.QuestionManager.Options;

import java.util.List;

public class SurveyResponse {
    private int questionId;
    private int questionTypeId;
    private String choiceId;
    private int numericResponse;
    private String textResponse;
    private String questionTitle;
    private int questionInstructorId;
    private String questionText;
    private String questionAddedDate;
    private List<Options> options;


    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
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

    public String getQuestionAddedDate() {
        return questionAddedDate;
    }

    public void setQuestionAddedDate(String questionAddedDate) {
        this.questionAddedDate = questionAddedDate;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public String getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(String choiceId) {
        this.choiceId = choiceId;
    }

    public int getNumericResponse() {
        return numericResponse;
    }

    public void setNumericResponse(int numericResponse) {
        this.numericResponse = numericResponse;
    }

    public String getTextResponse() {
        return textResponse;
    }

    public void setTextResponse(String textResponse) {
        this.textResponse = textResponse;
    }


}
