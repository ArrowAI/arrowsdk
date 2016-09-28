package com.arrowai.chat.Model;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
