package com.btpltech.bankingbot.Model;

/**
 * Created by Ravinder on 6/1/2016.
 */
public class Card {
  private String  cardName;
    private String cardImage;

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    private String cardDescription;

    public Card(String cardName, String cardImage ,String cardDescription) {
        this.cardName = cardName;
        this.cardImage = cardImage;
        this.cardDescription = cardDescription;
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
}
