package com.arrowai.chat.Model;

import org.json.JSONArray;
public class Card {
    private String  cardName;
    private String cardImage,cardImageUrl;
    JSONArray buttons;

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    private String cardDescription;


    public Card(String cardName, String cardImage, String cardImageUrl , String cardDescription, JSONArray buttons) {
        this.cardName = cardName;
        this.cardImage = cardImage;
        this.cardDescription = cardDescription;
        this.buttons=buttons;
        this.cardImageUrl=cardImageUrl;

    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
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

/*public class Card {
  private String  cardName;
    private String cardImage,url;
    JSONArray buttons;

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    private String cardDescription;

    public Card(String cardName, String cardImage,String url ,String cardDescription,JSONArray buttons) {
        this.cardName = cardName;
        this.cardImage = cardImage;
        this.cardDescription = cardDescription;
        this.buttons=buttons;
        this.url=url;
    }

    public String getCardImageUrl() {
        return url;
    }

    public void setCardImageUrl(String url) {
        this.url = url;
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
}*/
