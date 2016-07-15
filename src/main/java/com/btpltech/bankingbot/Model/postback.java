package com.btpltech.bankingbot.Model;

/**
 * Created by Ravinder on 6/29/2016.
 */
public class  postback {
    buttonPayload payload;
    public postback() {
    }

    public buttonPayload getPayload() {
        return payload;
    }

    public void setPayload(buttonPayload payload) {
        this.payload = payload;
    }

    public postback(buttonPayload payload) {
        this.payload = payload;
    }
}
