package com.btpltech.bankingbot.Model;

/**
 * Created by Ravinder on 6/27/2016.
 */
public class suggestions {
    String text;
    public suggestions(String text) {
        this.text = text;
    }

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private suggestions() {
    }
}
