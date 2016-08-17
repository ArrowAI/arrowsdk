package com.arrowai.chat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arrowai.chat.Activity.Chat;
import com.arrowai.chat.Activity.ChatActivity;
import com.arrowai.chat.Model.ButtonTemplate;
import com.arrowai.chat.Model.Card;
import com.arrowai.chat.Model.TransactionHistory;
import com.arrowai.chat.Model.confirmation_class;
import com.arrowai.chat.R;
import com.arrowai.chat.util.CommonUtil;

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
    TransactionHistory transactionList;
    private List<Chat> listStorage;
    private Context context;
    private CardListAdapter cardListAdapter;
    private ConfirmationAdapter confirmationAdapter;
    private ArrayList<ButtonTemplate> buttonList;
    private ButtonTemplate buttons;
    private confirmation_class confirmation;
    private ArrayList<com.arrowai.chat.Model.confirmation_class> confirmationList;
    private TransactionAdapter transactionAdapter;
    private ButtonTemplateAdapter buttonTemplateAdapter;
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
        String from="";
        if (listStorage.get(position).getFrom().toLowerCase()!=null) {
            from= listStorage.get(position).getFrom().toLowerCase();
        }
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
                    listViewHolder.btnNo = (Button) convertView.findViewById(R.id.btnNo);
                    listViewHolder = bindButtonEvents(listViewHolder, tempJSON);
                    convertView.setTag(listViewHolder);
                } else if (tempType.equals("search_list")) {
                    cardListArry = new ArrayList<>();
                    convertView = layoutinflater.inflate(R.layout.card_list_widget, parent, false);
                    listViewHolder.cardList = (GridView) convertView.findViewById(R.id.cardGrid);
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

        } else if (type.equals("list")) {
            bindDefaultEvents = false;
            convertView = layoutinflater.inflate(R.layout.list_options, parent, false);
            listViewHolder.listOptions = (ListView) convertView.findViewById(R.id.listOptions);
            listViewHolder = bindListEvents(listViewHolder, tempJSON, position);

        } else if (templateType != null && templateType.toLowerCase().equals("template")) {
            bindDefaultEvents = false;
            convertView = layoutinflater.inflate(R.layout.list_options, parent, false);
            listViewHolder.listOptions = (ListView) convertView.findViewById(R.id.listOptions);
            listViewHolder = bindListEvents(listViewHolder, tempJSON, position);
        } else if (from.equals(mUsername.toLowerCase())) {
            convertView = layoutinflater.inflate(R.layout.chat_message_right, parent, false);
        } else if (from.equals(mUsername.toLowerCase())) {
            convertView = layoutinflater.inflate(R.layout.chat_message_right, parent, false);
        } else {
            convertView = layoutinflater.inflate(R.layout.chat_message_left, parent, false);
        }
        if (bindDefaultEvents) {
            listViewHolder.textdate = (TextView) convertView.findViewById(R.id.textdate);
            listViewHolder.msgContainer = (LinearLayout) convertView.findViewById(R.id.Lbyother);
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

        }
        return convertView;
    }

    static class ViewHolder {
        TextView textdate;
        TextView textMessage;
        Button btnYes;
        Button btnNo;
        Button payBill;
        ListView listOptions;
        GridView cardList;
        RelativeLayout relativeLayout;
        TextView cardName, cardDescription;
        LinearLayout msgContainer;
    }
    private ViewHolder bindGridvents(ViewHolder viewHolder, JSONObject tempJSON, int position) {
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = tempJSON.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String cardName = jsonObject.getString("title");
                String cardImage = jsonObject.getString("image");
                String description = jsonObject.getString("description");
                cardList = new Card(cardName, cardImage, description);
                cardListArry.add(cardList);
            }
        } catch (Exception e) {
        }
        cardListAdapter = new CardListAdapter(context, cardListArry);
        ViewGroup.LayoutParams layoutParams = listViewHolder.cardList.getLayoutParams();
        layoutParams.height = jsonArray.length() * 170; //this is in pixels
        listViewHolder.cardList.setLayoutParams(layoutParams);
        listViewHolder.cardList.setAdapter(cardListAdapter);
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
                    ((ChatActivity) context).sendMessage(arryList.get(position), false, null);
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
                            confirmation = new confirmation_class(type, title, info, payload);
                            confirmationList.add(confirmation);
                        }

                    } catch (Exception e) {
                    }
                    ViewGroup.LayoutParams layoutParams = listViewHolder.listOptions.getLayoutParams();
                    layoutParams.height = ChagebuttonsArray.length() * 250; //this is in pixels
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
                        String title = payloadTemplate.getString("title");
                        JSONObject payload = payloadTemplate.getJSONObject("payload");
                        buttons = new ButtonTemplate(title, payload);
                        buttonList.add(buttons);
                    }

                } catch (Exception e) {
                }
                ViewGroup.LayoutParams layoutParams = listViewHolder.cardList.getLayoutParams();
                layoutParams.height = ChagebuttonsArray.length() * 120; //this is in pixels
                listViewHolder.cardList.setLayoutParams(layoutParams);
                buttonTemplateAdapter = new ButtonTemplateAdapter(context, buttonList);
                listViewHolder.cardList.setAdapter(buttonTemplateAdapter);
                buttonTemplateAdapter.notifyDataSetChanged();


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
                    ((ChatActivity) context).sendMessage("", true, btnPayloadYes1);
                }
            }
        });

        viewHolder.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof ChatActivity) {
                    ((ChatActivity) context).sendMessage("", true, btnPayloadNO1);
                }
            }
        });

        return viewHolder;
    }


}
