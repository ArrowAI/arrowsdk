package com.arrowai.chat.Model;

import org.json.JSONObject;

/**
 * Created by Asus on 11-12-2017.
 */

public class CardButtonItem {

    private String name, value;


    private JSONObject payload;


    public CardButtonItem(String name, String value, JSONObject payload) {
        this.name = name;
        this.value= value;
        this.payload = payload;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {

        return name;
    }

    public String getValue() {
        return value;
    }

    public JSONObject getPayload() {
        return payload;
    }
    public void setPayload(JSONObject payload) {
        this.payload = payload;
    }


}
