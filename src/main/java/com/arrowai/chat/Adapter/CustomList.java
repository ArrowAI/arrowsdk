package com.arrowai.chat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.arrowai.chat.R;

/**
 * Created by Jhasuman on 4/19/2016.
 */
public class CustomList extends ArrayAdapter<String>
{
     Context mcontext;
     int[] imageId;
    public CustomList(Context context,int[] imageId)
    {
        super(context, R.layout.image_list);
        this.mcontext = context;
        this.imageId = imageId;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        //LayoutInflater inflater = .getLayoutInflater();
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View rowView= inflater.inflate(R.layout.image_list, null, true);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}