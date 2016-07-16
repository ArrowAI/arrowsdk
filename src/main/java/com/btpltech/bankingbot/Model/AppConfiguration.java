package com.btpltech.bankingbot.Model;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.btpltech.bankingbot.Activity.ChatActivity;
import com.btpltech.bankingbot.Activity.LoginActivity;
import com.btpltech.bankingbot.Activity.SplashActivity;
import com.btpltech.bankingbot.util.AppController;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ravinder on 7/14/2016.
 */
public class AppConfiguration {
    public AppConfiguration() {
    }
    String bot;
    String sideMenus;
    String appId;

    JSONArray bots, sideMenu = new JSONArray();
    FirebaseDatabase database;
    DatabaseReference myRef;
    public  void setAppId(android.content.Context ctx, String appId) {
        SharedPreferences prefs =ctx.getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("appId", appId);
        editor.commit();
        getUserId(ctx);
        getSharedPref(ctx);
        if (!bot.equals("")) {

        } else {
            bindMenu(ctx);
        }
    }
    public void getUserId(android.content.Context ctx) {
        String android_id = Settings.Secure.getString(ctx.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(android.content.Context.TELEPHONY_SERVICE);
        WifiManager wm = (WifiManager) ctx.getSystemService(android.content.Context.WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        String deviceinfo = "IMEI : " + ""//telephonyManager.getDeviceId()
                + " \nIP : " + ip
                + " \nANDROID_ID : " + android_id
                + " \nVERSION.RELEASE : " + Build.VERSION.RELEASE
                + " \nVERSION.INCREMENTAL : " + Build.VERSION.INCREMENTAL
                + " \nVERSION.SDK.NUMBER : " + Build.VERSION.SDK_INT
                + " \nBOARD : " + Build.BOARD
                + " \nBOOTLOADER : " + Build.BOOTLOADER
                + " \nBRAND : " + Build.BRAND
                + " \nCPU_ABI : " + Build.CPU_ABI
                + " \nCPU_ABI2 : " + Build.CPU_ABI2
                + " \nDISPLAY : " + Build.DISPLAY
                + " \nFINGERPRINT : " + Build.FINGERPRINT
                + " \nHARDWARE : " + Build.HARDWARE
                + " \nHOST : " + Build.HOST
                + " \nID : " + Build.ID
                + " \nMANUFACTURER : " + Build.MANUFACTURER
                + " \nMODEL : " + Build.MODEL
                + " \nPRODUCT : " + Build.PRODUCT
                + " \nSERIAL : " + Build.SERIAL
                + " \nTAGS : " + Build.TAGS
                + " \nTIME : " + Build.TIME
                + " \nTYPE : " + Build.TYPE
                + " \nUNKNOWN : " + Build.UNKNOWN
                + " \nUSER : " + Build.USER;
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("appUser/guest");

        myRef.push().setValue(deviceinfo);
        String key = myRef.getKey();
        saveSharedPref(ctx,key,key);
    }
    public void saveSharedPref(android.content.Context ctx,String name, String key) {
        SharedPreferences prefs = ctx.getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", name);
        editor.putString("userId", key);
        editor.commit();
    }
    public   void bindMenu (final android.content.Context ctx) {
        AppConfiguration appConfiguration= new AppConfiguration();
        String url = "http://apps.arrowai.com/api/application.php";
        JSONObject map = new JSONObject();
        try {
            map.put("appId", appId);
            map.put("action", "detail");
        } catch (JSONException e) {
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.has("error")) {
                        return;
                    }
                    if (response.has("data")) {
                        JSONObject jsonObj = response.getJSONObject("data");
                        bots = jsonObj.getJSONArray("bots");
                        if (jsonObj.has("sideMenu")) {
                            String sMenu = jsonObj.getString("sideMenu");
                            if (sMenu != "null") {
                                sideMenu = jsonObj.getJSONArray("sideMenu");
                            }
                        }
                        saveBots(ctx,bots.toString(), sideMenu.toString());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    public void getSharedPref(android.content.Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("ChatPrefs", 0);
        bot = prefs.getString("bots", "");
        sideMenus = prefs.getString("sideMenu", "");
        appId=prefs.getString("appId", null);

    }

    public void saveBots(android.content.Context ctx,String bots, String sideMenu) {
        SharedPreferences prefs = ctx.getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("bots", bots);
        editor.putString("sideMenu", sideMenu);
        editor.commit();
    }

}
