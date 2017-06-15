package com.arrowai.chat.Model;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Ravinder on 6/9/2016.
 */
public class TopMenu {
    private JSONArray action;
    private String name;
    private String botId;
    private JSONObject payload;

    public TopMenu(JSONArray action, String name,String botId,JSONObject payload) {
        this.action = action;
        this.name = name;
        this.botId = botId;
        this.payload = payload;
    }

    public JSONArray getAction() {
        return action;
    }

    public void setAction(JSONArray action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public JSONObject getPayload() {
        return payload;
    }

    public void setPayload(JSONObject payload) {
        this.payload = payload;
    }
}