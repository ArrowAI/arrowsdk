package com.arrowai.chat.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arrowai.chat.R;
import com.arrowai.chat.util.CommonUtil;
import com.firebase.client.Firebase;

public class RegisterActivity extends AppCompatActivity {

    EditText etUserName, etMobile, etPassword, etEmail;
    TextView link_login;
    private String mUsername;
    private Firebase mFirebaseRef;
    Button singnUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUserName = (EditText) findViewById(R.id.input_name);
        etMobile = (EditText) findViewById(R.id.input_mobile);
        etPassword = (EditText) findViewById(R.id.input_password);
        etEmail = (EditText) findViewById(R.id.input_email);
        final Firebase mFirebaseRef = new Firebase(CommonUtil.FIREBASEURL);
        singnUp = (Button) findViewById(R.id.btn_signup);
        link_login = (TextView) findViewById(R.id.link_login);
        singnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                String userName = etUserName.getText().toString();
                String moblile = etMobile.getText().toString();
                Register(userName, email, moblile, password);
            }
        });
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), LoginActivity.class);
//                i.putExtra("PersonID", personID);
                startActivity(i);
            }
        });

    }
    private void Register(String userName, String email, String moblile, String password) {
        if (userName.length() < 1) {
            return;
        }
        if (email.length() < 1) {
            return;
        }
        if (moblile.length() < 1) {
            return;
        }
        if (password.length() < 1) {
            return;
        }
        mFirebaseRef = new Firebase(CommonUtil.FIREBASEURL).child("appUser");
        User user = new User(userName, password, email, moblile);
        mFirebaseRef.push().setValue(user);
       String key= mFirebaseRef.getKey();
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setMessage("Success")
                .setTitle("Registration is successfull")
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
        saveSharedPref(userName,email,moblile,key);
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        intent.putExtra("type", "Chat to boat");
        intent.putExtra("botid", "5731efe1c8ce1f52008b4567");
        intent.putExtra("botName", "Banking Bot");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void saveSharedPref(String name,String email,String mobile,String key){
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", name);
        editor.putString("email",email);
        editor.putString("mobile",mobile);
        editor.putString("userId",key);
        editor.commit();
    }
}