package com.btpltech.bankingbot.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.btpltech.bankingbot.Activity.Chat;
import com.btpltech.bankingbot.Adapter.Adapter_ListChat;
import com.btpltech.bankingbot.Model.Model_ListChat;
import com.btpltech.bankingbot.R;
import com.btpltech.bankingbot.SQliteData.Bots;
import com.btpltech.bankingbot.SQliteData.ChatListData;
import com.btpltech.bankingbot.SQliteData.SQLiteAdapter;
import com.btpltech.bankingbot.util.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListChat extends Fragment {
    ListView listView;
    ListView listView_image;
    Adapter_ListChat adapter;
    Context context;
    String _Id;
    ChatListData chatListData;
    private SQLiteAdapter sqLiteAdapter;
    Bots botstable;
    private ProgressBar mLoadingPb;
    int image[] = {R.drawable.rsz_bot_1, R.drawable.rsz_bot_2, R.drawable.rsz_bot_3, R.drawable.i4};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_list_chat, container, false);
        listView = (ListView) view.findViewById(R.id.chatlist);
        mLoadingPb = (ProgressBar) view.findViewById(R.id.pb_loading);
        mLoadingPb.setVisibility(View.GONE);
        sqLiteAdapter = new SQLiteAdapter(getActivity());
        chatListData = new ChatListData(sqLiteAdapter);
        botstable = new Bots(sqLiteAdapter);
        bind();

        return view;
    }

    void bind() {

        mLoadingPb.setVisibility(View.VISIBLE);

        String url = "http://bots.arrowai.com/api/index.php";
        JSONObject map = new JSONObject();
        try {
            map.put("action", "botStoreList");
        } catch (JSONException e) {

        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                List<Model_ListChat> model_listChats = new ArrayList<Model_ListChat>();
                try {
                    sqLiteAdapter.openToWrite();
                    //botstable.deleteChatListData();
                    JSONArray jsonarray = response.getJSONArray("data");
                    for (int i = 0; i < jsonarray.length(); i++) {
                        mLoadingPb.setVisibility(View.GONE);
                        JSONObject jsonObj = jsonarray.getJSONObject(i);
                        _Id = jsonObj.getString("_id");
                        String name = jsonObj.getString("name");
                        String image_url = jsonObj.getString("image_url");
                        String description = jsonObj.getString("description");
                        String cateogires = jsonObj.getString("category");
                        Model_ListChat ab = new Model_ListChat(_Id, name, description, cateogires, image, image_url,null,null,null);
                        model_listChats.add(ab);
                        adapter = new Adapter_ListChat(getContext(), model_listChats, image, image_url, true);
                        botstable.insertBot(_Id, name, description, image_url);
                        listView.setAdapter(adapter);
                        if (adapter != null)
                            adapter.notifyDataSetChanged();
                    }
                    sqLiteAdapter.close();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
//                if (error.networkResponse == null)
//                {
////                    error.printStackTrace();
//                    Toast.makeText(getActivity(), "Please check your internet connection!", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    error.printStackTrace();
//                    VolleyLog.d("Error", "Error: " + error.getMessage());
//                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity().getApplicationContext(),
//                            error.getMessage(), Toast.LENGTH_SHORT).show();
//                }
            }
        });
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError
//            {
//                Map<String, String> params = new HashMap<String, String>();
//                return params;
//            }
//        };
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }
}