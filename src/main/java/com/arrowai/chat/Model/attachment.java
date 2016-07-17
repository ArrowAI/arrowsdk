package com.arrowai.chat.Model;

/**
 * Created by rajmendra on 22/06/16.
 */
public class attachment {
    String type;
    public attachment(com.arrowai.chat.Model.payload payload, String type) {
        this.payload = payload;
        this.type = type;
    }
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
    payload payload;
    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private attachment() {
    }
}
