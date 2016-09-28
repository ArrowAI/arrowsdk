package com.arrowai.chat.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.arrowai.chat.R;
import com.arrowai.chat.util.CommonUtil;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class LoginActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    TextView link_signup;
    private String mUsername;
    private Firebase mFirebaseRef;
    Button loginButton;
    String username;
    String password;
    String email;
    FirebaseDatabase database;

    Query myRefQuery;

    public void getSharedPref() {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        username = prefs.getString("username", "");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSharedPref();
        if (!username.equals("")) {
            // Intent i = new Intent(getBaseContext(), ChatActivity.class);
            Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
            intent.putExtra("type", "Chat to boat");
            intent.putExtra("botid", "5731efe1c8ce1f52008b4567");
            intent.putExtra("botName", "Banking Bot");
            intent.putExtra("bots", intent.getStringExtra("bots"));
            intent.putExtra("sideMenu", intent.getStringExtra("sideMenu"));
            startActivity(intent);
        }
        emailEditText = (EditText) findViewById(R.id.input_email);
        passwordEditText = (EditText) findViewById(R.id.input_password);
        loginButton = (Button) findViewById(R.id.btn_login);
        link_signup = (TextView) findViewById(R.id.link_signup);
        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase(CommonUtil.FIREBASEURL);

        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
               // startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();

                email = email.trim();
                password = password.trim();

                if (email.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Invalid userid or password")
                            .setTitle("Try Again")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    String userEmail=email.toString();
                    database = FirebaseDatabase.getInstance();
                    myRefQuery = database.getReference("appUser").orderByChild("email").equalTo(userEmail);
                    myRefQuery.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                        @Override
                        public void onDataChange(com.google.firebase.database.DataSnapshot snapshot) {
                            for (com.google.firebase.database.DataSnapshot postSnapshot : snapshot.getChildren()) {
                                //Getting the data from snapshot
                                String userName = (String) postSnapshot.child("userName").getValue();
                                String emailUser = (String) postSnapshot.child("email").getValue();
                                String passUser = (String) postSnapshot.child("password").getValue();
                                String mobile = (String) postSnapshot.child("mobile").getValue();
                                String key = (String) postSnapshot.getKey();
                                if (emailUser.equals(email) && passUser.equals(password)) {
                                    saveSharedPref(userName, email, mobile, key);
                                    Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                                    intent.putExtra("type", "Chat to boat");
                                    intent.putExtra("botid", "5731efe1c8ce1f52008b4567");
                                    intent.putExtra("botName", "Banking Bot");
                                    intent.putExtra("bots", intent.getStringExtra("bots"));
                                    intent.putExtra("sideMenu", intent.getStringExtra("sideMenu"));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                } else {
                                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(LoginActivity.this);
                                    builder.setMessage("Error")
                                            .setTitle("Login details are not correct")
                                            .setPositiveButton(android.R.string.ok, null);
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    public void saveSharedPref(String name, String email, String mobile, String key) {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", name);
        editor.putString("email", email);
        editor.putString("mobile", mobile);
        editor.putString("userId", key);
        editor.commit();
    }
}
