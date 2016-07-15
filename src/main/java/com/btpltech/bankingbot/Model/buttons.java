package com.btpltech.bankingbot.Model;

/**
 * Created by Ravinder on 6/28/2016.
 */
public class buttons {
   public buttonPayload payload;
    public  String title;
    public String type;
    public variableType variableType;

    public buttons() {
    }

    public buttons(buttonPayload payload, String title, String type, com.btpltech.bankingbot.Model.variableType variableType) {
        this.payload = payload;
        this.title = title;
        this.type = type;
        this.variableType = variableType;
    }

    public buttonPayload getPayload() {
        return payload;
    }

    public void setPayload(buttonPayload payload) {
        this.payload = payload;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public com.btpltech.bankingbot.Model.variableType getVariableType() {
        return variableType;
    }

    public void setVariableType(com.btpltech.bankingbot.Model.variableType variableType) {
        this.variableType = variableType;
    }
}
