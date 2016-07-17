package com.arrowai.chat.Model;

/**
 * Created by Ravinder on 6/28/2016.
 */
public class buttonPayload {
    String variable;
    String value;

    public buttonPayload(String variable, String value) {
        this.variable = variable;
        this.value = value;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public buttonPayload() {
    }
}
