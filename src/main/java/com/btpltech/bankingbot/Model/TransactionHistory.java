package com.btpltech.bankingbot.Model;

/**
 * Created by Ravinder on 6/6/2016.
 */
public class TransactionHistory {
    private String type;
    private String date;
    private  String amount;
    private String transaction_type;
    private  String payementTo;
    public TransactionHistory(String type, String date, String amount, String transaction_type, String payementTo) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.transaction_type = transaction_type;
        this.payementTo = payementTo;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
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

}
