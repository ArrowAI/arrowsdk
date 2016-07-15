package com.btpltech.bankingbot.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.btpltech.bankingbot.Model.AboutModel;
import com.btpltech.bankingbot.R;

/**
 * Created by Jhasuman on 4/19/2016.
 */
public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.MyViewHolder>
{
    private ArrayList<AboutModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        }
    }

    public AboutAdapter(ArrayList<AboutModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.about_cardview, parent, false);

//        view.setOnClickListener(HomeActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;

        textViewName.setText(dataSet.get(listPosition).getName());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
