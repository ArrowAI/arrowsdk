package com.btpltech.bankingbot.Model;

/**
 * Created by Ravinder on 6/29/2016.
 */
public class list {
    String description;
    String image;
    String title;
    String type;
    String date;
    String amount;
    String transaction_type;
    String payementTo;
    public list() {
    }

    public list(String description, String image, String title, String type) {
        this.description = description;
        this.image = image;
        this.title = title;
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getPayementTo() {
        return payementTo;
    }

    public void setPayementTo(String payementTo) {
        this.payementTo = payementTo;
    }

    public list(String type, String date, String amount, String transaction_type, String payementTo) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.transaction_type = transaction_type;
        this.payementTo = payementTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
