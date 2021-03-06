package com.arrowai.chat.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import  com.arrowai.chat.Model.NavItem;
import  com.arrowai.chat.R;

import java.util.ArrayList;
/**
 * Created by Ravinder on 6/10/2016.
 */
public class SideMenuAdaper extends BaseAdapter {
        Context mContext;
        ArrayList<NavItem> mNavItems;
        public SideMenuAdaper(Context context, ArrayList<NavItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }
        @Override
        public int getCount() {
            return mNavItems.size();
        }
        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            }
            else {
                view = convertView;
            }
            TextView titleView = (TextView) view.findViewById(R.id.title);
            titleView.setText( mNavItems.get(position).getTitle() );
            return view;
        }
    }

