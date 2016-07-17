package com.arrowai.chat.Model;

/**
 * Created by Suman kumar jha on 5/18/2016.
 */
public class Model_ListChatDetails
{
    private String _id;
    private String name;
    private String image_url;
    private String description;
    private String cateogory;

    public Model_ListChatDetails(String _id, String name, String image_url, String description, String cateogory) {
        this._id = _id;
        this.name = name;
        this.image_url = image_url;
        this.description = description;
        this.cateogory = cateogory;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
