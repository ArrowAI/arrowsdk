package com.arrowai.chat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arrowai.chat.Activity.Chat;
import com.arrowai.chat.Activity.ChatActivity;
import com.arrowai.chat.Model.ButtonTemplate;
import com.arrowai.chat.Model.Card;
import com.arrowai.chat.Model.Confirmation;
import com.arrowai.chat.Model.Crausal;
import com.arrowai.chat.Model.TransactionHistory;
import com.arrowai.chat.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravinder on 6/1/2016.
 */
public class CarausalAdapter extends ArrayAdapter<Crausal> {
    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView home;
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

    private LayoutInflater layoutinflater;
    ViewHolder listViewHolder;
    Card cardList;
    ArrayList<Card> cardListArry;
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
    String mUsername;

    public CarausalAdapter(Context context, ArrayList<Crausal> users) {
        super(context, R.layout.carausal_item, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Crausal card = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.credit_card_list, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.Title);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(card.getCardName());

        // Return the completed view to render on screen
        return convertView;
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
               // cardList = new Card(cardName, cardImage, description);
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


    JSONArray jsonArray = new JSONArray();


    JSONObject btnPayloadYes1;
    JSONObject btnPayloadNO1;
    ChatActivity chat;


}