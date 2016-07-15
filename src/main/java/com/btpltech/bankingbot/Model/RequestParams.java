package com.btpltech.bankingbot.Model;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rajmendra on 22/06/16.
 */
public class RequestParams {
    String botId;
    String applicationId;
    payLoadId payload;
    buttonPayload payloadPram;

    public com.btpltech.bankingbot.Model.payLoadId getPayload() {
        return payload;
    }

    public void setPayload(com.btpltech.bankingbot.Model.payLoadId payload) {
        this.payload = payload;
    }

    public RequestParams(String botId, String applicationId, payLoadId payload, messageData messageData, message message, com.btpltech.bankingbot.Model.sender sender, boolean sentFromServer, boolean sentFromUser, long timestamp) {
        this.botId = botId;
        this.applicationId = applicationId;
        this.payload = payload;
        this.messageData = messageData;
        this.message = message;
        this.sender = sender;
        this.sentFromServer = sentFromServer;
        this.sentFromUser = sentFromUser;
        this.timestamp = timestamp;

    }

    messageData messageData;
    message message;
    sender sender;
    boolean sentFromServer;
    boolean sentFromUser;
    long timestamp;

    public com.btpltech.bankingbot.Model.message getMessage() {
        return message;
    }

    public void setMessage(com.btpltech.bankingbot.Model.message message) {
        this.message = message;
    }
    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getSentFromUser() {
        return sentFromUser;
    }

    public void setSentFromUser(boolean sentFromUser) {
        this.sentFromUser = sentFromUser;
    }

    public com.btpltech.bankingbot.Model.sender getSender() {
        return sender;
    }

    public void setSender(com.btpltech.bankingbot.Model.sender sender) {
        this.sender = sender;
    }

    public boolean getSentFromServer() {
        return sentFromServer;
    }

    public void setSentFromServer(boolean sentFromServer) {
        this.sentFromServer = sentFromServer;
    }


    public com.btpltech.bankingbot.Model.messageData getMessageData() {
        return messageData;
    }

    public void setMessageData(com.btpltech.bankingbot.Model.messageData messageData) {
        this.messageData = messageData;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private RequestParams() {
    }
}
