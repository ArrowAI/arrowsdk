package com.btpltech.bankingbot.Model;

/**
 * Created by Ravinder on 5/31/2016.
 */
public class Transaction {


    private String accountNo;
    private String amount;
    private String date;
    private String userName;
    private String email;
    private String reason;

    public Transaction(String accountNo, String amount, String date, String userName, String email, String reason) {
        this.accountNo = accountNo;
        this.amount = amount;
        this.date = date;
        this.userName = userName;
        this.email = email;
        this.reason = reason;
    }


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
