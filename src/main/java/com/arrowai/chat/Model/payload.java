package com.arrowai.chat.Model;

import android.content.Context;

import java.util.List;

/**
 * Created by rajmendra on 22/06/16.
 */
public class payload {
    String template_type;
    String text;
    String value;
    String variable;
    List<PayLoadList> list;



    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private payload() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getTemplate_type() {
        return template_type;
    }

    public void setTemplate_type(String template_type) {
        this.template_type = template_type;
    }

    public Confirmation getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Confirmation confirmation) {
        this.confirmation = confirmation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    Confirmation confirmation;
}
