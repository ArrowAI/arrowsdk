package com.arrowai.chat.Adapter;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.apache.commons.codec.binary.StringUtils;
import org.lucasr.twowayview.TwoWayView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
//import com.arrowai.smartchat.Activity.AddMoneyActivity;
import com.android.volley.toolbox.NetworkImageView;
import com.arrowai.chat.Activity.Chat;
import com.arrowai.chat.Activity.ChatActivity;
import com.arrowai.chat.Model.ButtonTemplate;
import com.arrowai.chat.Model.Card;
import com.arrowai.chat.Model.CardButtonItem;
import com.arrowai.chat.Model.Confirmation;
import com.arrowai.chat.Model.TransactionHistory;
import com.arrowai.chat.Model.VolleySingleton;
import com.arrowai.chat.R;
import com.arrowai.chat.util.CommonUtil;
import com.arrowai.chat.Model.TopMenu;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suman kumar jha on 5/20/2016.
 */

public class AdapterChatList extends BaseAdapter {
    private LayoutInflater layoutinflater;
    ViewHolder listViewHolder;
    Card cardList;
    ArrayList<Card> cardListArry;
    ArrayList<CardButtonItem> cardBtnListArry;
    TransactionHistory transactionList;
    private List<Chat> listStorage;
    private Context context;
    private CardListAdapter cardListAdapter;
    private ConfirmationAdapter confirmationAdapter;
    private ArrayList<Confirmation> confirmationList;
    private ArrayList<ButtonTemplate> buttonList;
    private ButtonTemplate buttons;
    private Confirmation confirmation;
    private TransactionAdapter transactionAdapter;
    private ButtonTemplateAdapter buttonTemplateAdapter;
    private static final int UNBOUNDED = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    TopMenu menu;
  //  List<TopMenu> menuArrayList;
    TopMenuAdapter topMenuAdapter;
    String mUsername;

