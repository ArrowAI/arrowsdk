package com.arrowai.chat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.arrowai.chat.Activity.ChatActivity;
import com.arrowai.chat.Activity.HomeActivity;
import com.arrowai.chat.Model.TopMenu;
import com.arrowai.chat.Model.VolleySingleton;
import com.arrowai.chat.R;

import java.util.List;

/**
 * Created by Ravinder on 6/10/2016.
 */
public class TopMenuAdapter extends BaseAdapter {
    private LayoutInflater layoutinflater;
    ViewHolder listViewHolder;
    private List<TopMenu> listStorage;
    private Context context;

    int[] imageview;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public TopMenuAdapter(Context context, List<TopMenu> customizedListView) {
        this.context = context;
        this.imageview = imageview;
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
    }

    @Override
    public int getCount() {
        try {
            return listStorage.size();
        }
        catch (Exception e)
        {
            return listStorage.size();
        }
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
            convertView = layoutinflater.inflate(R.layout.keyboard, parent, false);
//            listViewHolder.mTitle = (TextView) convertView.findViewById(R.id.Title);
//            listViewHolder.image = (NetworkImageView) convertView.findViewById(R.id.image);
            listViewHolder.button = (TextView) convertView.findViewById(R.id.button);
            listViewHolder.layout = (RelativeLayout) convertView.findViewById(R.id.card);

            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }

       listViewHolder.button.setText(listStorage.get(position).getName());
//        try {
//            mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
//
//
//            listViewHolder.image.setImageUrl(listStorage.get(position).getImage(), mImageLoader);
//        } catch (Exception e) {
//        }
        listViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof ChatActivity) {
                    ((ChatActivity) context).setBotId(listStorage.get(position).getAction(),listStorage.get(position).getName(),listStorage.get(position).getBotId(),listStorage.get(position).getPayload());
                }
            }
        });
        return convertView;
    }
    static class ViewHolder {
        TextView mTitle;
        NetworkImageView image;
        RelativeLayout layout;
        TextView button;
    }
}
