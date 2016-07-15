package com.btpltech.bankingbot.Model;

/**
 * Created by rajmendra on 22/06/16.
 */
public class messageData {
    public com.btpltech.bankingbot.Model.message getMessage() {
        return message;
    }

    public void setMessage(com.btpltech.bankingbot.Model.message message) {
        this.message = message;
    }

    public com.btpltech.bankingbot.Model.sender getSender() {
        return sender;
    }

    public void setSender(com.btpltech.bankingbot.Model.sender sender) {
        this.sender = sender;
    }

    public messageData(com.btpltech.bankingbot.Model.message message, com.btpltech.bankingbot.Model.sender sender) {
        this.message = message;
        this.sender = sender;
    }
    public messageData(com.btpltech.bankingbot.Model.postback message, com.btpltech.bankingbot.Model.sender sender) {
        this.postback = message;
        this.sender = sender;
    }


    message message;
    sender sender;
    postback postback;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private messageData() {
    }
}