    public AdapterChatList(Context context, List<Chat> customizedListView, String userName) {
        this.context = context;
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;
        mUsername = userName;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        listViewHolder = new ViewHolder();

        boolean bindDefaultEvents = true;
        String from = listStorage.get(position).getFrom().toLowerCase();
        String type = listStorage.get(position).getType().toLowerCase();
        JSONObject attachmentJSON = listStorage.get(position).getAttachmentJson();
        String templateType = "";
        String buttonType = "";
        final JSONObject cardList;
        JSONObject tempJSON = new JSONObject();
        if (attachmentJSON != null && attachmentJSON.has("type")) {
            try {
                tempJSON = attachmentJSON;
                if (tempJSON.has("type")) {
                    templateType = tempJSON.getString("type");
                }
            } catch (Exception e) {
            }
        }
        JSONObject payloadTemplate = new JSONObject();
        String message = "";
        String tempType = "";
        System.out.println("templateType---"+templateType);
        if (templateType != null && templateType.toLowerCase().equals("template")) {
            bindDefaultEvents = false;
            try {
                if (tempJSON != null && tempJSON.has("payload")) {
                    payloadTemplate = tempJSON.getJSONObject("payload");
                }
                if (payloadTemplate != null && payloadTemplate.has("text")) {
                    message = payloadTemplate.getString("text").toString();
                }
                if (payloadTemplate != null && payloadTemplate.has("template_type")) {
                    tempType = payloadTemplate.getString("template_type");
                }
                System.out.println("tempType---"+tempType);
                if (tempType.equals("transaction_list")) {
                    convertView = layoutinflater.inflate(R.layout.transaction_history, parent, false);
                    listViewHolder.cardList = (GridView) convertView.findViewById(R.id.cardGrid);
                    listViewHolder.textMessage = (TextView) convertView.findViewById(R.id.textHeading);
                    listViewHolder = bindTransaction(listViewHolder, payloadTemplate, position, message);
                    convertView.setTag(listViewHolder);
                } else if (tempType.equals("confirmation")) {
                    convertView = layoutinflater.inflate(R.layout.card_confirmation, parent, false);
                    listViewHolder.listOptions = (ListView) convertView.findViewById(R.id.chageList);
                    listViewHolder.textMessage = (TextView) convertView.findViewById(R.id.textHeading);
                    listViewHolder.btnYes = (Button) convertView.findViewById(R.id.btnYes);
                    // listViewHolder.btnNo = (Button) convertView.findViewById(R.id.btnNo);
                    listViewHolder = bindButtonEvents(listViewHolder, tempJSON);
                    convertView.setTag(listViewHolder);
                } else if (tempType.equals("search_list")) {
                    cardListArry = new ArrayList<>();
                    cardBtnListArry = new ArrayList<>();
                    convertView = layoutinflater.inflate(R.layout.card_list_widget, parent, false);
                    listViewHolder.cardList_New = (TwoWayView) convertView.findViewById(R.id.cardGrid);
                    listViewHolder.textMessage = (TextView) convertView.findViewById(R.id.textHeading);
                    listViewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.cardDetail);
                    listViewHolder.cardName = (TextView) convertView.findViewById(R.id.cardName);
                    listViewHolder.cardDescription = (TextView) convertView.findViewById(R.id.cardDescription);
                    listViewHolder.textMessage.setText(message);
                    listViewHolder = bindGridvents(listViewHolder, payloadTemplate, position);
                    listViewHolder.cardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String descripiton = cardListArry.get(position).getCardDescription();
                            String cardName = cardListArry.get(position).getCardName();
                            ((ChatActivity) context).showCardDiscriopion(cardName, descripiton);
                        }
                    });
                    convertView.setTag(listViewHolder);
                } else if (tempType.equals("button")) {
                    convertView = layoutinflater.inflate(R.layout.button_template, parent, false);
                    listViewHolder.textMessage = (TextView) convertView.findViewById(R.id.textHeading);
                    listViewHolder.cardList = (GridView) convertView.findViewById(R.id.buttongrid);
                    listViewHolder = bindButtonEvents(listViewHolder, tempJSON);
                    convertView.setTag(listViewHolder);
                }
                else if (tempType.equals("button_bottom")) {
                    convertView = layoutinflater.inflate(R.layout.button_template, parent, false);
                    listViewHolder.textMessage = (TextView) convertView.findViewById(R.id.textHeading);
                    listViewHolder.cardList = (GridView) convertView.findViewById(R.id.buttongrid);
                    listViewHolder = bindButtonEvents(listViewHolder, tempJSON);
                    convertView.setTag(listViewHolder);
                }
                else if (tempType.equals("image")) {
                    convertView = layoutinflater.inflate(R.layout.button_template, parent, false);
                    listViewHolder.textMessage = (TextView) convertView.findViewById(R.id.textHeading);
                    listViewHolder.cardList = (GridView) convertView.findViewById(R.id.buttongrid);
                    listViewHolder = bindButtonEvents(listViewHolder, tempJSON);
                    convertView.setTag(listViewHolder);
                }
            } catch (Exception e) {

            }
        } else if (type.equals("card_detail")) {
            bindDefaultEvents = false;
            convertView = layoutinflater.inflate(R.layout.card_discription, parent, false);
            listViewHolder.cardName = (TextView) convertView.findViewById(R.id.cardName);
            listViewHolder.cardDescription = (TextView) convertView.findViewById(R.id.cardDescription);
           convertView.setTag(listViewHolder);
            listViewHolder.cardName.setText(listStorage.get(position).getChatuser());
            listViewHolder.cardDescription.setText(listStorage.get(position).getChatbot());
            //convertView.setTag(listViewHolder);

        } else if (type.equals("list")) {
            bindDefaultEvents = false;
            convertView = layoutinflater.inflate(R.layout.list_options, parent, false);
            listViewHolder.listOptions = (ListView) convertView.findViewById(R.id.listOptions);
            listViewHolder = bindListEvents(listViewHolder, tempJSON, position);
            convertView.setTag(listViewHolder);
        }
        else if (type.equals("topmenu")) {
            bindDefaultEvents = false;
            convertView = layoutinflater.inflate(R.layout.top_menu, parent, false);
            listViewHolder.topMenueGrid = (TwoWayView) convertView.findViewById(R.id.gridTop);
            listViewHolder = bindTopMenuEvents(listViewHolder, attachmentJSON, position);
            convertView.setTag(listViewHolder);
        } else if (templateType != null && templateType.toLowerCase().equals("template")) {
            bindDefaultEvents = false;
            convertView = layoutinflater.inflate(R.layout.list_options, parent, false);
            listViewHolder.listOptions = (ListView) convertView.findViewById(R.id.listOptions);
            listViewHolder = bindListEvents(listViewHolder, tempJSON, position);
            convertView.setTag(listViewHolder);
        } else if (from.equals("sentfromserver")&& !listStorage.get(position).getMessage().trim().equals("")) {
            convertView = layoutinflater.inflate(R.layout.chat_message_left, parent, false);
        } else if (from.equals("sentfromrep") && !listStorage.get(position).getMessage().trim().equals("")) {
            convertView = layoutinflater.inflate(R.layout.chat_message_left, parent, false);
        }
        else if (from.equals("sentfromuser") && !listStorage.get(position).getMessage().trim().equals("")) {
            convertView = layoutinflater.inflate(R.layout.chat_message_right, parent, false);
        }
        else if(from.equals(mUsername.toLowerCase()))
        {
            convertView = layoutinflater.inflate(R.layout.chat_message_right, parent, false);
        } else if (templateType != null && templateType.toLowerCase().equals("image")) {
            bindDefaultEvents = false;
            convertView = layoutinflater.inflate(R.layout.image_item, parent, false);
            listViewHolder.image = (NetworkImageView) convertView.findViewById(R.id.imageNetwork);
            convertView.setTag(listViewHolder);

            try {
                ImageLoader mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
                JSONObject payloadObject =attachmentJSON.optJSONObject("payload");
                JSONObject imageObject =payloadObject.optJSONObject("images");
                String imageUrl =imageObject.optString("url");
                listViewHolder.image.setImageUrl(imageUrl, mImageLoader);
            } catch (Exception e) {
            }
        }

        else if (templateType != null && templateType.toLowerCase().equals("video")) {
            bindDefaultEvents = false;
            convertView = layoutinflater.inflate(R.layout.video_item, parent, false);
            listViewHolder.videoView = (VideoView) convertView.findViewById(R.id.videoView);
            convertView.setTag(listViewHolder);

            try {
                ImageLoader mImageLoader = VolleySingleton.getInstance(context).getImageLoader();
                JSONObject payloadObject =attachmentJSON.optJSONObject("payload");
                JSONObject imageObject =payloadObject.optJSONObject("images");
                String imageUrl =imageObject.optString("url");
                MediaController mediaController = new MediaController(context);
                mediaController.setAnchorView(listViewHolder.image);

                listViewHolder.videoView.setMediaController(mediaController);
                listViewHolder.videoView.setVideoURI(Uri.parse("http://file2.video9.in/english/movie/2014/x-men-_days_of_future_past/X-Men-%20Days%20of%20Future%20Past%20Trailer%20-%20[Webmusic.IN].3gp"));

                listViewHolder.videoView.start();
            } catch (Exception e) {
            }
        }


        else  {
            convertView = layoutinflater.inflate(R.layout.chat_message_left, parent, false);
        }

        if (bindDefaultEvents) {
            listViewHolder.textdate = (TextView) convertView.findViewById(R.id.textdate);
            listViewHolder.msgContainer = (LinearLayout) convertView.findViewById(R.id.Lbyother);
            listViewHolder.textMessage = (TextView) convertView.findViewById(R.id.textMessage);
            convertView.setTag(listViewHolder);
            String timeAgo = CommonUtil.getTimeAgo(Long.valueOf(listStorage.get(position).getTime()), context);
            if (!listStorage.get(position).getMessage().trim().equals("") && listViewHolder.textdate != null) {
                listViewHolder.textdate.setText(timeAgo);
            }
            if (listStorage.get(position).getMessage().trim().equals("")) {
                listViewHolder.msgContainer.setVisibility(View.GONE);
                listViewHolder.textdate.setVisibility(View.GONE);
            }
            listViewHolder.textMessage.setText(listStorage.get(position).getMessage());
           // convertView.setTag(listViewHolder);
        }
        return convertView;


