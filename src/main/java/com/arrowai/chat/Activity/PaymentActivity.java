package com.arrowai.chat.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.arrowai.chat.R;
import com.arrowai.chat.SQliteData.Account;
import com.arrowai.chat.SQliteData.SQLiteAdapter;
import com.arrowai.chat.SQliteData.Statement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
public class PaymentActivity extends AppCompatActivity {
    private EditText tvMoney;
    private EditText tvMobile;
    private Button addButton;
    private String money;
    private String mobileNo;
    private String mUsername,mUserEmail;
    Account myAccount;
    Statement myBankStatement;
    private SQLiteAdapter sqLiteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doing_payment_widget);

    }
    private String getDate()
    {
        DateFormat dateFormatter = new SimpleDateFormat("MMM dd hh.mm");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s = dateFormatter.format(today);
        return s;
    }
    public  void payBill(String money, String mobile, Context ctx)
    {
        sqLiteAdapter = new SQLiteAdapter(ctx);
        myAccount = new Account(sqLiteAdapter);
        myBankStatement= new Statement(sqLiteAdapter);
        sqLiteAdapter.openToWrite();
        myAccount.insertAccount(mUsername, "", "", "","-"+money,mUserEmail);
        myBankStatement.insertTransaction("",money,getDate(),mUsername,mUserEmail,"Recharge Mobile:"+mobile);
        sqLiteAdapter.close();
    }
    private void setupUsername() {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        mUsername = prefs.getString("username", null);
        mUserEmail = prefs.getString("email", null);
        if (mUsername == null) {
            Random r = new Random();
            prefs.edit().putString("username", mUsername).commit();
        }
    }
}
