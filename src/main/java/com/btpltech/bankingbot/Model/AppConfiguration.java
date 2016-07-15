package com.btpltech.bankingbot.Model;

import android.content.SharedPreferences;

/**
 * Created by Ravinder on 7/14/2016.
 */
public class AppConfiguration {
    public AppConfiguration() {
    }


    public  void setAppId(android.content.Context ctx, String appId) {
        SharedPreferences prefs =ctx.getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("appId", appId);
        editor.commit();

    }
}
