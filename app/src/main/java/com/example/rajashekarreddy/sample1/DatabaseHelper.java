package com.example.rajashekarreddy.sample1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "smsPlus.db";
    public static final String TABLE_NAME="blacklist";
    public static final String col_1="PHN";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, PHN VARCHAR2 NOT NULL ); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("drop table if exists "+ TABLE_NAME);
        onCreate(db);

    }
    public  boolean insertData(String number){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_1,number);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }

    public Cursor getAllData(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from "+TABLE_NAME,null);
        return res;
    }

    public void removeItems(List<String> bItems){

        for (String s:bItems
             ) {
            SQLiteDatabase db = this.getWritableDatabase();
             db.execSQL("delete from "+TABLE_NAME+" where PHN = "+s);
        }


    }

}
