package com.btpltech.bankingbot.SQliteData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

 public class SQLiteAdapter {

    public static final String DATABASE_NAME = "BOT_STORE_DB_0.0.12";
    public static final int    DATABASE_VERSION = 1;

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    private Context context;

    public SQLiteAdapter(Context c){
        context = c;
    }

    public SQLiteAdapter openToRead() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        return this;
    }

    public SQLiteAdapter openToWrite() throws android.database.SQLException {
        sqLiteHelper = new SQLiteHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        sqLiteHelper.close();
    }
    public long insertTableData(String Table_Name, ContentValues contentValues)
    {
        return sqLiteDatabase.insert(Table_Name, null, contentValues);
    }
    public long deleteTableData(String Table_Name)
    {
        return sqLiteDatabase.delete(Table_Name, null, null);
    }
     public long deleteTableData(String Table_Name,String ColumnName,String Val)
     {
         return sqLiteDatabase.delete(Table_Name, ColumnName + "=" + Val, null);
     }
     public long updateTableData(String Table_Name,ContentValues contentValues,String where)
     {
         return sqLiteDatabase.update(Table_Name, contentValues, where, null);
     }

     public Cursor getTableData(String Table_Name)
     {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT  * FROM " + Table_Name,null);
        return cursor;
    }
     public Cursor Query(String query){
         Cursor cursor=null;
         try {
             cursor= sqLiteDatabase.rawQuery(query, null);
         }
         catch (Exception e)
         {
             String s=e.getMessage().toString();
         }
         return cursor;
     }
     public Cursor getSelectedData(String Table_Name,String chatuser)
     {
         Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Table_Name +" WHERE chatuser = "+chatuser,null);
         return cursor;
     }


     public Cursor getSelectedSessionData(String Table_Name,String chatuser)
     {
         Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Table_Name +" WHERE chatuser = "+chatuser,null);
         return cursor;
     }
     public Cursor getSelectedChatListData(String Table_Name,String chatuser)
     {
         Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Table_Name +" WHERE chatuser = "+chatuser,null);
         return cursor;
     }
    public class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(Context context, String name,
                            CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(ChatListData.SCRIPT_CREATE_CLASSES_TABLE);
            db.execSQL(Bots.SCRIPT_CREATE_BOTS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

        }

    }

}
