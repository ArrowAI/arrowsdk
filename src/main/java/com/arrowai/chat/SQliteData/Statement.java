package com.arrowai.chat.SQliteData;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Nitin on 5/31/2016.
 */
public class Statement {


    private SQLiteAdapter sqlLiteAdapter;

    public static final String TABLE_STATEMENT = "statement";
    public static final String _id = "_id";
    public static final String account_no = "account";
    public static final String amount = "amount";
    public static final String date = "date";
    public static final String user_name ="username";
    public static final String email ="email";
    public static final String reason ="reason";



    public static final String SCRIPT_CREATE_ACCOUNT_TABLE =
            "create table " + TABLE_STATEMENT + " ("
                    + _id + " integer primary key autoincrement, "
                    + account_no + " text null, "
                    + amount + " text null,"
                    + date + " text null,"+
                    user_name + "text null" +
                    email + "text null" +
                    reason + "text null" +")";

    public Statement(SQLiteAdapter sqlLiteAda)
    {
        sqlLiteAdapter=sqlLiteAda;
    }


    public long insertTransaction(String account_no,String amount, String date, String user_name,String email, String reason)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(account_no, account_no);
        contentValues.put(amount, amount);
        contentValues.put(date, date);
        contentValues.put(user_name, user_name);
        contentValues.put(email, email);
        contentValues.put(reason, reason);
        return sqlLiteAdapter.insertTableData(TABLE_STATEMENT, contentValues);
    }

    public long deleteTransactionData(){
        return sqlLiteAdapter.deleteTableData(TABLE_STATEMENT);
    }
    public Cursor getAllTransactionData()
    {
        return sqlLiteAdapter.getTableData(TABLE_STATEMENT);
    }
}
