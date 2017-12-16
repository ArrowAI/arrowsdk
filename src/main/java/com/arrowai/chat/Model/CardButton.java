package com.arrowai.chat.Model;

/**
 * Created by Asus on 14-12-2017.
 */
import org.json.JSONObject;

/**
 * Created by Ravinder on 10/15/2016.
 */

public class CardButton {
    JSONObject variableType;
    String type;
    JSONObject buttonPayload;
    String title;


    public CardButton(JSONObject variableType, String type, JSONObject buttonPayload, String title) {
        this.variableType = variableType;
        this.type = type;
        this.buttonPayload = buttonPayload;
        this.title = title;
    }

    public JSONObject getVariableType() {
        return variableType;
    }

    public void setVariableType(JSONObject variableType) {
        this.variableType = variableType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JSONObject getButtonPayload() {
        return buttonPayload;
    }

    public void setButtonPayload(JSONObject buttonPayload) {
        this.buttonPayload = buttonPayload;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
