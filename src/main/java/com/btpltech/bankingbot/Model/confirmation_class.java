package com.btpltech.bankingbot.Model;

import org.json.JSONObject;

/**
 * Created by Ravinder on 6/30/2016.
 */
public class confirmation_class {
    private String type;
    private String title;
    private String message;
    private JSONObject payload;
    public confirmation_class (String type, String title, String message, JSONObject payload)
    {

        this.type = type;
        this.title = title;
        this.message = message;
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
