package com.arrowai.chat.Activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.arrowai.chat.Adapter.AdapterChatList;
import com.arrowai.chat.Adapter.SideMenuAdaper;
import com.arrowai.chat.Adapter.SuggestionAdapter;
import com.arrowai.chat.Adapter.TopMenuAdapter;
import com.arrowai.chat.Model.ArrowAi;
import com.arrowai.chat.Model.IntitialResponse;
import com.arrowai.chat.Model.NavItem;
import com.arrowai.chat.Model.RequestParams;
import com.arrowai.chat.Model.TopMenu;
import com.arrowai.chat.Model.botInitial;
import com.arrowai.chat.Model.buttonPayload;
import com.arrowai.chat.Model.message;
import com.arrowai.chat.Model.messageData;
import com.arrowai.chat.Model.postback;
import com.arrowai.chat.Model.sender;
import com.arrowai.chat.Model.suggestions;
import com.arrowai.chat.R;
import com.arrowai.chat.SQliteData.ChatListData;
import com.arrowai.chat.SQliteData.SQLiteAdapter;
import com.arrowai.chat.util.CommonUtil;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.lucasr.twowayview.TwoWayView;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;
public class ChatActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TextToSpeech.OnInitListener {
    private String email, mobile;
    private ValueEventListener mConnectedListener;
    private AdapterChatList mChatListAdapter;
    private ScrollView scroll;
    private String appId;
    private JSONArray bots;
    private JSONArray sideMenu;
    String input;
    Chat chat;
    List<Chat> chatList;
    ChatListData chatListData;
    private SQLiteAdapter sqLiteAdapter;
    ListView listViewChat;
    EditText inputText;
    String botName;
    String title;
    String openType;
    String text;
    String endExistingFlow;
    GifImageView imgLoading;
    String initialGreeting;
    TopMenu menu;
    List<TopMenu> menuArrayList;
    TopMenuAdapter topMenuAdapter;
    GridView topMenueGrid;
    private String botId;
    private String botImage;
    private String getBotName;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    ArrayList<NavItem> menuItem;
    NavItem sideMenuItem;
    private SideMenuAdaper mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    //firebase implimantion
    private String mActivityTitle;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private String userId;
    private JSONObject defaultbBot;
    private String mUsername;
    private Firebase mFirebaseRef;
    String bot;
    String sideMenus;
    Boolean showSideMenu;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private TextToSpeech textToSpeech;
    private TextView chattingTo;
    private FrameLayout chatStatus;
    TopMenuAdapter bottomMenuAdapter;
    List<TopMenu> topMenuList;
    List<suggestions> suggestionArrary;
    SuggestionAdapter suggestionAdapter;

