package com.btpltech.bankingbot.Model;

import org.json.JSONObject;

/**
 * Created by Ravinder on 6/7/2016.
 */
public class ButtonTemplate  {

    private String type;
    private String title;
    private String message;
    private JSONObject payload;
    public ButtonTemplate( String title,  JSONObject payload) {
        this.title = title;
        this.payload = payload;
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

    public JSONObject getPayload() {
        return payload;
    }

    public void setPayload(JSONObject payload) {
        this.payload = payload;
    }
}
