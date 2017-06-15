package com.arrowai.chat;

import org.json.JSONObject;

/**
 * Created by Ravinder on 6/15/2017.
 */

public class Helper {
    //helper class
    public interface AppInterface {
        public void myFunction(JSONObject properties);
    }
   public static AppInterface myapp = null;

  public   static void registerApp(AppInterface appinterface) {
        myapp = appinterface;
    }
}
