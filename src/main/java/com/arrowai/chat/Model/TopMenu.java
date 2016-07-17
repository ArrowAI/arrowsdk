package com.arrowai.chat.Model;

/**
 * Created by Ravinder on 6/9/2016.
 */
public class TopMenu {
    private String botId;
    private String Image;
    private String name;

    public TopMenu(String botId, String image, String name) {
        this.botId = botId;
        Image = image;
        this.name = name;
    }

    public String getBotId() {
        return botId;
    }

    public void setBotId(String botId) {
        this.botId = botId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TopMenu(String botId) {
        this.botId = botId;
    }
}