    TopMenu topMenu;
    suggestions suggestions;
    private GridView bottomMenu;
    ImageButton keyBoard, dashBoard;
    RelativeLayout dynamicKeyboard;
    private TwoWayView towWaySuggestion ;
    private boolean disableTyping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CookieHandler.setDefault(new CookieManager());
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FrameLayout f = (FrameLayout) findViewById(R.id.frameLayout);
                        f.setVisibility(View.VISIBLE);
                    }
                }
        );
        ArrowAi appConfiguration = new ArrowAi();
        menuItem = new ArrayList<>();
        keyBoard = (ImageButton) findViewById(R.id.keyBoard);
        dashBoard = (ImageButton) findViewById(R.id.dashBoard);
        bottomMenu = (GridView) findViewById(R.id.BottomMenu);
        dynamicKeyboard = (RelativeLayout) findViewById(R.id.dynamicKeyboard);
        inputText = (EditText) findViewById(R.id.messageInput);
        towWaySuggestion=(TwoWayView) findViewById(R.id.suggestion);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    String message = inputText.getText().toString();
                    sendMessage(message, false, null);
                    inputText.setText("");
                }
                return true;
            }
        });
        inputText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dynamicKeyboard.setVisibility(View.GONE);
                dashBoard.setVisibility(View.VISIBLE);
                keyBoard.setVisibility(View.GONE);
            }
        });
        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (inputText.length() == 0) {
                    dashBoard.setVisibility(View.VISIBLE);

                } else {
                    keyBoard.setVisibility(View.GONE);
                    dynamicKeyboard.setVisibility(View.GONE);
                    dashBoard.setVisibility(View.VISIBLE);

                }

            }
        });
        keyBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard();
            }
        });
        dashBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showKeybord();

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //getSupportActionBar().setIcon(R.drawable.home);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        listViewChat = (ListView) findViewById(R.id.list);
        setupUsername();
        sqLiteAdapter = new SQLiteAdapter(this);
        chatListData = new ChatListData(sqLiteAdapter);
        chatList = new ArrayList<Chat>();
        menuArrayList = new ArrayList<TopMenu>();
        imgLoading = (GifImageView) findViewById(R.id.imgLoading);
        checkNetwork();
        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        setupDrawer();
        try {
            bots = new JSONArray(bot);
            sideMenu = new JSONArray(sideMenus);
        } catch (Exception e) {

        }
        addDrawerItems();
        Random r = new Random();
        Intent intent = getIntent();
        getSupportActionBar().setTitle(botName);
        //bindList();
        inputText = (EditText) findViewById(R.id.messageInput);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    String message = inputText.getText().toString();
                    sendMessage(message, false, null);
                    inputText.setText("");
                }
                return true;
            }
        });
        chatStatus = (FrameLayout) findViewById(R.id.chatStatus);
        chattingTo = (TextView) findViewById(R.id.chattingTo);
        inputText = (EditText) findViewById(R.id.messageInput);
        inputText.setFocusableInTouchMode(false);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
        inputText.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                // TODO Auto-generated method stub
                inputText.setFocusableInTouchMode(true);
                inputText.requestFocus() ;
                return false;
            }});
        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (inputText.length() == 0) {
                    btnSpeak.setVisibility(View.VISIBLE);
                    findViewById(R.id.sendButton).setVisibility(View.GONE);
                } else {
                    btnSpeak.setVisibility(View.GONE);
                    findViewById(R.id.sendButton).setVisibility(View.VISIBLE);
                }

            }
        });
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    String message = inputText.getText().toString();
                    sendMessage(message, false, new JSONObject());
                    inputText.setText("");
                }
                return true;
            }
        });
        findViewById(R.id.sendButton).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String message = inputText.getText().toString();
                        sendMessage(message, false, null);
                        towWaySuggestion.setVisibility(View.GONE);
                        inputText.setText("");
                    }
                });
        if (chatList.size() <= 0) {
            //getBotInitials();
        }

        /**
         * Showing google speech input dialog
         */
        mChatListAdapter = new
                AdapterChatList(ChatActivity.this, chatList, mUsername);
        listViewChat.setAdapter(mChatListAdapter);
        database = FirebaseDatabase.getInstance();
        bindMenu();

        myRef = database.getReference("outGoingMessage/" + appId + "/users/" + userId + "/messages");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String str = s;
                JSONObject responseObj = new JSONObject();
                JSONObject messageData = new JSONObject();
                JSONObject message = new JSONObject();
                JSONObject sender = new JSONObject();
                dataSnapshot.getChildren();
                Gson gson = new Gson();
                String responseJson = gson.toJson(dataSnapshot.getValue());
                try {
                    responseObj = new JSONObject(responseJson);
                    Log.d("My App", responseObj.toString());
                } catch (Throwable t) {
                }
                if (responseObj.has("message")) {
                    processResponse(responseJson);
                } else if (responseObj.has("messageData")) {
                    try {
                        messageData = responseObj.getJSONObject("messageData");
                        String from = "";
                        String chatText = "";
                        String timestamp;
                        String senderId = "";
                        Chat chat = null;
                        if (responseObj.has("sentFromServer")) {
                            if (responseObj.getBoolean("sentFromServer") == true) {
                                from = "sentFromServer";
                            }
                        }
                        if (responseObj.has("sentFromUser")) {
                            if (responseObj.getBoolean("sentFromUser") == true ) {
                                from = "sentFromUser";
                            }
                        }
                        if (responseObj.has("sentFromRep")) {
                            if (responseObj.getBoolean("sentFromRep") == true) {

                                from = "sentFromRep";
                            }
                        }
                        if (messageData.has("message")) {
                            message = messageData.getJSONObject("message");
                            sender = messageData.getJSONObject("sender");
                            chatText = message.getString("text");
                            senderId = sender.getString("id");
                            timestamp = responseObj.getString("timestamp");
                            if (chatText != null && !chatText.trim().equals("")) {
                                chat = new Chat(mUsername, senderId, chatText, from, timestamp, null, mUsername);
                                chatList.add(chat);
                            }
                        }
                    } catch (Throwable t) {
                    }
                }
                if (responseObj.has("keyboard")) {
                    try {
                        JSONObject keyboard = new JSONObject();
                        keyboard = responseObj.getJSONObject("keyboard");
                        convertStartMessageToKeyboard(keyboard);
                    } catch (Exception e) {
                        Log.d("Error", e.toString());
                    }
                } else {
                    if (responseObj.has("start")) {
                        try {
                            String startVal = responseObj.getString("start");
                            if (startVal == "1") {
                                return;
                            } else {
                                String IntitialResponse = gson.toJson(dataSnapshot.getValue());
                                bindInitialGreatings(IntitialResponse);
                            }
                        } catch (Exception ex) {
                        }
                    }
                }
                //refresh adapter
                mChatListAdapter.notifyDataSetChanged();
                setFocusLastElem();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        View view = ChatActivity.this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(ChatActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }

    void bindMenu() {

        sender s = new sender(userId);
        myRef = database.getReference("inComingMessage");
        botInitial star = new botInitial(appId, 1, s, false, true, Long.valueOf(getTime()), null);
        myRef.push().setValue(star);

    }
    private void getBotInitials(boolean setBotid, boolean senStartMessage, boolean deletebotId, String botIdt) {
        sender s = new sender(userId);
        myRef = database.getReference("inComingMessage");
        if (setBotid && senStartMessage) {
            botId = botIdt;
            botInitial star = new botInitial(appId, botId, 1, s, false, true, Long.valueOf(getTime()), null);
            myRef.push().setValue(star);
        } else if (senStartMessage && !setBotid && !deletebotId) {
            botInitial star = new botInitial(appId, 1, s, false, true, Long.valueOf(getTime()), null);
            myRef.push().setValue(star);
        } else if (deletebotId && senStartMessage) {
            botInitial star = new botInitial(appId, 1, s, false, true, Long.valueOf(getTime()), null);
            myRef.push().setValue(star);
        }
    }
    public void setBotId(JSONArray action, String botName, String botId) {
        towWaySuggestion.setVisibility(View.GONE);
        boolean setbotId = false;
        boolean sendStartMessage = false;
        boolean deletebotId = false;
        boolean sendText = false;
        boolean hideKeyboard = false;
        for (int i = 0; i < action.length(); i++) {
            try {
                if (action.getString(i).equals("setBotId")) {
                    setbotId = true;
                }
                if (action.getString(i).equals("sendStartMessage")) {
                    sendStartMessage = true;
                }
                if (action.getString(i).equals("deleteBotId")) {
                    deletebotId = true;
                }
                if (action.getString(i).equals("sendText")) {
                    sendText = true;
                    hideKeyboard = true;
                }
            } catch (Exception ex) {

            }
        }
        if (sendText) {
            sendMessage(botName, false, null);
            hideKeyboard();
        } else {
            getBotInitials(setbotId, sendStartMessage, deletebotId, botId);
            chat = new Chat(mUsername, botId, botName, "sentFromUser", getTime(), null, "");
            chatList.add(chat);
            mChatListAdapter.notifyDataSetChanged();
            setFocusLastElem();
        }
    }
    public void showSuggestions(JSONArray recommendations) {
        JSONArray suggestionsObj = new JSONArray();
        JSONArray recommendationArray = new JSONArray();
        try {
            if (recommendations.length()!=0 ) {
                towWaySuggestion.setVisibility(View.VISIBLE);
                suggestionArrary = new ArrayList<suggestions>();
                recommendationArray = recommendations;
                for (int i = 0; i < recommendationArray.length(); i++) {
                    suggestions = new suggestions(recommendationArray.get(i).toString());
                    suggestionArrary.add(suggestions);
                }
            }

        } catch (Exception e) {
            Toast.makeText(ChatActivity.this, e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        suggestionAdapter = new SuggestionAdapter(ChatActivity.this, suggestionArrary);
        towWaySuggestion.setAdapter(suggestionAdapter);
        suggestionAdapter.notifyDataSetChanged();

    }

    public void convertStartMessageToKeyboard(JSONObject keyboard) {
        JSONArray option = new JSONArray();
        JSONArray action = new JSONArray();
        try {

                if(keyboard.has("disableTyping"))
                {
                    disableTyping=keyboard.getBoolean("disableTyping");
                }
                if(disableTyping)
                {
                    inputText.setEnabled(false);
                }
                if(!disableTyping)
                {
                    inputText.setEnabled(true);
                }
            if (keyboard.has("options")) {

                topMenuList = new ArrayList<TopMenu>();
                option = keyboard.getJSONArray("options");
                for (int i = 0; i < option.length(); i++) {
                    JSONObject jsonObject = (JSONObject) option.get(i);
                    action = jsonObject.getJSONArray("action");
                    String botName = jsonObject.getString("text");
                    String botId = "";
                    if (jsonObject.has("botId")) {
                        botId = jsonObject.getString("botId");
                    }
                    topMenu = new TopMenu(action, botName, botId);
                    topMenuList.add(topMenu);
                }
            }

        } catch (Exception e) {
            Toast.makeText(ChatActivity.this, e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        bottomMenuAdapter = new TopMenuAdapter(ChatActivity.this, topMenuList);
        bottomMenu.setAdapter(bottomMenuAdapter);
        bottomMenuAdapter.notifyDataSetChanged();
        showKeybord();
    }

    public void showKeybord() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(ChatActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        dashBoard.setVisibility(View.GONE);
        dynamicKeyboard.setVisibility(View.VISIBLE);
        keyBoard.setVisibility(View.VISIBLE);
    }

    public void hideKeyboard() {
        keyBoard.setVisibility(View.GONE);
        dynamicKeyboard.setVisibility(View.GONE);
        dashBoard.setVisibility(View.VISIBLE);
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
        }
    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    inputText.setText(result.get(0));
                }
                break;
            }
        }
    }

    public void sendMessage(String chatText, final boolean botResend, JSONObject payloadParam) {
        if (chatText.equals("") && !botResend) {
            return;
        }
        try {
            if (payloadParam != null) {
                String variable = "", value = "";
                buttonPayload payload = new buttonPayload();
                postback postback = new postback();
                message m = new message();
                variable = payloadParam.getString("variable");
                value = payloadParam.getString("value");
                payload.setVariable(variable);
                payload.setValue(value);
                postback.setPayload(payload);
                m.setPostback(postback);
                sender s = new sender(userId);
                messageData mdata = new messageData(m, s);
                RequestParams req = new RequestParams(botId, appId, null, mdata, null, s, false, true, Long.valueOf(getTime()));
                myRef = database.getReference("inComingMessage");
                myRef.push().setValue(req);

            } else {
                message m = new message();
                m.setText(chatText);
                sender s = new sender(userId);
                messageData mdata = new messageData(m, s);
                RequestParams req = new RequestParams(botId, appId, null, mdata, null, s, false, true, Long.valueOf(getTime()));
                myRef = database.getReference("inComingMessage");
                myRef.push().setValue(req);
            }
        } catch (Exception e) {

        }
    }

    void bindInitialGreatings(String response) {
        try {
            JSONObject responseJson = new JSONObject(response);
            JSONObject data = responseJson.getJSONObject("start");
            String suggestionArray = null;
            if (data.has("suggestions")) {
                suggestionArray = data.getString("suggestions");

            }
            if (data.has("initialGreeting")) {
                initialGreeting = data.getString("initialGreeting");
            }
            if (initialGreeting != null && initialGreeting.length() > 0) {
                chat = new Chat(mUsername, botId, initialGreeting, botName, getTime(), null, "");
                chatList.add(chat);
                chat = new Chat(mUsername, botId, suggestionArray, botName, getTime(), null, "list");
                chatList.add(chat);
            }
            mChatListAdapter = new AdapterChatList(ChatActivity.this, chatList, mUsername);
            listViewChat.setAdapter(mChatListAdapter);
            mChatListAdapter.notifyDataSetChanged();
            imgLoading.setVisibility(View.GONE);
            setFocusLastElem();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    void processResponse(String response) {
        try {
            JSONObject responseJson = new JSONObject(response);

            String callAgain = "", text = "", noResponse = "",from="";
            attachmentJson = null;
            if (responseJson.has("sentFromServer")) {
                if (responseJson.getBoolean("sentFromServer") == true) {
                    from = "sentFromServer";
                }
            }
            if (responseJson.has("sentFromUser")) {
                if (responseJson.getBoolean("sentFromUser") == true ) {
                    from = "sentFromUser";
                }
            }
            if (responseJson.has("sentFromRep")) {
                if (responseJson.getBoolean("sentFromRep") == true) {

                    from = "sentFromRep";
                }
            }
            if (responseJson.has("message")) {
                JSONObject messageJson = responseJson.getJSONObject("message");
                if(messageJson.has("recommendations")) {
                    showSuggestions(messageJson.getJSONArray("recommendations"));
                }
                if (messageJson.has("callAgain")) {
                    callAgain = messageJson.getString("callAgain");
                }
                if (messageJson.has("noResponse")) {
                    noResponse = messageJson.getString("noResponse");
                }
                if (messageJson.has("text")) {
                    text = messageJson.getString("text");
                }
                if (messageJson.has("attachment")) {
                    attachmentJson = new JSONObject();
                    attachmentJson = messageJson.getJSONObject("attachment");
                }
                if (!noResponse.toLowerCase().equals("true")) {
                    chat = new Chat(mUsername, botId, text, from, getTime(), attachmentJson, "");
                    chatList.add(chat);
                }
                if (callAgain != null && callAgain.toLowerCase().equals("true")) {
                    // sendMessage("", true, null);
                } else {
                    RelativeLayout lytSuggestion = (RelativeLayout) findViewById(R.id.lytSuggestion);
                    if (messageJson.has("suggestion")) {
                        String suggestion = messageJson.getString("suggestion");
                        lytSuggestion.setVisibility(View.VISIBLE);
                        TextView textSuggestionMessage = (TextView) findViewById(R.id.textSuggestionMessage);
                        textSuggestionMessage.setText(suggestion);
                    } else {
                        lytSuggestion.setVisibility(View.GONE);
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void getBotInitials() {
        sender s = new sender(userId);
        botInitial star = new botInitial(appId, botId, 1, s, false, true, Long.valueOf(getTime()), null);
        myRef = database.getReference("inComingMessage");
        myRef.push().setValue(star);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        if (showSideMenu) {
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            mDrawerLayout.setDrawerListener(mDrawerToggle);
        } else {
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }

    }

    private void addDrawerItems() {
       /* menuItem
          mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems);
  		mDrawerList.setAdapter(mAdapter);
  		*/
        try {

            if (sideMenu.get(1).equals(null)) {
                return;
            } else {
                for (int i = 0; i < sideMenu.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) sideMenu.get(i);
                        botId = jsonObject.getString("bot_id");
                        title = jsonObject.getString("title");
                        if (jsonObject.has("text")) {
                            text = jsonObject.getString("text");
                        }
                        openType = jsonObject.getString("open_type");
                        endExistingFlow = jsonObject.getString("endExistingFlow");
                        sideMenuItem = new NavItem(title, openType, botId, text, endExistingFlow);
                        menuItem.add(sideMenuItem);
                    } catch (Exception e) {
                    }
                }
            }
        } catch (Exception e) {

        }
        mAdapter = new SideMenuAdaper(ChatActivity.this, menuItem);
        mDrawerList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDrawerLayout.closeDrawer(mDrawerList);
                String bot_id = menuItem.get(position).getBot_id();
                title = menuItem.get(position).getTitle();
                text = menuItem.get(position).getText();
                openType = menuItem.get(position).getOpen_type();
                endExistingFlow = menuItem.get(position).getEndExistingFlow();
                botId = bot_id;
                if (openType.toLowerCase() == "bot") {
                    getBotInitials();
                    return;
                }
                sendMessage(text, false, null);
            }
        });
    }

    public void saveSharedPref(String name) {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", name);
        editor.putString("userId", name);
        editor.commit();
    }

    MenuItem menuItems;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            FrameLayout f = (FrameLayout) findViewById(R.id.frameLayout);
            f.setVisibility(View.VISIBLE);
            listViewChat.setEmptyView(new View(ChatActivity.this));
        }
        if (id == R.id.action_logout) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences("ChatShellPrefs", Context.MODE_PRIVATE);
            settings.edit().clear().commit();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkNetwork() {
        RelativeLayout noconnection = (RelativeLayout) findViewById(R.id.noconnection);
        if (!CommonUtil.CheckInternet(getApplicationContext())) {

            noconnection.setVisibility(View.VISIBLE);
        } else {
            noconnection.setVisibility(View.GONE);
        }
    }

    private void setupUsername() {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        mUsername = prefs.getString("username", null);
        email = prefs.getString("email", null);
        mobile = prefs.getString("mobile", null);
        userId = prefs.getString("userId", null);
        bot = prefs.getString("bots", null);
        sideMenus = prefs.getString("sideMenu", null);
        appId = prefs.getString("appId", null);
        showSideMenu = prefs.getBoolean("showMenu", false);
        botName = prefs.getString("appName", null);
        if (mUsername == null) {
            Random r = new Random();
            prefs.edit().putString("username", mUsername).commit();
        }
    }

    private void bindList() {
//    chatList.clear();
        sqLiteAdapter.openToRead();
        Cursor cursor = chatListData.getAllChatListData();
        if (cursor == null) {
            return;
        }
        String chatbot;
        String chatuser;
        String text;
        String from;
        String time;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                chatbot = cursor.getString(cursor.getColumnIndex("chatbot"));
                chatuser = cursor.getString(cursor.getColumnIndex("chatuser"));
                if (chatbot != null && chatbot.toLowerCase().equals(botId.toLowerCase()) && chatuser.toLowerCase().equals(mUsername.toLowerCase())) {
                    text = cursor.getString(cursor.getColumnIndex("text"));
                    from = cursor.getString(cursor.getColumnIndex("fromUser"));
                    time = cursor.getString(cursor.getColumnIndex("time"));
                    chat = new Chat(chatuser, chatbot, text, from, time, null, "");
                    chatList.add(chat);
                    mChatListAdapter = new AdapterChatList(getApplicationContext(), chatList, mUsername);
                    listViewChat.setAdapter(mChatListAdapter);
                }
                cursor.moveToNext();
            }
        }
        // mChatListAdapter = new AdapterChatList(null, ChatActivity.this, R.layout.activity_chat,chat,mUsername);
        cursor.close();
        sqLiteAdapter.close();
        setFocusLastElem();
    }
    private void setFocusLastElem() {
        listViewChat.setSelection(chatList.size() - 1);

    }

    Chat suggestionChat;
    JSONObject payload;
    JSONObject attachmentJson;

    public void showCardDiscriopion(String cardName, String cardDescription) {

        if (chatList != null && chatList.size() > 0) {
            chat = chatList.get(chatList.size() - 1);
        }
        if (chat != null && chat.getType().equals("card_detail")) {
            chatList.remove(chatList.size() - 1);
        }

        chat = new Chat(cardName, cardDescription, "", "", "", null, "card_detail");
        chatList.add(chat);
        mChatListAdapter = new AdapterChatList(ChatActivity.this, chatList, mUsername);
        listViewChat.setAdapter(mChatListAdapter);
        setFocusLastElem();
    }

    private String getTime() {
        Long tsLong = System.currentTimeMillis();
        String ts = tsLong.toString();
        return ts;
    }

    private String getStringFromArray(JSONArray array) {
        String s = "";
        try {
            for (int i = 0; i < array.length(); i++) {
                s += String.valueOf(array.get(i) + "/n");
            }
        } catch (JSONException e) {
            e.getStackTrace();
        }
        return s;
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(mDrawerList);
        return false;
    }

    public void setBotId(String botsId, String name) {
        botId = botsId;
        chatStatus.setVisibility(View.VISIBLE);
        FrameLayout f = (FrameLayout) findViewById(R.id.frameLayout);
        f.setVisibility(View.GONE);
        chattingTo.setText("Talking To: " + name);
        getBotInitials();
    }


    public void bindWidget(String type) {
        getBotInitials();
    }

    public int getTotalHeightofListView(ListView listView) {
        ListAdapter mAdapter = listView.getAdapter();
        int totalHeight = 0;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);

            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),

                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            totalHeight += mView.getMeasuredHeight();
            Log.w("HEIGHT" + i, String.valueOf(totalHeight));
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
        return totalHeight;
    }
    @Override
    public void onInit(int status) {

    }
}
