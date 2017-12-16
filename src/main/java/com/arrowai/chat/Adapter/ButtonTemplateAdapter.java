package com.arrowai.chat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.arrowai.chat.Activity.ChatActivity;
import com.arrowai.chat.Activity.HomeActivity;
import com.arrowai.chat.Model.ButtonTemplate;
import com.arrowai.chat.Model.Confirmation;
import com.arrowai.chat.R;

import java.util.List;

/**
 * Created by Ravinder on 6/7/2016.
 */
public class ButtonTemplateAdapter extends BaseAdapter {
    private LayoutInflater layoutinflater;
    ViewHolder listViewHolder;
    private List<ButtonTemplate> listStorage;
    private Context context;
    private HomeActivity.AnimateFirstDisplayListener mAnimator;
    int[] imageview;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    public ButtonTemplateAdapter(Context context, List<ButtonTemplate> customizedListView) {
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
            convertView = layoutinflater.inflate(R.layout.button_layout, parent, false);
            listViewHolder.button = (TextView) convertView.findViewById(R.id.btnText);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }
        listViewHolder.button.setText(listStorage.get(position).getTitle());
        listViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listStorage.get(position).getPayload().optString("type").equals("postback")) {
                    ((ChatActivity) context).sendMessage(listStorage.get(position).getTitle(), true, listStorage.get(position).getPayload().optJSONObject("payload"),false,listStorage.get(position).getType(),listStorage.get(position).getUrl());

                }
                else if (listStorage.get(position).getPayload().optString("type").equals("openscreen")) {

                    SharedPreferences prefs = context.getSharedPreferences("ChatPrefs", 0);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("OpenScreen", "transaction");
                    editor.commit();
                    ((ChatActivity) context).onBackPressed();
                    //((ChatActivity) context).sendMessage(listStorage.get(position).getTitle(), true, listStorage.get(position).getPayload().optJSONObject("payload"),false,listStorage.get(position).getType(),listStorage.get(position).getUrl());

                }

                else {

                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(listStorage.get(position).getUrl()));
                    context.startActivity(myIntent);
                    Log.d("onclick", "url " + listStorage.get(position).getUrl());
                }

               // ((ChatActivity) context).sendMessage(listStorage.get(position).getTitle(), true, listStorage.get(position).getPayload().optJSONObject("payload"),false,listStorage.get(position).getType(),listStorage.get(position).getUrl());
            }
        });
        return convertView;
    }
    static class ViewHolder {
        TextView button;
    }
}
