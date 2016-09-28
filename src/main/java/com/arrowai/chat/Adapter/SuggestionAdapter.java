package com.arrowai.chat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.arrowai.chat.Activity.ChatActivity;
import com.arrowai.chat.Model.suggestions;
import com.arrowai.chat.R;

import java.util.List;

/**
 * Created by Ravinder on 9/27/2016.
 */

public class SuggestionAdapter extends BaseAdapter {
    private LayoutInflater layoutinflater;
    SuggestionAdapter.ViewHolder listViewHolder;
    private List<suggestions> listStorage;
    private Context context;


    public SuggestionAdapter(Context context, List<suggestions> customizedListView) {
        this.context = context;

        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;

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
            listViewHolder = new SuggestionAdapter.ViewHolder();
            convertView = layoutinflater.inflate(R.layout.suggesionlayout, parent, false);
            listViewHolder.button = (TextView) convertView.findViewById(R.id.button);
            listViewHolder.layout = (RelativeLayout) convertView.findViewById(R.id.card);

            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (SuggestionAdapter.ViewHolder) convertView.getTag();
        }
        listViewHolder.button.setText(listStorage.get(position).getText());

        listViewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof ChatActivity) {
                    ((ChatActivity) context).sendMessage(listStorage.get(position).getText(), false, null);
                }
            }
        });
        return convertView;
    }
    static class ViewHolder {
        TextView button;
        RelativeLayout layout;

    }
}
