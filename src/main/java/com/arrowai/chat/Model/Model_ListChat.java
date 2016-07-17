package com.arrowai.chat.Model;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Suman kumar jha on 5/18/2016.
 */
public class Model_ListChat
{
    private String _id;
    private String name;
//    private String image_url;
    int image_url [];
    private String description;
    private String cateogory;

    public JSONArray getBots() {
        return bots;
    }
    public JSONArray getMnue() {
        return menu;
    }

    public void setBots(JSONArray bots) {
        this.bots = bots;
    }

    private  JSONArray bots;
    private  JSONArray menu;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;
    JSONObject defaultbot;

    public JSONArray getMenu() {
        return menu;
    }

    public void setMenu(JSONArray menu) {
        this.menu = menu;
    }

    public JSONObject getDefaultbot() {
        return defaultbot;
    }

    public void setDefaultbot(JSONObject defaultbot) {
        this.defaultbot = defaultbot;
    }

    public Model_ListChat(String _id, String name, String description, String cateogory, int[] image_url, String image, JSONArray bots, JSONArray menu, JSONObject defaultbot) {
        this._id = _id;
        this.name = name;
        this.image_url = image_url;
        this.description = description;
        this.cateogory = cateogory;
        this.image=image;
        this.bots=bots;
        this.menu=menu;
        this.defaultbot=defaultbot;
    }

    public String get_id()
    {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCateogory() {
        return cateogory;
    }

    public void setCateogory(String cateogory) {
        this.cateogory = cateogory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int[] getImage_url() {
        return image_url;
    }

    public void setImage_url(int[] image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
