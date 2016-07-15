package com.btpltech.bankingbot.Model;

import org.json.JSONObject;

/**
 * Created by Ravinder on 6/27/2016.
 */
public class botInitial {

    String applicationId;
    String botId;
    int start;
    sender sender;
    String endExistingFlow;
    boolean sentFromServer;
    boolean sentFromUser;
    long timestamp;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public com.btpltech.bankingbot.Model.sender getSender() {
        return sender;
    }

    public void setSender(com.btpltech.bankingbot.Model.sender sender) {
        this.sender = sender;
    }

    public String getEndExistingFlow() {
        return endExistingFlow;
    }

    public void setEndExistingFlow(String endExistingFlow) {
        this.endExistingFlow = endExistingFlow;
    }

    public boolean isSentFromServer() {
        return sentFromServer;
    }

    public void setSentFromServer(boolean sentFromServer) {
        this.sentFromServer = sentFromServer;
    }

    public boolean isSentFromUser() {
        return sentFromUser;
    }

    public void setSentFromUser(boolean sentFromUser) {
        this.sentFromUser = sentFromUser;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public botInitial(String applicationId, String botId, int start, com.btpltech.bankingbot.Model.sender sender, boolean sentFromServer, boolean sentFromUser, long timestamp) {
        this.applicationId = applicationId;
        this.botId = botId;
        this.start = start;
        this.sender = sender;
        this.sentFromServer = sentFromServer;
        this.sentFromUser = sentFromUser;
        this.timestamp = timestamp;
    }
}

