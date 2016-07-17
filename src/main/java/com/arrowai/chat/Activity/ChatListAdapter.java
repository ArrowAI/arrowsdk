package com.arrowai.chat.Activity;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.arrowai.chat.Adapter.FirebaseListAdapter;
import com.arrowai.chat.R;
import com.firebase.client.Query;
public class ChatListAdapter extends FirebaseListAdapter<Chat>
{
    private String mUsername;
    public ChatListAdapter(Query ref, Activity activity, int layout, String mUsername)
    {
        super(ref, Chat.class, layout, activity);
        this.mUsername = mUsername;
    }
    @Override
    protected void populateView(View view, Chat chat)
    {
        String author = chat.getFrom();
        TextView authorText = (TextView) view.findViewById(R.id.author);
        authorText.setText(author + ": ");
        authorText.setGravity(Gravity.RIGHT);
        if (author != null && author.equals(mUsername))
        {
            authorText.setTextColor(Color.RED);
        }
        else
        {
            authorText.setTextColor(Color.BLUE);
        }
        ((TextView) view.findViewById(R.id.message)).setText(chat.getMessage());
    }
}
