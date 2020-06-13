package com.app.group15.model;

public class Policy  extends Persistence{

    private String policyName;
    private String policyValue;
    private Boolean policyState;
    private String policyDesc;

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyValue() {
        return policyValue;
    }

    public void setPolicyValue(String policyValue) {
        this.policyValue = policyValue;
    }

    public Boolean getPolicyState() {
        return policyState;
    }

    public void setPolicyState(Boolean policyState) {
        this.policyState = policyState;
    }

    public String getPolicyDesc() {
        return policyDesc;
    }

    public void setPolicyDesc(String policyDesc) {
        this.policyDesc = policyDesc;
    }



}
