package com.arrowai.chat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.arrowai.chat.Activity.HomeActivity;
import com.arrowai.chat.Activity.ListChatDetails;
import com.arrowai.chat.Model.Model_ListChat;
import com.arrowai.chat.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

/**
 * Created by Suman kumar jha on 5/18/2016.
 */
public class Adapter_ListChat extends BaseAdapter
{
    private LayoutInflater layoutinflater;
    ViewHolder listViewHolder;
    private List<Model_ListChat> listStorage;
    private Context context;
    private HomeActivity.AnimateFirstDisplayListener mAnimator;
    int[] imageview;
    private ImageLoader mImageLoader;
    String imgUrl;
    boolean click;
    public Adapter_ListChat(Context context, List<Model_ListChat> customizedListView,int[] imageview, String imgUrl, boolean click)
    {
        this.context = context;
        this.imageview=imageview;
        this.imgUrl=imgUrl;
    this.click=click;
        layoutinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public int getCount()
    {
        return listStorage.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.listchat_titles, parent, false);
            listViewHolder.mTitle = (TextView) convertView.findViewById(R.id.Title);
            listViewHolder.mDesc = (TextView) convertView.findViewById(R.id.Desc);
            listViewHolder.image=(ImageView)convertView.findViewById(R.id.image);
            convertView.setTag(listViewHolder);
        }
        else
        {
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.mTitle.setText(listStorage.get(position).getName());
        String desc = listStorage.get(position).getDescription();
        if(desc.length()>30){
            listViewHolder.mDesc.setText(desc.substring(0,30)+"...");
        }
        else
        {
            listViewHolder.mDesc.setText(desc);
        }
        if(listStorage.get(position).getImage().length()>0){
            mImageLoader.loadImage(listStorage.get(position).getImage(), new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    listViewHolder.image.setImageBitmap(loadedImage);
                }
            });


        }else {
            if (position < imageview.length) {
                listViewHolder.image.setImageResource(imageview[position]);
            } else {
                listViewHolder.image.setImageResource(imageview[0]);
            }
        }
        if(click) {
            convertView.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, ListChatDetails.class);
                            String title = listStorage.get(position).getName();
                            int imageurl = imageview[position];
                            String description = listStorage.get(position).getDescription();
                            String cateogory = listStorage.get(position).getCateogory();
                            String id = listStorage.get(position).get_id();
                            intent.putExtra("id", id);
                            intent.putExtra("title", title);
                            intent.putExtra("imgurl", imageurl);
                            intent.putExtra("cateogory", cateogory);
                            intent.putExtra("description", description);
                            context.startActivity(intent);
                        }
                    });
        }
//        if (position % 2 == 0)
//        {
//            convertView.setBackgroundColor(Color.parseColor("#e6e6e6"));
//        }
//        else
//        {
//            convertView.setBackgroundColor(Color.parseColor("#B6B6B6"));
//        }
        return convertView;
    }
    static class ViewHolder
    {
        TextView mTitle;
        TextView mDesc;
        ImageView image;
    }
}
