package com.arrowai.chat.Model;

/**
 * Created by Ravinder on 6/28/2016.
 */
public class IntitialResponse {
    String botId;
    String applicationId;
    String payload;

    public com.arrowai.chat.Model.start getStart() {
        return start;
    }

    public void setStart(com.arrowai.chat.Model.start start) {
        this.start = start;
    }


    public IntitialResponse(String botId, String applicationId, start start, com.arrowai.chat.Model.sender sender, boolean sentFromServer, boolean sentFromUser, long timestamp) {
        this.botId = botId;
        this.applicationId = applicationId;
        this.sender = sender;
        this.sentFromServer = sentFromServer;
        this.sentFromUser = sentFromUser;
        this.timestamp = timestamp;
        this.start = start;
    }



    message message;
    start start;
    sender sender;
    boolean sentFromServer;
    boolean sentFromUser;
    long timestamp;

    public com.arrowai.chat.Model.message getMessage() {
        return message;
    }

    public void setMessage(com.arrowai.chat.Model.message message) {
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

    public com.arrowai.chat.Model.sender getSender() {
        return sender;
    }

    public void setSender(com.arrowai.chat.Model.sender sender) {
        this.sender = sender;
    }

    public boolean getSentFromServer() {
        return sentFromServer;
    }

    public void setSentFromServer(boolean sentFromServer) {
        this.sentFromServer = sentFromServer;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public com.arrowai.chat.Model.message getMessageData() {
        return message;
    }

    public void setMessageData(com.arrowai.chat.Model.message messageData) {
        this.message = messageData;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private IntitialResponse() {
    }
}
