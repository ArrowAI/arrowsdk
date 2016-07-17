package com.arrowai.chat.Model;

/**
 * Created by Ravinder on 6/10/2016.
 */
public class NavItem {
    String title;
    String open_type;
    String bot_id;
    String text;
    String endExistingFlow;

    public String getTitle() {
        return title;
    }

    public NavItem(String title, String open_type, String bot_id, String text, String endExistingFlow) {
        this.title = title;
        this.open_type = open_type;
        this.bot_id = bot_id;
        this.text = text;
        this.endExistingFlow = endExistingFlow;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOpen_type() {
        return open_type;
    }

    public void setOpen_type(String open_type) {
        this.open_type = open_type;
    }

    public String getBot_id() {
        return bot_id;
    }

    public void setBot_id(String bot_id) {
        this.bot_id = bot_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEndExistingFlow() {
        return endExistingFlow;
    }

    public void setEndExistingFlow(String endExistingFlow) {
        this.endExistingFlow = endExistingFlow;
    }
}