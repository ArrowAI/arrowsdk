package com.btpltech.bankingbot.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.btpltech.bankingbot.Activity.HomeActivity;
import com.btpltech.bankingbot.Model.TransactionHistory;
import com. btpltech.bankingbot.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

/**
 * Created by Ravinder on 6/6/2016.
 */
public class TransactionAdapter extends BaseAdapter {
    private LayoutInflater layoutinflater;
    ViewHolder listViewHolder;
    private List<TransactionHistory> listStorage;
    private Context context;
    private HomeActivity.AnimateFirstDisplayListener mAnimator;
    int[] imageview;
    private ImageLoader mImageLoader;
    public TransactionAdapter(Context context, List<TransactionHistory> customizedListView) {
        this.context = context;
        this.imageview = imageview;
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
        DisplayImageOptions displayOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.imgplaceholder)
                .showImageForEmptyUri(R.drawable.imgplaceholder)
                .showImageOnFail(R.drawable.imgplaceholder)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .considerExifParams(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .writeDebugLogs()
                .defaultDisplayImageOptions(displayOptions)
                .build();
        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(config);
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
            convertView = layoutinflater.inflate(R.layout.transaction_hystory, parent, false);
            listViewHolder.mTitle = (TextView) convertView.findViewById(R.id.Title);
            listViewHolder.date = (TextView) convertView.findViewById(R.id.date);
            listViewHolder.type = (TextView) convertView.findViewById(R.id.type);
            listViewHolder.amount = (TextView) convertView.findViewById(R.id.amount);

            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }
        listViewHolder.mTitle.setText(listStorage.get(position).getPayementTo());
        listViewHolder.date.setText(listStorage.get(position).getDate());
        listViewHolder.type.setText(listStorage.get(position).getTransaction_type());
        listViewHolder.amount.setText("Rs : "+listStorage.get(position).getAmount());

        return convertView;
    }
    static class ViewHolder {
        TextView mTitle;
        TextView date;
        TextView type;
        TextView amount;
    }
}
