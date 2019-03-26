package com.arrowai.chat.Model;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Ravinder on 6/7/2016.
 */
public class Confirmation {

    private String type;
    private String title;
    private String message;
    private JSONObject payload;

    public List<ActionButton> getActionButton() {
        return actionButton;
    }

    public void setActionButton(List<ActionButton> actionButton) {
        this.actionButton = actionButton;
    }

    public List<Details> getDetails() {
        return details;
    }

    public void setDetails(List<Details> details) {
        this.details = details;
    }

    private List<ActionButton> actionButton;
    private List<Details> details;
    public Confirmation(String type, String title, String message, JSONObject payload) {
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
