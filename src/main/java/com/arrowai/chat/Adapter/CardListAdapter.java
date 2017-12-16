package com.arrowai.chat.Adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.arrowai.chat.Activity.ChatActivity;
import com.arrowai.chat.Activity.HomeActivity;
import com.arrowai.chat.Model.Card;
import com.arrowai.chat.Model.CardButton;
import com.arrowai.chat.Model.CardButtonItem;
import com.arrowai.chat.Model.VolleySingleton;
import com.arrowai.chat.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravinder on 6/3/2016.
 */
/*
public class CardListAdapter extends BaseAdapter {
    private LayoutInflater layoutinflater;
    ViewHolder listViewHolder;
    private List<Card> listStorage;
    private Context context;

    private HomeActivity.AnimateFirstDisplayListener mAnimator;
    int[] imageview;
    private String buttonText="";
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    CardButtonAdapter cardBtnAdapter;
    CardButtonItem cardBtnItem;

    public CardListAdapter(Context context, List<Card> customizedListView) {
        this.context = context;
        this.imageview = imageview;
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;

        mAnimator = new HomeActivity.AnimateFirstDisplayListener();
        //itemList = new ArrayList<CardButtonItem>();
         //cardBtnAdapter = new CardButtonAdapter(context,R.layout.button_layout, itemList);
    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.card_item_list, parent, false);
            listViewHolder.mTitle = (TextView) convertView.findViewById(R.id.Title);
            listViewHolder.mDescription = (TextView) convertView.findViewById(R.id.Desc);
            //listViewHolder.button = (Button) convertView.findViewById(R.id.button);
            listViewHolder.image = (NetworkImageView) convertView.findViewById(R.id.image);
            listViewHolder.layout = (RelativeLayout) convertView.findViewById(R.id.card);
            listViewHolder.mButtonRecyclerView = (RecyclerView) convertView.findViewById(R.id.buttonRecyclerView);
            listViewHolder.mButtonRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            listViewHolder.mButtonRecyclerView.setItemAnimator(new DefaultItemAnimator());
            listViewHolder.mButtonRecyclerView.setNestedScrollingEnabled(false);

            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }


        listViewHolder.mTitle.setText(listStorage.get(position).getCardName());
        listViewHolder.mDescription.setText(listStorage.get(position).getCardDescription());
        ArrayList<CardButtonItem> btnItemList =new ArrayList<>() ;
        JSONArray buttonArr = listStorage.get(position).getButtons();
        for (int m = 0; m <= buttonArr.length(); m++) {
            JSONObject item = buttonArr.optJSONObject(m);  //gets the ith Json object of JSONArray
            String buttonTitle = item.optString("title");
            CardButtonItem cardBtnItem = new CardButtonItem(buttonTitle, "", item);
            btnItemList.add(cardBtnItem);
        }
        cardBtnAdapter = new CardButtonAdapter(context,R.layout.button_layout, btnItemList);
        listViewHolder.mButtonRecyclerView.setAdapter(cardBtnAdapter);
        cardBtnAdapter.notifyDataSetChanged();




        try {
            mImageLoader = VolleySingleton.getInstance(context).getImageLoader();


            listViewHolder.image.setImageUrl(listStorage.get(position).getCardImageUrl(), mImageLoader);
        } catch (Exception e) {
        }
       */
/* listViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof ChatActivity) {

                        ((ChatActivity) context).sendMessage(buttonText, false, null, false, "", "");

                    // ((ChatActivity) context).sendMessage(listStorage.get(position).getCardName(), false, null);
                }
            }
        });*//*

        return convertView;
    }
    static class ViewHolder {
        TextView mTitle,mDescription;
        NetworkImageView image;
       // Button button;
        RelativeLayout layout;
        RecyclerView mButtonRecyclerView;
    }
}
*/
//public class CardListAdapter extends BaseAdapter {
//    private LayoutInflater layoutinflater;
//    ViewHolder listViewHolder;
//    private List<Card> listStorage;
//    private Context context;
//    private HomeActivity.AnimateFirstDisplayListener mAnimator;
//    int[] imageview;
//    CardButtonAdapter cardButtonAdapter;
//    CardButton cardButton;
//    ArrayList<CardButton> arrayCardButton;
//    private RequestQueue mRequestQueue;
//    private ImageLoader mImageLoader;
//
//    public CardListAdapter(Context context, List<Card> customizedListView) {
//        this.context = context;
//        this.imageview = imageview;
//        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        listStorage = customizedListView;
//        mAnimator = new HomeActivity.AnimateFirstDisplayListener();
//    }
//
//    @Override
//    public int getCount() {
//        return listStorage.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return position;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            listViewHolder = new ViewHolder();
//            convertView = layoutinflater.inflate(R.layout.card_item_list, parent, false);
//            listViewHolder.mTitle = (TextView) convertView.findViewById(R.id.Title);
//            listViewHolder.image = (NetworkImageView) convertView.findViewById(R.id.image);
//            listViewHolder.layout = (RelativeLayout) convertView.findViewById(R.id.card);
//            listViewHolder.desc = (TextView) convertView.findViewById(R.id.Desc);
//            listViewHolder.cardButtons = (GridView) convertView.findViewById(R.id.gridButton);
//
//            listViewHolder = bindButtons(listViewHolder, listStorage.get(position).getButtons());
//            convertView.setTag(listViewHolder);
//            listViewHolder.mTitle.setText(listStorage.get(position).getCardName());
//            listViewHolder.desc.setText(listStorage.get(position).getCardDescription());
//            try {
//                mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
//
//
//                listViewHolder.image.setImageUrl(listStorage.get(position).getCardImage(), mImageLoader);
//
//            } catch (Exception e) {
//                Log.d("Error",e.toString());
//
//            }
//        } else {
//            listViewHolder = (ViewHolder) convertView.getTag();
//        }
//
//       /* listViewHolder.layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (context instanceof ChatActivity) {
//                    ((ChatActivity) context).sendMessage(listStorage.get(position).getCardName(), false, null);
//                }
//            }
//        });*/
//
//        return convertView;
//    }
//    static class ViewHolder {
//        TextView mTitle;
//        TextView desc;
//        NetworkImageView image;
//        RelativeLayout layout;
//        GridView cardButtons;
//    }
//
//
//    private CardListAdapter.ViewHolder bindButtons(CardListAdapter.ViewHolder viewHolder, JSONArray buttonsJson) {
//        JSONArray jsonArray = new JSONArray();
//        arrayCardButton= new ArrayList<>();
//        String title="";
//        String type="";
//        JSONObject variableType= new JSONObject();
//        JSONObject buttonPayload= new JSONObject();
//        try {
//            jsonArray = buttonsJson;
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                if(jsonObject.has("title")) {
//                    title = jsonObject.getString("title");
//                }
//                if(jsonObject.has("type")) {
//                    type = jsonObject.getString("type");
//                }
//                if(jsonObject.has("variableType")) {
//                    variableType = jsonObject.getJSONObject("variableType");
//                }
//                if(jsonObject.has("payload")) {
//                    buttonPayload = jsonObject.getJSONObject("payload");
//                }
//                cardButton = new CardButton( variableType,type, buttonPayload,title);
//                arrayCardButton.add(cardButton);
//            }
//
//            cardButtonAdapter = new CardButtonAdapter(context, arrayCardButton);
//            listViewHolder.cardButtons.setAdapter(cardButtonAdapter);
//            cardButtonAdapter.notifyDataSetChanged();
//        } catch (Exception e) {
//            Log.d("Error",e.toString());
//        }
//        return viewHolder;
//    }
//}
/**
 * Created by Ravinder on 6/3/2016.
 */
public class CardListAdapter extends BaseAdapter {
    private LayoutInflater layoutinflater;
    ViewHolder listViewHolder;
    private List<Card> listStorage;
    private Context context;
    private HomeActivity.AnimateFirstDisplayListener mAnimator;
    int[] imageview;
    CardButtonAdapter cardButtonAdapter;//o
    CardButton cardButton;
    ArrayList<CardButton> arrayCardButton;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public CardListAdapter(Context context, List<Card> customizedListView) {
        this.context = context;
        this.imageview = imageview;
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
        mAnimator = new HomeActivity.AnimateFirstDisplayListener();
    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.card_item_list, parent, false);
            listViewHolder.mTitle = (TextView) convertView.findViewById(R.id.Title);
            listViewHolder.image = (NetworkImageView) convertView.findViewById(R.id.image);
            listViewHolder.layout = (RelativeLayout) convertView.findViewById(R.id.card);
            listViewHolder.desc = (TextView) convertView.findViewById(R.id.Desc);
            listViewHolder.cardButtons = (RecyclerView) convertView.findViewById(R.id.gridView);
            listViewHolder.cardButtons.setLayoutManager(new LinearLayoutManager(context));
            listViewHolder.cardButtons.setItemAnimator(new DefaultItemAnimator());
            listViewHolder.cardButtons.setNestedScrollingEnabled(false);


            convertView.setTag(listViewHolder);

        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }

        listViewHolder = bindButtons(listViewHolder, listStorage.get(position).getButtons());
        listViewHolder.mTitle.setText(listStorage.get(position).getCardName());
        listViewHolder.desc.setText(listStorage.get(position).getCardDescription());
        try {
            mImageLoader = VolleySingleton.getInstance(context).getImageLoader();


            listViewHolder.image.setImageUrl(listStorage.get(position).getCardImageUrl(), mImageLoader);

        } catch (Exception e) {
            Log.d("Error",e.toString());

        }


        return convertView;
    }
    static class ViewHolder {
        TextView mTitle;
        TextView desc;
        NetworkImageView image;
        RelativeLayout layout;
        RecyclerView cardButtons;
    }


    private CardListAdapter.ViewHolder bindButtons(CardListAdapter.ViewHolder viewHolder, JSONArray buttonsJson) {
        JSONArray jsonArray = new JSONArray();
        arrayCardButton= new ArrayList<>();
        String title="";
        String type="";
        JSONObject variableType= new JSONObject();
        JSONObject buttonPayload= new JSONObject();
        try {
            jsonArray = buttonsJson;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                if(jsonObject.has("title")) {
                    title = jsonObject.getString("title");
                }
                if(jsonObject.has("type")) {
                    type = jsonObject.getString("type");
                }
                if(jsonObject.has("variableType")) {
                    variableType = jsonObject.getJSONObject("variableType");
                }
                if(jsonObject.has("payload")) {
                    buttonPayload = jsonObject.getJSONObject("payload");
                }
                cardButton = new CardButton( variableType,type, jsonObject,title);
                arrayCardButton.add(cardButton);
            }

            cardButtonAdapter = new CardButtonAdapter(context, arrayCardButton);
            listViewHolder.cardButtons.setAdapter(cardButtonAdapter);
            cardButtonAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.d("Error",e.toString());
        }
        return viewHolder;
    }
}

