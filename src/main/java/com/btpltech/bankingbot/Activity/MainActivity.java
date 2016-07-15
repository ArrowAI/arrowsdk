package com.btpltech.bankingbot.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.btpltech.bankingbot.R;

public class MainActivity extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        {
            View view = inflater.inflate(R.layout.activity_main, container, false);
            Button btnStartMATH = (Button) view.findViewById(R.id.btnStartMATH);
            btnStartMATH.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(), ChatActivity.class);
                    i.putExtra("type", "Chat to boat");
                    startActivity(i);
                }
            });
            return view;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}