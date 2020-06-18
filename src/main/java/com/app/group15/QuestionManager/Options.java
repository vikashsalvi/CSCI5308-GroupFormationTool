package com.app.group15.QuestionManager;

public class Options {

    private String option;
    private String value;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "OptionValue{" +
                "option='" + option + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}
