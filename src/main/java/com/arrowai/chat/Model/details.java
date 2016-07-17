package com.arrowai.chat.Model;

/**
 * Created by Ravinder on 6/29/2016.
 */
public class details {
    private String type;
    private String title;
    private String message;
    private buttonPayload payload;
    private variableType variableType;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    public details() {
    }
    public details( String message, buttonPayload payload,String type, String title,com.arrowai.chat.Model.variableType variableType) {
        this.type = type;
        this.title = title;
        this.message = message;
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

    public com.arrowai.chat.Model.variableType getVariableType() {
        return variableType;
    }

    public void setVariableType(com.arrowai.chat.Model.variableType variableType) {
        this.variableType = variableType;
    }
}