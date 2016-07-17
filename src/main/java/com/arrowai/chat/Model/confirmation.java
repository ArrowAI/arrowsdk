package com.arrowai.chat.Model;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ravinder on 6/7/2016.
 */
public class confirmation {

    private String type;
    private String title;
    private String message;
    private JSONObject payload;
    ArrayList<actionButton> actionButton;
    ArrayList<details> details;


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

    public ArrayList<actionButton> getActionButtons() {
        return actionButton;
    }

    public void setActionButtons(ArrayList<actionButton> actionButtons) {
        this.actionButton = actionButtons;
    }

    public ArrayList<details> getDetailse() {
        return details;
    }

    public void setDetailse(ArrayList<details> detailse) {
        this.details = detailse;
    }

    public confirmation (ArrayList<actionButton> actionButton, ArrayList<details> details, String type, String title)
    {
        this.actionButton = actionButton;
        this.details = details;
        this.type = type;
        this.title = title;
    }


    @SuppressWarnings("unused")
    private confirmation() {
    }

}
