package com.btpltech.bankingbot.SQliteData;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by rajmendra on 22/05/16.
 */
public class Bots {

    private SQLiteAdapter sqlLiteAdapter;

    public static final String TABLE_BOTS = "Bots";
    public static final String _id = "_id";
    public static final String bot_id = "bot_id";
    public static final String bot_name = "bot_name";
    public static final String bot_desc = "bot_desc";
    public static final String bot_img="bot_img";

    public static final String SCRIPT_CREATE_BOTS_TABLE =
            "create table " + TABLE_BOTS + " ("
                    + _id + " integer primary key autoincrement, "
                    + bot_id + " text null, "
                    + bot_name + " text null,"
                    + bot_desc + " text null,"
                    + bot_img + " text null);";

    public Bots(SQLiteAdapter sqlLiteAda)
    {
        sqlLiteAdapter=sqlLiteAda;
    }


    public long insertBot(String botId,String botName,String botDesc,String botImg)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(bot_id, botId);
        contentValues.put(bot_name, botName);
        contentValues.put(bot_desc, botDesc);
        return sqlLiteAdapter.insertTableData(TABLE_BOTS, contentValues);
    }

    public long deleteChatListData(){
        return sqlLiteAdapter.deleteTableData(TABLE_BOTS);
    }
    public Cursor getAllChatListData()
    {
        return sqlLiteAdapter.getTableData(TABLE_BOTS);
    }
}
