package com.btpltech.bankingbot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com. btpltech.bankingbot.Activity.ChatActivity;
import  com.btpltech.bankingbot.Activity.HomeActivity;
import  com.btpltech.bankingbot.Model.confirmation;
import  com.btpltech.bankingbot.Model.confirmation;
import  com.btpltech.bankingbot.Model.confirmation_class;
import  com.btpltech.bankingbot.R;

import java.util.List;
/**
 * Created by Ravinder on 6/7/2016.
 */
public class ConfirmationAdapter extends BaseAdapter {
    private LayoutInflater layoutinflater;
    ViewHolder listViewHolder;
    private List<confirmation_class> listStorage;
    private Context context;
    private HomeActivity.AnimateFirstDisplayListener mAnimator;
    int[] imageview;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    public ConfirmationAdapter(Context context, List<confirmation_class> customizedListView) {
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
            convertView = layoutinflater.inflate(R.layout.change_widget, parent, false);
            listViewHolder.mTitle = (TextView) convertView.findViewById(R.id.type);
            listViewHolder.message = (TextView) convertView.findViewById(R.id.message);
            listViewHolder.change = (Button) convertView.findViewById(R.id.change);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }
        listViewHolder.mTitle.setText(listStorage.get(position).getTitle());
        listViewHolder.message.setText(listStorage.get(position).getMessage());
        listViewHolder.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof ChatActivity) {
                    ((ChatActivity) context).sendMessage("", true, listStorage.get(position).getPayload());
                }
            }
        });
        return convertView;
    }
    static class ViewHolder {
        TextView mTitle;
        TextView message;
        Button change;
    }
}
