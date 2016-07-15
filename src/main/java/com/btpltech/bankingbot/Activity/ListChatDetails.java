package com.btpltech.bankingbot.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.btpltech.bankingbot.Adapter.AdapterChatList;
import com.btpltech.bankingbot.Adapter.Adapter_ListDetail;
import com.btpltech.bankingbot.Model.AboutModel;
import com.btpltech.bankingbot.Model.Model_ListChatDetails;
import com.btpltech.bankingbot.R;
import com.btpltech.bankingbot.util.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListChatDetails extends AppCompatActivity {
    //    ListView recyclerView;
    RecyclerView recyclerView;
    private static ArrayList<AboutModel> data;
    Toolbar toolbar;
    Adapter_ListDetail adapter;
    //    Adapter_ListChat1 adapter;
    RecyclerView.LayoutManager layoutManager;
    AppBarLayout app_bar_layout;
    CoordinatorLayout rootLayout;
    int image;
    CollapsingToolbarLayout collapsingToolbarLayout;
    String id;
    String botName;
    List<Model_ListChatDetails> model_listChats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chat_details);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Intent i = getIntent();


        id = getIntent().getExtras().getString("id");
        image = getIntent().getExtras().getInt("imgurl");
        botName = getIntent().getExtras().getString("title");
        ImageView imageView = (ImageView) findViewById(R.id.toolbar_image);
        //imageView.setImageResource(image);

        bind();

        app_bar_layout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(Color.WHITE);


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        Button btnChatToBot = (Button) findViewById(R.id.btnChatToBot);
        btnChatToBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra("type", "Chat to boat");
                intent.putExtra("botid", id);
                intent.putExtra("botName", botName);
                startActivity(intent);
            }

        });
        model_listChats = new ArrayList<Model_ListChatDetails>();
    }

    String _Id;
    String name;
    String image_url;
    String description;
    String cateogires;


    void bind() {
        String url = "http://bots.arrowai.com/api/index.php";
        JSONObject map = new JSONObject();
        try {
            map.put("action", "botinfo");
            map.put("bot", id);
        } catch (JSONException e) {

        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    {
                        _Id = jsonObject.getString("_id");
                        name = jsonObject.getString("name");
                        image_url = jsonObject.getString("image_url");
                        description = jsonObject.getString("description");
                        cateogires = jsonObject.getString("category");
                        toolbar.setTitle(name);
                    }
                    getBotInitials();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  error.printStackTrace();
                if (error.networkResponse == null) {
//                    error.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Please check your internet connection!", Toast.LENGTH_SHORT).show();
                } else {
                    error.printStackTrace();
                    VolleyLog.d("Error", "Error: " + error.getMessage());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext().getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_chat_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getBotInitials() {

        String url = "http://bots.arrowai.com/api/index.php";
        JSONObject payload = new JSONObject();
        JSONObject map = new JSONObject();

        try {
            map.put("bot", id);
            map.put("action", "getbotsuggestionandgreeting");
        } catch (Exception e) {

        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONArray suggestionArray = null;

                    if (data.has("suggestions")) {
                        suggestionArray = data.getJSONArray("suggestions");
                    }
                    Model_ListChatDetails ab = new Model_ListChatDetails(_Id, name, image_url, description, getStringFromArray(suggestionArray));
                    model_listChats.add(ab);
                    adapter = new Adapter_ListDetail(getApplicationContext(), model_listChats);
                    recyclerView.setAdapter(adapter);
                    if (adapter != null)
                        adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String json = null;
                NetworkResponse response = error.networkResponse;
                if (response != null && response.data != null) {
                    json = new String(response.data);
                    String s = json;
                }
            }
        });
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(300000000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private String getTime() {
        Long tsLong = System.currentTimeMillis() / 1000;
        String ts = tsLong.toString();
        return ts;
    }

    private String getStringFromArray(JSONArray array) {
        String s = "";
        try {
            for (int i = 0; i < array.length(); i++) {
                s += "&#8226; "+ String.valueOf(array.get(i) + "<br/>");
            }
        } catch (JSONException e) {
            e.getStackTrace();
        }
        return s;
    }
}
