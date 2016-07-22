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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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
public class ArrowAi {
    public ArrowAi() {
    }

    String bot;
    String sideMenus;
    String appId;
    JSONArray bots, sideMenu = new JSONArray();
    FirebaseDatabase database;
    DatabaseReference myRef;
    Query myRefQuery;
    String userId = "";

    public void setAppId(android.content.Context ctx, String appId) {
        SharedPreferences prefs = ctx.getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("appId", appId);
        editor.commit();

    }

    public void guestLogin(String uniqueId, JSONObject jsonObject, final android.content.Context ctx) {
        setupAppId(ctx);
        String url = "http://54.88.238.120:8081/users/add";
        JSONObject deviceInfo = new JSONObject();
        deviceInfo = getUserId(ctx);
        JSONObject map = new JSONObject();
        try {
            map.put("applicationId", appId);
            map.put("data", jsonObject);
            map.put("uniqId", uniqueId);
            map.put("deviceInfo", deviceInfo);

        } catch (JSONException e) {
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int loginStatus = 0;
                try {
                    if (response.has("code")) {
                        loginStatus = response.getInt("code");
                        if (loginStatus == 0) {
                            userId = response.getString("userId");
                            saveSharedPref(ctx, "geustUser", userId);
                            Intent myIntent = new Intent(ctx, ChatActivity.class);
                            ctx.startActivity(myIntent);


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
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue queue = Volley.newRequestQueue(ctx);
        queue.add(jsonObjReq);

    }

    public void logIn(String uniqueId, JSONObject jsonObject, final android.content.Context ctx) {
        setupAppId(ctx);
        String url = "http://54.88.238.120:8081/users/add";
        JSONObject deviceInfo = new JSONObject();
        deviceInfo = getUserId(ctx);
        JSONObject map = new JSONObject();
        try {
            map.put("applicationId", appId);
            map.put("uniqId", uniqueId);
            map.put("data", jsonObject);
            map.put("deviceInfo", deviceInfo);

        } catch (JSONException e) {
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int loginStatus = 0;
                try {
                    if (response.has("code")) {
                        loginStatus = response.getInt("code");
                        if (loginStatus == 0) {
                            userId = response.getString("userId");
                            saveSharedPref(ctx, userId, userId);


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
        RequestQueue queue = Volley.newRequestQueue(ctx);
        queue.add(jsonObjReq);
    }

    public void logOut(final android.content.Context ctx,String applicationId) {
        saveSharedPref(ctx, "", "");
        String url = "http://54.88.238.120:8081/users/add";
        JSONObject deviceInfo = new JSONObject();
        deviceInfo = getUserId(ctx);
        JSONObject map = new JSONObject();
        try {
            map.put("applicationId", applicationId);
            map.put("uniqId", null);
            map.put("data", null);
            map.put("deviceInfo", deviceInfo);

        } catch (JSONException e) {
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int loginStatus = 0;
                try {
                    if (response.has("code")) {
                        loginStatus = response.getInt("code");
                        if (loginStatus == 0) {
                            userId = response.getString("userId");
                            saveSharedPref(ctx, userId, userId);


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
        RequestQueue queue = Volley.newRequestQueue(ctx);
        queue.add(jsonObjReq);


    }

    public JSONObject getUserId(android.content.Context ctx) {
        String android_id = Settings.Secure.getString(ctx.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(android.content.Context.TELEPHONY_SERVICE);
        WifiManager wm = (WifiManager) ctx.getSystemService(android.content.Context.WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        JSONObject deviceData = new JSONObject();
        try {
            deviceData.put("IP", ip);
            deviceData.put("ANDROID_ID", android_id);
//            deviceData.put("VERSION.RELEASE ", Build.VERSION.RELEASE);
//            deviceData.put("VERSION.INCREMENTAL", Build.VERSION.INCREMENTAL);
            //  deviceData.put("VERSION.SDK.NUMBER", Build.VERSION.SDK_INT).toString();
            deviceData.put("BOARD", Build.BOARD);
            deviceData.put("BOOTLOADER", Build.BOOTLOADER);
            deviceData.put("BRAND", Build.BRAND);
            deviceData.put("CPU_ABI", Build.CPU_ABI);
            deviceData.put("CPU_ABI2", Build.CPU_ABI2);
            deviceData.put("DISPLAY", Build.DISPLAY);
            deviceData.put("FINGERPRINT", Build.FINGERPRINT);
            deviceData.put("HARDWARE", Build.HARDWARE);
            deviceData.put("HOST", Build.HOST);
            deviceData.put("ID", Build.ID);
            deviceData.put("MANUFACTURER", Build.MANUFACTURER);
            deviceData.put("MODEL", Build.MODEL);
            deviceData.put("PRODUCT", Build.PRODUCT);
            deviceData.put("SERIAL", Build.SERIAL);
            deviceData.put("TAGS", Build.TAGS);
            deviceData.put("TIME", Build.TIME).toString();
            deviceData.put("TYPE", Build.TYPE);
            deviceData.put("UNKNOWN", Build.UNKNOWN);
            deviceData.put("USER", Build.USER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return deviceData;
    }

    private void saveSharedPref(android.content.Context ctx, String name, String key) {
        SharedPreferences prefs = ctx.getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", name);
        editor.putString("userId", key);
        editor.commit();
    }

    private void setupAppId(android.content.Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("ChatPrefs", 0);
        appId = prefs.getString("appId", null);
    }
    public void showLeftMenu(Boolean show,android.content.Context ctx)
    {
        SharedPreferences prefs = ctx.getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("showMenu",show);
        editor.commit();
    }

}
