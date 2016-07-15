package com.btpltech.bankingbot.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ravinder on 6/27/2016.
 */
public class start {
   public String id;
    public  String initialGreeting;
    ArrayList<String> suggestions;
    public long isStart;
    public start(String id, String initialGreeting, ArrayList<String> suggestions) {
        this.id = id;
        this.initialGreeting = initialGreeting;
        this.suggestions=suggestions ;
    }
    public start(long isStart) {
        isStart=isStart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInitialGreeting() {
        return initialGreeting;
    }

    public void setInitialGreeting(String initialGreeting) {
        this.initialGreeting = initialGreeting;
    }

    public ArrayList<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(ArrayList<String> suggestions) {
        this.suggestions = suggestions;
    }
    private start() {
    }
}
