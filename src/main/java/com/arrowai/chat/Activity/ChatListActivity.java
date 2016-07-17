package com.arrowai.chat.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.arrowai.chat.R;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private static final String FIREBASE_URL = "https://incandescent-inferno-5441.firebaseio.com";
    private String mUsername;
    private Firebase mFirebaseRef;
    private ValueEventListener mConnectedListener;
    private ChatListAdapter mChatListAdapter;
    ListView listViewChat;
    String username;
    List<String> chats;
    String  type;
    public void getSharedPref()
    {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        username = prefs.getString("username", null);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        String mUsername = prefs.getString("username", null);

        View navHeaderView= navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView tvHeaderName= (TextView) navHeaderView.findViewById(R.id.txtUsername);
        tvHeaderName.setText(mUsername);
        navigationView.setNavigationItemSelectedListener(this);
        

        //CHAT IMPLEMENTATION START=========================================
        listViewChat=(ListView)findViewById(R.id.list);
        // Make sure we have a mUsername
        //setupUsername();

        setTitle("Bot list");
        chats = new ArrayList<String>();
        for(int i = 0;i<5;)
        {
            chats.add("Bot "+ i++);
        }
        bindList();
        Random r = new Random();
        Intent intent =getIntent();
        type = intent.getStringExtra("type");
        listViewChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.putExtra("type", type);
                i.putExtra("chat", chats.get(position));
                startActivity(i);
            }
        });
    }
    public void bindList(){
       ListAdapter listAdapter = new ArrayAdapter<String>(ChatListActivity.this,
                android.R.layout.simple_list_item_1, chats);
        listViewChat.setAdapter(listAdapter);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

          if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