//        else if (templateType != null && templateType.toLowerCase().equals("template")) {
//            bindDefaultEvents = false;
//            convertView = layoutinflater.inflate(R.layout.list_options, parent, false);
//            listViewHolder.listOptions = (ListView) convertView.findViewById(R.id.listOptions);
//            listViewHolder = bindListEvents(listViewHolder, tempJSON, position);
//        } else if (from.equals(mUsername.toLowerCase())) {
//            convertView = layoutinflater.inflate(R.layout.chat_message_right, parent, false);
//        } else if (from.equals(mUsername.toLowerCase())) {
//            convertView = layoutinflater.inflate(R.layout.chat_message_right, parent, false);
//        } else {
//            convertView = layoutinflater.inflate(R.layout.chat_message_left, parent, false);
//        }
//        if (bindDefaultEvents) {
//            listViewHolder.textdate = (TextView) convertView.findViewById(R.id.textdate);
//            listViewHolder.msgContainer = (LinearLayout) convertView.findViewById(R.id.Lbyother);
//            listViewHolder.textMessage = (TextView) convertView.findViewById(R.id.textMessage);
//            convertView.setTag(listViewHolder);
//            String timeAgo = CommonUtil.getTimeAgo(Long.valueOf(listStorage.get(position).getTime()), context);
//            if (!listStorage.get(position).getMessage().trim().equals("") && listViewHolder.textdate != null) {
//                listViewHolder.textdate.setText(timeAgo);
//            }
//            if (listStorage.get(position).getMessage().trim().equals("")) {
//                listViewHolder.msgContainer.setVisibility(View.GONE);
//                listViewHolder.textdate.setVisibility(View.GONE);
//            }
//            listViewHolder.textMessage.setText(listStorage.get(position).getMessage());
//        }
//        return convertView;
    }

    static class ViewHolder {
        TextView textdate;
        TextView textMessage;
        Button btnYes;
        Button btnNo;
        Button payBill;
        ListView listOptions;
        NetworkImageView image;
        VideoView videoView;
        TwoWayView topMenueGrid;
        GridView cardList;
        TwoWayView cardList_New;
        RelativeLayout relativeLayout;
        TextView cardName, cardDescription;
        LinearLayout msgContainer;
    }
    private ViewHolder bindGridvents(ViewHolder viewHolder, JSONObject tempJSON, int position) {
        JSONArray jsonArray = new JSONArray();
        JSONArray buttons = new JSONArray();
        cardListArry.clear();
        try {
            jsonArray = tempJSON.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String cardName = jsonObject.getString("title");
                String cardImage = jsonObject.getString("image");
                String cardImageUrl = jsonObject.getString("url");
                String description = jsonObject.getString("description");
                if (jsonObject.has("buttons")) {
                    buttons = jsonObject.getJSONArray("buttons");
                }
                cardList = new Card(cardName, cardImage,cardImageUrl,description, buttons);
                cardListArry.add(cardList);
            }
        } catch (Exception e) {
        }
        cardListAdapter = new CardListAdapter(context, cardListArry);

        listViewHolder.cardList_New.setAdapter(cardListAdapter);
        cardListAdapter.notifyDataSetChanged();
        return viewHolder;
    }

    private ViewHolder bindTransaction(ViewHolder viewHolder, JSONObject tempJSON, int position, String message) {
        final ArrayList<TransactionHistory> tarnArrayList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = tempJSON.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String type = jsonObject.getString("type");
                String date = jsonObject.getString("date");
                String amount = jsonObject.getString("amount");
                String transaction_type = jsonObject.getString("transaction_type");
                String payementTo = jsonObject.getString("payementTo");
                transactionList = new TransactionHistory(type, date, amount, transaction_type, payementTo);
                tarnArrayList.add(transactionList);
            }


        } catch (Exception e) {
        }
        transactionAdapter = new TransactionAdapter(context, tarnArrayList);
        listViewHolder.textMessage.setText(message);
        listViewHolder.cardList.setAdapter(transactionAdapter);
        ViewGroup.LayoutParams layoutParams = listViewHolder.cardList.getLayoutParams();
        layoutParams.height = jsonArray.length() * 350; //this is in pixels
        listViewHolder.cardList.setLayoutParams(layoutParams);

        transactionAdapter.notifyDataSetChanged();

        return viewHolder;
    }
    private ViewHolder bindTopMenuEvents(ViewHolder viewHolder, JSONObject tempJSON, int position) {

        JSONArray jsonArray = new JSONArray();
        final ArrayList<TopMenu> menuArrayList = new ArrayList<>();
        try {
            jsonArray = tempJSON.getJSONArray("bots");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String botId = jsonObject.getString("bot_id");
                String botImage = "";//jsonObject.getString("image");
                String botName = jsonObject.getString("bot_text");
               // menu = new TopMenu(botId, botImage, botName);
                menuArrayList.add(menu);
            }


        } catch (Exception e) {
            Toast.makeText(context, e.toString(),
                    Toast.LENGTH_LONG).show();
        }
        topMenuAdapter = new TopMenuAdapter(context, menuArrayList);
        listViewHolder.topMenueGrid.setAdapter(topMenuAdapter);
        topMenuAdapter.notifyDataSetChanged();
        return viewHolder;
    }

    JSONArray jsonArray = new JSONArray();

    private ViewHolder bindListEvents(ViewHolder viewHolder, JSONObject tempJSON, int position) {
        final ArrayList<String> arryList = new ArrayList<>();
        try {
            jsonArray = new JSONArray(listStorage.get(position).getMessage());
            for (int i = 0; i < jsonArray.length(); i++) {
                String item = jsonArray.getString(i);
                arryList.add(item);
            }
        } catch (Exception e) {
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, arryList);
        ViewGroup.LayoutParams layoutParams = listViewHolder.listOptions.getLayoutParams();
        layoutParams.height = jsonArray.length() * 150; //this is in pixels
        listViewHolder.listOptions.setLayoutParams(layoutParams);
        listViewHolder.listOptions.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        viewHolder.listOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                if (context instanceof ChatActivity) {
                    ((ChatActivity) context).sendMessage(arryList.get(position), false, null,false,"","");
                }

            }
        });
        return viewHolder;
    }



    JSONObject btnPayloadYes1;
    JSONObject btnPayloadNO1;
    ChatActivity chat;
    private ViewHolder bindButtonEvents(ViewHolder viewHolder, JSONObject tempJSON) {
        JSONObject payloadTemplate = new JSONObject();
        JSONArray buttonsArray = new JSONArray();
        JSONArray ChagebuttonsArray = new JSONArray();
        JSONObject button1JSON = new JSONObject();
        String message = "";
        btnPayloadYes1 = new JSONObject();
        btnPayloadNO1 = new JSONObject();
        try {
            if (tempJSON != null && tempJSON.has("payload")) {
                payloadTemplate = tempJSON.getJSONObject("payload");
            }
            if (payloadTemplate.has("text")) {
                message = payloadTemplate.getString("text");
            }
            JSONObject jsonObject = new JSONObject();
            if (payloadTemplate != null && payloadTemplate.has("confirmation")) {
                confirmationList = new ArrayList<>();
                jsonObject = payloadTemplate.getJSONObject("confirmation");
                viewHolder.textMessage.setText(message);
                viewHolder.btnYes.setText("continue");
                buttonsArray = jsonObject.getJSONArray("actionButton");
                button1JSON = buttonsArray.getJSONObject(0);
                btnPayloadYes1 = button1JSON.getJSONObject("payload");
                if (jsonObject.has("details")) {
                    ChagebuttonsArray = jsonObject.getJSONArray("details");
                    final ArrayList<TransactionHistory> tarnArrayList = new ArrayList<>();
                    try {
                        for (int i = 0; i < ChagebuttonsArray.length(); i++) {
                            jsonObject = (JSONObject) ChagebuttonsArray.get(i);
                            String type = jsonObject.getString("type");
                            String title = jsonObject.getString("title");
                            String info = jsonObject.getString("message");
                            JSONObject payload = jsonObject.getJSONObject("payload");
                            confirmation = new Confirmation(type, title, info, payload);
                            confirmationList.add(confirmation);
                        }

                    } catch (Exception e) {
                    }
                    ViewGroup.LayoutParams layoutParams = listViewHolder.listOptions.getLayoutParams();
                   layoutParams.height = ChagebuttonsArray.length() * 230; //this is in pixels
                    listViewHolder.listOptions.setLayoutParams(layoutParams);
                    confirmationAdapter = new ConfirmationAdapter(context, confirmationList);
                    listViewHolder.listOptions.setAdapter(confirmationAdapter);
                    confirmationAdapter.notifyDataSetChanged();
                }

            } else if (payloadTemplate != null && payloadTemplate.has("buttons")) {
                buttonList = new ArrayList<>();
                viewHolder.textMessage.setText(message);
                ChagebuttonsArray = payloadTemplate.getJSONArray("buttons");
                try {
                    for (int i = 0; i < ChagebuttonsArray.length(); i++) {
                        payloadTemplate = (JSONObject) ChagebuttonsArray.get(i);
                        String title = payloadTemplate.optString("title");
                        //JSONObject payload = payloadTemplate.optJSONObject("payload");
                        String type = "";
                        String url = "";
                        if (payloadTemplate.has("type")) {
                            type = payloadTemplate.optString("type");
                        }
                        if (payloadTemplate.has("url")) {
                            url = payloadTemplate.optString("url");
                        }
                        if (payloadTemplate.has("mobile_url")) {
                            url = payloadTemplate.optString("mobile_url");
                        }
//                        String type, String title, String message, JSONObject payload, String url
                        buttons = new ButtonTemplate(type, title, "", payloadTemplate, url);
                        buttonList.add(buttons);



                    }

                } catch (Exception e) {
                }


                buttonTemplateAdapter = new ButtonTemplateAdapter(context, buttonList);
                listViewHolder.cardList.setAdapter(buttonTemplateAdapter);
                buttonTemplateAdapter.notifyDataSetChanged();

                if (ChagebuttonsArray.length() > 1) {
                    ViewGroup.LayoutParams layoutParams = listViewHolder.cardList.getLayoutParams();
                    layoutParams.height = getItemHeightofListView(listViewHolder.cardList,ChagebuttonsArray.length()); //this is in pixels
                    listViewHolder.cardList.setLayoutParams(layoutParams);
                }
            }

        } catch (Exception e) {
            Toast.makeText(context, e.toString(),
                    Toast.LENGTH_SHORT).show();


        }
        chat = new ChatActivity();

        //tempJSON
        viewHolder.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof ChatActivity) {
                    ((ChatActivity) context).sendMessage("", true, btnPayloadYes1,false,"","");
                }
            }
        });

        viewHolder.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof ChatActivity) {
                    ((ChatActivity) context).sendMessage("", true, btnPayloadNO1,false,"","");
                }
            }
        });

        return viewHolder;
    }



    // To calculate the total height of all items in ListView call with items = adapter.getCount()
    public static int getItemHeightofListView(GridView listView, int items) {
        ListAdapter adapter = listView.getAdapter();

        int grossElementHeight = 0;
        for (int i = 0; i < items; i++) {
            View childView = adapter.getView(i, null, listView);
            childView.measure(UNBOUNDED, UNBOUNDED);
            grossElementHeight += childView.getMeasuredHeight();
        }
        return grossElementHeight;
    }
}