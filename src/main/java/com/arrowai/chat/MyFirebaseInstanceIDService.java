package com.arrowai.chat;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Asus on 11-12-2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        //Displaying token on logcat
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        //calling the method store token and passing token
        storeToken(refreshedToken);

        database = FirebaseDatabase.getInstance();
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        String appId = prefs.getString("appId", null);
        String userId = prefs.getString("userId", null);
        if (userId!=null) {
            myRef = database.getReference("appUser/" + appId + "/" + userId + "/deviceInfo/FCM_Token");
            myRef.setValue(refreshedToken);
        }

    }

    private void storeToken(String token) {
        //we will save the token in sharedpreferences later
        SharedPrefManager.getInstance(getApplicationContext()).saveDeviceToken(token);
    }
}
