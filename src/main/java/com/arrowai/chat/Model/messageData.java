package com.arrowai.chat.Model;

/**
 * Created by rajmendra on 22/06/16.
 */
public class messageData {
    public com.arrowai.chat.Model.message getMessage() {
        return message;
    }

    public void setMessage(com.arrowai.chat.Model.message message) {
        this.message = message;
    }

    public com.arrowai.chat.Model.sender getSender() {
        return sender;
    }

    public void setSender(com.arrowai.chat.Model.sender sender) {
        this.sender = sender;
    }

    public messageData(com.arrowai.chat.Model.message message, com.arrowai.chat.Model.sender sender) {
        this.message = message;
        this.sender = sender;
    }
    public messageData(com.arrowai.chat.Model.postback message, com.arrowai.chat.Model.sender sender) {
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