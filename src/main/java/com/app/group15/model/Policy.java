package com.app.group15.model;

public class Policy extends Persistence{

    private String policy_name;
    private String policy_value;
    private Boolean policy_state;

    public String getPolicy_name() {
        return policy_name;
    }

    public void setPolicy_name(String policy_name) {
        this.policy_name = policy_name;
    }

    public String getPolicy_value() {
        return policy_value;
    }

    public void setPolicy_value(String policy_value) {
        this.policy_value = policy_value;
    }

    public Boolean getPolicy_state() {
        return policy_state;
    }

    public void setPolicy_state(Boolean policy_state) {
        this.policy_state = policy_state;
    }



}
