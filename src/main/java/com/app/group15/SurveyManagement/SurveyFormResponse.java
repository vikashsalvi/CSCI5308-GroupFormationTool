package com.app.group15.SurveyManagement;

import java.util.List;

public class SurveyFormResponse {


    private List<SurveyResponse> surveyResponse;
    private int surveyId;

    public List<SurveyResponse> getSurveyResponse() {
        return surveyResponse;
    }

    public void setSurveyResponse(List<SurveyResponse> surveyResponse) {
        this.surveyResponse = surveyResponse;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }
}
