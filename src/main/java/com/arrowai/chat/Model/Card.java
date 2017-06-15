package com.arrowai.chat.Model;

import org.json.JSONArray;

/**
 * Created by Ravinder on 6/1/2016.
 */
public class Card {
  private String  cardName;
    private String cardImage;
    JSONArray buttons;

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    private String cardDescription;

    public Card(String cardName, String cardImage ,String cardDescription,JSONArray buttons) {
        this.cardName = cardName;
        this.cardImage = cardImage;
        this.cardDescription = cardDescription;
        this.buttons=buttons;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    public JSONArray getButtons() {
        return buttons;
    }

    public void setButtons(JSONArray buttons) {
        this.buttons = buttons;
    }
}
