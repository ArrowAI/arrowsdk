package com.arrowai.chat.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.arrowai.chat.Activity.Chat;
import com.arrowai.chat.Activity.ChatActivity;
import com.arrowai.chat.Adapter.AdapterChatList;
import com.arrowai.chat.Adapter.Adapter_ListChat;
import com.arrowai.chat.Model.Model_ListChat;
import com.arrowai.chat.R;
import com.arrowai.chat.SQliteData.Bots;
import com.arrowai.chat.SQliteData.ChatListData;
import com.arrowai.chat.SQliteData.SQLiteAdapter;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConversationHistory extends Fragment {
    private static final String FIREBASE_URL = "https://incandescent-inferno-5441.firebaseio.com";
    private String mUsername;
    private Firebase mFirebaseRef;
    private ValueEventListener mConnectedListener;
    private AdapterChatList mChatListAdapter;
    ListView listViewChat;
    String username;
    List<String> chats;
    String type;
    List<Chat> chatList;
    ChatListData chatListData;
    private SQLiteAdapter sqLiteAdapter;
    Bots botstable;
    Chat chat;
    Adapter_ListChat adapter;
    int image[]={R.drawable.rsz_bot_1,R.drawable.rsz_bot_2,R.drawable.rsz_bot_3,R.drawable.i4};
    public void getSharedPref() {
        SharedPreferences prefs = getContext().getSharedPreferences("ChatPrefs", 0);
        username = prefs.getString("username", null);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        {
            View view = inflater.inflate(R.layout.activity_conversation_history, container, false);
            getSharedPref();
            listViewChat = (ListView)view.findViewById(R.id.list);
            listViewChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    Chat a = chatList.get(position);
                    Intent i = new Intent(getActivity(), ChatActivity.class);
                    i.putExtra("type", "");
                    i.putExtra("chat", "");
                    i.putExtra("botid",a.getChatbot());
                    i.putExtra("botName",a.getFrom());
                    startActivity(i);
                }
            });
            sqLiteAdapter = new SQLiteAdapter(getActivity());
            chatListData = new ChatListData(sqLiteAdapter);
            botstable = new Bots(sqLiteAdapter);
            chatList = new ArrayList<Chat>();
            bindList();
            return view;
        }
    }
    String chatbot;
    String chatuser;
    String text;
    String from;
    String time;

    private void bindList() {
//    chatList.clear();
        sqLiteAdapter.openToRead();
        Cursor cursorChats = chatListData.getAllChatListData();
        Cursor cursorBots = botstable.getAllChatListData();
        List<Model_ListChat> model_listChats = new ArrayList<Model_ListChat>();
        if (cursorChats == null) {
            return;
        }
        ArrayList<String> bots =new ArrayList<String>();
        if (cursorChats.moveToFirst()) {
            while (!cursorChats.isAfterLast()) {


//                if (cursorBots.moveToFirst()) {
//                    while (!cursorBots.isAfterLast()) {
//
//                        String botId = cursorBots.getString(cursorBots.getColumnIndex("bot_id"));
//
//                        if (chatbot != null && chatbot.toLowerCase().equals(botId.toLowerCase())) {
//
//                            String id = cursorBots.getString(cursorBots.getColumnIndex("bot_id"));
//                            String name = cursorBots.getString(cursorBots.getColumnIndex("bot_name"));
//                            String description = cursorBots.getString(cursorBots.getColumnIndex("bot_desc"));
//
//                            Model_ListChat ab = new Model_ListChat(id, name, description, description, image,"");
//                            model_listChats.add(ab);
//
//                        }
//                        cursorBots.moveToNext();
//
//                    }
//                }

                        chatbot = cursorChats.getString(cursorChats.getColumnIndex("chatbot"));
                        chatuser = cursorChats.getString(cursorChats.getColumnIndex("chatuser"));
                        from = cursorChats.getString(cursorChats.getColumnIndex("fromUser"));
                        if(chatuser!= null && !chatuser.toLowerCase().equals(from.toLowerCase())){
                            if(!bots.contains(chatbot)){
                                text = cursorChats.getString(cursorChats.getColumnIndex("text"));
                                time = cursorChats.getString(cursorChats.getColumnIndex("time"));
                               // chat = new Chat(chatuser, chatbot, text, from, time, null,"");
                                chatList.add(chat);
                                bots.add(chatbot);
                                Model_ListChat ab = new Model_ListChat(chatbot, from, "", "",image,time,null,null,null);
                                model_listChats.add(ab);
                            }

                        }


                cursorChats.moveToNext();

            }
        }

        adapter = new Adapter_ListChat(getContext(), model_listChats, image, "", false);
        listViewChat.setAdapter(adapter);
        if (adapter != null)
            adapter.notifyDataSetChanged();
        cursorChats.close();
        sqLiteAdapter.close();

    }
}
