package com.arrowai.chat.Model;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.arrowai.chat.Activity.Chat;
import com.arrowai.chat.Activity.ChatActivity;
import com.arrowai.chat.Activity.LoginActivity;
import com.arrowai.chat.Activity.SplashActivity;
import com.arrowai.chat.Activity.User;
import com.arrowai.chat.util.AppController;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

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
    Query myRefQuery;

    public void setAppId(android.content.Context ctx, String appId) {
        SharedPreferences prefs = ctx.getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("appId", appId);
        editor.commit();

    }
    public void logIn(final String userName, final String email,final String mobile,String id ,JSONObject jsonObject, final android.content.Context ctx) {
        String url = "http://54.88.238.120:8081/auth/login";
        JSONObject map = new JSONObject();
        try {
            map.put("appId",appId );
            map.put("uniqueId", email);
            map.put("otherParameters", jsonObject);
            //appid,unique id,otherparameters,deviceinfo
        } catch (JSONException e) {
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String loginStatus = "";
                try {
                    if (response.has("status")) {
                        loginStatus = response.getString("status").toString();
                        if (loginStatus.equals("success")) {
                            JSONObject jsonObj = response.getJSONObject("data");
                            String name = jsonObj.getString("name");
                            String email = jsonObj.getString("email");
                            String mobile = jsonObj.getString("phone");
                            String key = jsonObj.getString("key");
                            String id = jsonObj.getString("_id");

                        } else {

                        }
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

    public void register(final String userName, final String email,final String mobile,String id ,JSONObject jsonObject, final android.content.Context ctx) {

    }

    public void logOut(android.content.Context ctx) {
        saveSharedPref(ctx,"","");
        getUserId(ctx);

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
        saveSharedPref(ctx, key, key);
    }

    private void saveSharedPref(android.content.Context ctx, String name, String key) {
        SharedPreferences prefs = ctx.getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", name);
        editor.putString("userId", key);
        editor.commit();
    }




}
