package com.btpltech.bankingbot.Model;

/**
 * Created by Ravinder on 6/28/2016.
 */
public class variableType {
    String type;
    String valueName;

    private variableType() {
    }

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

    public variableType(String type, String valueName) {
        this.type = type;
        this.valueName = valueName;
    }
}
