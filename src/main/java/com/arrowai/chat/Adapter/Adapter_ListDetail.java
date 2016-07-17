package com.arrowai.chat.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arrowai.chat.Activity.HomeActivity;
import com.arrowai.chat.Model.Circle;
import com.arrowai.chat.Model.Model_ListChatDetails;
import com.arrowai.chat.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suman kumar jha on 5/18/2016.
 */
public class Adapter_ListDetail extends RecyclerView.Adapter<Adapter_ListDetail.MyViewHolder>
{
    private LayoutInflater layoutinflater;
    RecyclerView.ViewHolder listViewHolder;
    private HomeActivity.AnimateFirstDisplayListener mAnimator;
    String image_url;
    private ImageLoader mImageLoader;
    List<Model_ListChatDetails> dataSet;
    Context context;
    public Adapter_ListDetail(Context context, List<Model_ListChatDetails> dataSet)
    {
        this.context=context;
        this.dataSet=dataSet;
        mImageLoader = ImageLoader.getInstance();
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
        mImageLoader.init(config);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textusername;
        TextView txtdescription;
        TextView txtcateogry;
        TextView textSuggestion;
        Circle botimage;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.textusername = (TextView) itemView.findViewById(R.id.txtbotname);
            this.textSuggestion = (TextView) itemView.findViewById(R.id.textSuggestion);
            this.txtdescription = (TextView) itemView.findViewById(R.id.textdescription);
            this.botimage = (Circle)itemView.findViewById(R.id.botimage);
        }

    }

    public Adapter_ListDetail(ArrayList<Model_ListChatDetails> data)
    {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_listchatdetails, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition)
    {
        TextView textViewName = holder.textusername;
        TextView textdescription = holder.txtdescription;
        TextView textSuggestion =holder.textSuggestion;
        final ImageView image = holder.botimage;
        textViewName.setText(dataSet.get(listPosition).getName());
        textdescription.setText(dataSet.get(listPosition).getDescription());
        textSuggestion.setText(Html.fromHtml(dataSet.get(listPosition).getCateogory()));
        if(dataSet.get(listPosition).getImage_url().length()<=0)
        {
            return;
        }
        mImageLoader.loadImage(dataSet.get(listPosition).getImage_url(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                image.setImageBitmap(loadedImage);
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return dataSet.size();
    }
}
