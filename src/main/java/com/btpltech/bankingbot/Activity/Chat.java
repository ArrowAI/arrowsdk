package com.btpltech.bankingbot.Activity;

import org.json.JSONObject;

/**
 * @author greg
 * @since 6/21/13
 */
public class Chat
{
    private String message;

    public String getChatbot() {
        return chatbot;
    }

    public void setChatbot(String chatbot) {
        this.chatbot = chatbot;
    }

    public String getChatuser() {
        return chatuser;
    }

    public void setChatuser(String chatuser) {
        this.chatuser = chatuser;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String from;
    private String chatuser;
    private String chatbot;
    private String time;


    public JSONObject getAttachmentJson() {
        return attachmentJson;
    }

    public void setAttachmentJson(JSONObject attachmentJson) {
        this.attachmentJson = attachmentJson;
    }

    private JSONObject attachmentJson;


    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public  Chat(String chatuser, String chatbot, String message, String from, String time, JSONObject attachmentJson,String type)
    {
        this.message = message;
        this.from = from;
        this.chatuser = chatuser;
        this.chatbot = chatbot;
        this.time = time;
        this.attachmentJson = attachmentJson;
        this.type =type;

    }




}
