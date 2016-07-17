package com.arrowai.chat.Model;

import java.util.ArrayList;

/**
 * Created by rajmendra on 22/06/16.
 */
public class payload {
    public String template_type;
    public String text;
    ArrayList<buttons> buttons;
    confirmation confirmation;
    ArrayList<actionButton> actionButton;
    ArrayList<details> details;

    ArrayList<list> list;
     // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private payload() {
    }
    public ArrayList<buttons> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList< buttons> buttons) {
        this.buttons = buttons;
    }

    public com.arrowai.chat.Model.confirmation getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(com.arrowai.chat.Model.confirmation confirmation) {
        this.confirmation = confirmation;
    }

    public ArrayList<com.arrowai.chat.Model.list> getList() {
        return list;
    }

    public void setList(ArrayList<com.arrowai.chat.Model.list> list) {
        this.list = list;
    }

//    public payload(ArrayList<list> list, ArrayList<buttons> buttons,confirmation confirmation, String template_type, String text) {
//        this.template_type = template_type;
//        this.text = text;
//        this.buttons = buttons;
//        this.list=list;
//        this.actionButton = actionButton;
//        this.details = details;
//        this.confirmation=confirmation;
//    }

    public payload(confirmation confirmation, String template_type, String text) {
        this.template_type = template_type;
        this.text = text;
        this.confirmation=confirmation;
    }
    public ArrayList<actionButton> getActionButtons() {
        return actionButton;
    }

    public void setActionButtons(ArrayList<actionButton> actionButtons) {
        this.actionButton = actionButtons;
    }

    public ArrayList<details> getDetailse() {
        return details;
    }

    public void setDetailse(ArrayList<details> detailse) {
        this.details = detailse;
    }
    public String getTemplate_type() {
        return template_type;
    }

    public void setTemplate_type(String template_type) {
        this.template_type = template_type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }




    // Required default constructor for Firebase object mapping

}
