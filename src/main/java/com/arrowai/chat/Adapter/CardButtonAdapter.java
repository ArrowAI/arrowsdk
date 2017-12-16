package com.arrowai.chat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.arrowai.chat.Activity.ChatActivity;
import com.arrowai.chat.Model.CardButton;
import com.arrowai.chat.Model.CardButtonItem;
import com.arrowai.chat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 11-12-2017.
 */


public class CardButtonAdapter extends RecyclerView.Adapter<CardButtonAdapter.ViewHolder> {

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    //private int listItemLayout;
    private ArrayList<CardButton> itemList;
    private Context context;

    // Constructor of the class
    public CardButtonAdapter(Context context, ArrayList<CardButton> itemList) {
       // listItemLayout = layoutId;
        this.itemList = itemList;
        this.context = context;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.button_layout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
        System.out.println("title--"+itemList.get(listPosition).getTitle());
        item.setText(itemList.get(listPosition).getTitle());
        item.setTag(listPosition);
    }

    // Static inner class to initialize the views of rows
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView item;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            item = (TextView) itemView.findViewById(R.id.btnText);
        }

        @Override
        public void onClick(View view) {
            if (itemList.get((Integer) view.getTag()).getButtonPayload().optString("type").equals("postback")) {
                ((ChatActivity) context).sendMessage(itemList.get((Integer) view.getTag()).getButtonPayload().optString("title"), false, itemList.get((Integer) view.getTag()).getButtonPayload().optJSONObject("payload"), false, "", "");

            } else {

                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(itemList.get((Integer) view.getTag()).getButtonPayload().optString("url")));
                context.startActivity(myIntent);
                Log.d("onclick", "url " + itemList.get((Integer) view.getTag()).getButtonPayload().optString("url"));
            }

        }
    }


}







