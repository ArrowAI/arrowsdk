package com.arrowai.chat.Model;

/**
 * Created by rajmendra on 22/06/16.
 */
public class ActionButton {
    payload payload;

    public com.arrowai.chat.Model.payload getPayload() {
        return payload;
    }

    public void setPayload(com.arrowai.chat.Model.payload payload) {
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String title;
    String type;
    VariableType variableType;

    public VariableType getVariableType() {
        return variableType;
    }

    public void setVariableType(VariableType variableType) {
        this.variableType = variableType;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private ActionButton() {
    }

}
