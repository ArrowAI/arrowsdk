package com.arrowai.chat.Model;

/**
 * Created by rajmendra on 22/06/16.
 */
public class sender {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    public sender(String id){
        this.id=id;
    }
    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private sender() {
    }
}
