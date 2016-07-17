package com.arrowai.chat.SQliteData;

import android.content.ContentValues;
import android.database.Cursor;

public class ChatListData
{
    private SQLiteAdapter sqlLiteAdapter;
    public static final String TABLE_NAME = "Chat";
    public static final String _id = "_id";
    public static final String chat_id = "chat_id";
    public static final String chatuser = "chatuser";
    public static final String chatbot="chatbot";
    public static final String text= "text";
    public static final String from= "fromUser";
    public static final String time= "time";
    public static final String SCRIPT_CREATE_CLASSES_TABLE =
            "create table " + TABLE_NAME + " ("
                    + _id + " integer primary key autoincrement, "
                    + chat_id + " text null, "
                    + chatuser + " text null,"
                    + chatbot + " text null,"
                    + text + " text null,"
                    + from + " text null,"
                    + time + " text null);";



    public ChatListData(SQLiteAdapter sqlLiteAda)
    {
        sqlLiteAdapter=sqlLiteAda;
    }

    public long insertChatListData(String chatuserval,String chatbotval,String textval, String fromval, String timeval)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(chatuser, chatuserval);
        contentValues.put(chatbot, chatbotval);
        contentValues.put(text, textval);
        contentValues.put(from, fromval);
        contentValues.put(time, timeval);
        return sqlLiteAdapter.insertTableData(TABLE_NAME, contentValues);
    }

    public long deleteChatListData(){
        return sqlLiteAdapter.deleteTableData(TABLE_NAME);
    }
    public Cursor getAllChatListData()
    {
        return sqlLiteAdapter.getTableData(TABLE_NAME);
    }
}
