package com.btpltech.bankingbot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com. btpltech.bankingbot.Activity.HomeActivity;
import com.btpltech.bankingbot.Model.Card;
import com.btpltech.bankingbot.Model.VolleySingleton;
import com.btpltech.bankingbot.R;


import java.util.List;

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
            listViewHolder.layout = (LinearLayout) convertView.findViewById(R.id.card);
            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }
        listViewHolder.mTitle.setText(listStorage.get(position).getCardName());
        try {
            mImageLoader = VolleySingleton.getInstance(context).getImageLoader();


            listViewHolder.image.setImageUrl(listStorage.get(position).getCardImage(), mImageLoader);
        } catch (Exception e) {
        }
       /* listViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof ChatActivity) {
                    ((ChatActivity) context).sendMessage(listStorage.get(position).getCardName(), false, null);
                }
            }
        });*/
        return convertView;
    }
    static class ViewHolder {
        TextView mTitle;
        NetworkImageView image;
        LinearLayout layout;
    }
}
