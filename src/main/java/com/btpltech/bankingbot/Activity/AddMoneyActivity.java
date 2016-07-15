package com.btpltech.bankingbot.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.btpltech.bankingbot.Adapter.AdapterChatList;
import com.btpltech.bankingbot.R;
import com.btpltech.bankingbot.SQliteData.Account;
import com.btpltech.bankingbot.SQliteData.ChatListData;
import com.btpltech.bankingbot.SQliteData.SQLiteAdapter;
import com.btpltech.bankingbot.SQliteData.Statement;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class AddMoneyActivity extends AppCompatActivity {
    private EditText tvMoney;
    private Button addButton;
    private String money;
    private String mUsername,mUserEmail;
    Account myAccount;
    Statement myBankStatement;
    private SQLiteAdapter sqLiteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_money);
        tvMoney = (EditText) findViewById(R.id.input_money);
        addButton = (Button) findViewById(R.id.btn_add);
         setupUsername();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                money = tvMoney.getText().toString();
                if (money.isEmpty()) {
                    return;
                }
               // addMoney(money);

            }
        });

    }
    private String getDate()
    {
        DateFormat dateFormatter = new SimpleDateFormat("MMM dd hh.mm");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s = dateFormatter.format(today);
        return s;
    }

public void addMoney(String money, Context ctx)
{
    sqLiteAdapter = new SQLiteAdapter(ctx);
    myAccount = new Account(sqLiteAdapter);
    myBankStatement= new Statement(sqLiteAdapter);
    sqLiteAdapter.openToWrite();
    myAccount.insertAccount(mUsername, "", "", "",money,mUserEmail);
    myBankStatement.insertTransaction("",money,getDate(),mUsername,mUserEmail,"Self");
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
