package com.arrowai.chat.SQliteData;

import android.content.ContentValues;
import android.database.Cursor;


public class Account {

    private SQLiteAdapter sqlLiteAdapter;

    public static final String TABLE_ACCOUNT = "Account";
    public static final String _id = "_id";
    public static final String customer_name = "name";
    public static final String account_no = "account no";
    public static final String bank_name ="bank name";
    public static final String ifsc="ifsc";
    public static final String email="email";
    public static final String amount="amount";

    public static final String SCRIPT_CREATE_ACCOUNT_TABLE =
            "create table " + TABLE_ACCOUNT + " ("
                    + _id + " integer primary key autoincrement, "
                    + customer_name + " text null, "
                    + account_no + " text null,"
                    + bank_name + " text null,"
                    + ifsc + " text null" + amount + " text null" + email + "text null" + ")";
    public Account(SQLiteAdapter sqlLiteAda)
    {
        sqlLiteAdapter=sqlLiteAda;
    }
    public long insertAccount(String customer_name,String account_no,String bank_name,String ifsc, String email,String amount)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(customer_name, customer_name);
        contentValues.put(account_no, account_no);
        contentValues.put(bank_name, bank_name);
        contentValues.put(ifsc, ifsc);
        contentValues.put(amount, amount);
        contentValues.put(email, email);
        return sqlLiteAdapter.insertTableData(TABLE_ACCOUNT, contentValues);
    }

    public long deleteAccountData(){
        return sqlLiteAdapter.deleteTableData(TABLE_ACCOUNT);
    }
    public Cursor getAllAccountData()
    {
        return sqlLiteAdapter.getTableData(TABLE_ACCOUNT);
    }
}
