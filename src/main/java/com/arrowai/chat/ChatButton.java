package com.arrowai.chat;

/**
 * Created by rajmendra on 14/07/16.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.arrowai.chat.Activity.ChatActivity;
import com.arrowai.chat.Activity.LoginActivity;
import com.arrowai.chat.Activity.SplashActivity;
import com.arrowai.chat.Model.AppConfiguration;
import com.arrowai.chat.util.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ChatButton extends FloatingActionButton implements View.OnTouchListener {

    //Custom values
    private boolean isShadowEnabled = true;
    private int mButtonColor;
    private int mShadowColor;
    private int mShadowHeight;
    private int mCornerRadius;
    //Native values
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mPaddingBottom;
    //Background drawable
    private Drawable pressedDrawable;
    private Drawable unpressedDrawable;
    String bot;
    String sideMenus;
    String appId;
    String userName;
    JSONArray bots, sideMenu = new JSONArray();

    boolean isShadowColorDefined = false;

    public ChatButton(Context context) {
        super(context);
        init();
        this.setOnTouchListener(this);
    }

    public ChatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        parseAttrs(context, attrs);
        this.setOnTouchListener(this);
    }

    public ChatButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        parseAttrs(context, attrs);
        this.setOnTouchListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //Update background color
        refresh();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        AppConfiguration appConfiguration = new AppConfiguration();
        getSharedPref(getContext());
        if (bot != null) {
        } else {
            bindMenu(getContext());
        }
        if (userName == null) {
            appConfiguration.getUserId(getContext());
        }
        Intent myIntent = new Intent(getContext(), ChatActivity.class);
        getContext().startActivity(myIntent);
        return false;
    }

    public void bindMenu(final Context ctx) {
        AppConfiguration appConfiguration = new AppConfiguration();
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
                        saveBots(ctx, bots.toString(), sideMenu.toString());
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

    public void getSharedPref(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("ChatPrefs", 0);
        bot = prefs.getString("bots", null);
        sideMenus = prefs.getString("sideMenu", null);
        appId = prefs.getString("appId", null);
        userName = prefs.getString("username", null);
    }

    public void saveBots(Context ctx, String bots, String sideMenu) {
        SharedPreferences prefs = ctx.getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("bots", bots);
        editor.putString("sideMenu", sideMenu);
        editor.commit();
    }

    private void init() {
        this.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        this.setImageResource(R.drawable.chat);

        //Init default values
        isShadowEnabled = true;
        Resources resources = getResources();
        if (resources == null) return;
    }

    private void parseAttrs(Context context, AttributeSet attrs) {

    }

    public void refresh() {
        int alpha = Color.alpha(mButtonColor);
        float[] hsv = new float[3];
        Color.colorToHSV(mButtonColor, hsv);
        hsv[2] *= 0.8f; // value component
        //if shadow color was not defined, generate shadow color = 80% brightness
        if (!isShadowColorDefined) {
            mShadowColor = Color.HSVToColor(alpha, hsv);
        }
        //Create pressed background and unpressed background drawables

        if (this.isEnabled()) {
            if (isShadowEnabled) {
                pressedDrawable = createDrawable(mCornerRadius, Color.TRANSPARENT, mButtonColor);
                unpressedDrawable = createDrawable(mCornerRadius, mButtonColor, mShadowColor);
            } else {
                mShadowHeight = 0;
                pressedDrawable = createDrawable(mCornerRadius, mShadowColor, Color.TRANSPARENT);
                unpressedDrawable = createDrawable(mCornerRadius, mButtonColor, Color.TRANSPARENT);
            }
        } else {
            Color.colorToHSV(mButtonColor, hsv);
            hsv[1] *= 0.25f; // saturation component
            int disabledColor = mShadowColor = Color.HSVToColor(alpha, hsv);
            // Disabled button does not have shadow
            pressedDrawable = createDrawable(mCornerRadius, disabledColor, Color.TRANSPARENT);
            unpressedDrawable = createDrawable(mCornerRadius, disabledColor, Color.TRANSPARENT);
        }
        updateBackground(unpressedDrawable);
        //Set padding
        this.setPadding(mPaddingLeft, mPaddingTop + mShadowHeight, mPaddingRight, mPaddingBottom + mShadowHeight);
    }

    private void updateBackground(Drawable background) {
        if (background == null) return;
        //Set button background
        if (Build.VERSION.SDK_INT >= 16) {
            this.setBackground(background);
        } else {
            this.setBackgroundDrawable(background);
        }
    }

    private LayerDrawable createDrawable(int radius, int topColor, int bottomColor) {

        float[] outerRadius = new float[]{radius, radius, radius, radius, radius, radius, radius, radius};

        //Top
        RoundRectShape topRoundRect = new RoundRectShape(outerRadius, null, null);
        ShapeDrawable topShapeDrawable = new ShapeDrawable(topRoundRect);
        topShapeDrawable.getPaint().setColor(topColor);
        //Bottom
        RoundRectShape roundRectShape = new RoundRectShape(outerRadius, null, null);
        ShapeDrawable bottomShapeDrawable = new ShapeDrawable(roundRectShape);
        bottomShapeDrawable.getPaint().setColor(bottomColor);
        //Create array
        Drawable[] drawArray = {bottomShapeDrawable, topShapeDrawable};
        LayerDrawable layerDrawable = new LayerDrawable(drawArray);

        //Set shadow height
        if (isShadowEnabled && topColor != Color.TRANSPARENT) {
            //unpressed drawable
            layerDrawable.setLayerInset(0, 0, 0, 0, 0);  /*index, left, top, right, bottom*/
        } else {
            //pressed drawable
            layerDrawable.setLayerInset(0, 0, mShadowHeight, 0, 0);  /*index, left, top, right, bottom*/
        }
        layerDrawable.setLayerInset(1, 0, 0, 0, mShadowHeight);  /*index, left, top, right, bottom*/

        return layerDrawable;
    }

    //Setter
    public void setShadowEnabled(boolean isShadowEnabled) {
        this.isShadowEnabled = isShadowEnabled;
        setShadowHeight(0);
        refresh();
    }

    public void setButtonColor(int buttonColor) {
        this.mButtonColor = buttonColor;
        refresh();
    }

    public void setShadowColor(int shadowColor) {
        this.mShadowColor = shadowColor;
        isShadowColorDefined = true;
        refresh();
    }

    public void setShadowHeight(int shadowHeight) {
        this.mShadowHeight = shadowHeight;
        refresh();
    }

    public void setCornerRadius(int cornerRadius) {
        this.mCornerRadius = cornerRadius;
        refresh();
    }

    public void setFButtonPadding(int left, int top, int right, int bottom) {
        mPaddingLeft = left;
        mPaddingRight = right;
        mPaddingTop = top;
        mPaddingBottom = bottom;
        refresh();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        refresh();
    }

    //Getter
    public boolean isShadowEnabled() {
        return isShadowEnabled;
    }

    public int getButtonColor() {
        return mButtonColor;
    }

    public int getShadowColor() {
        return mShadowColor;
    }

    public int getShadowHeight() {
        return mShadowHeight;
    }

    public int getCornerRadius() {
        return mCornerRadius;
    }


}


