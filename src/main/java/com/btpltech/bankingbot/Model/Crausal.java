package com.btpltech.bankingbot.Model;

/**
 * Created by Ravinder on 6/1/2016.
 */
public class Crausal {
    private String  cardName;
    private String cardImage;

    public Crausal(String cardName, String cardImage) {
        this.cardName = cardName;
        this.cardImage = cardImage;
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

