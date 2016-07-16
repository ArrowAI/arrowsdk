package com.btpltech.bankingbot.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.btpltech.bankingbot.Model.AppConfiguration;
import com.btpltech.bankingbot.R;
import com.btpltech.bankingbot.util.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    private boolean mVisible;
    String bot;
    String sideMenus;
    String appId;

    JSONArray bots, sideMenu = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSharedPref();
        if (!bot.equals("")) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        } else {
            bindMenu();
        }


    }

    public void saveSharedPref(String name, String email, String mobile) {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", name);
        editor.putString("email", email);
        editor.putString("mobile", mobile);
        editor.commit();
    }

  public   void bindMenu() {
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
                        saveSharedPref(bots.toString(), sideMenu.toString());

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

    public void getSharedPref() {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        bot = prefs.getString("bots", "");
        sideMenus = prefs.getString("sideMenu", "");
        appId=prefs.getString("appId", null);

    }

    public void saveSharedPref(String bots, String sideMenu) {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("bots", bots);
        editor.putString("sideMenu", sideMenu);
        editor.commit();
    }

}
