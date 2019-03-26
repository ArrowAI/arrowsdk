package com.arrowai.chat.Model;

/**
 * Created by rajmendra on 22/06/16.
 */
public class VariableType {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    String  type;
    String valueName;
    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private VariableType() {
    }

}
