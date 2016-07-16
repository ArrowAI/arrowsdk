package com.btpltech.bankingbot.Model;

import android.content.SharedPreferences;

import com.btpltech.bankingbot.Activity.ChatActivity;
import com.btpltech.bankingbot.Activity.SplashActivity;

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
        if (ctx instanceof ChatActivity) {
            ((ChatActivity) ctx).getUserId();
        }
        if (ctx instanceof SplashActivity) {
            ((SplashActivity) ctx).bindMenu();
        }

    }
}
