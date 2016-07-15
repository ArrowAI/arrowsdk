package com.btpltech.bankingbot.Model;

/**
 * Created by Ravinder on 6/29/2016.
 */
public class actionButton {
    private String type;
    private String title;
    private String message;
    private buttonPayload payload;
    private variableType variableType;
    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private actionButton() {
    }

    public actionButton(buttonPayload payload, String title,String type, variableType variableType) {
        this.type = type;
        this.title = title;
        this.payload = payload;
        this.variableType = variableType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public buttonPayload getPayload() {
        return payload;
    }

    public void setPayload(buttonPayload payload) {
        this.payload = payload;
    }

    public com.btpltech.bankingbot.Model.variableType getVariableType() {
        return variableType;
    }

    public void setVariableType(com.btpltech.bankingbot.Model.variableType variableType) {
        this.variableType = variableType;
    }
}
