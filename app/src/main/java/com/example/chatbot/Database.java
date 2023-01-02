package com.example.chatbot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static String chatbot="chatbot";
    public static String messageCode="messageCode";
    public static String myMessage="message";
    public static String[] data = {"messageCode", "message"};


    public Database(@Nullable Context context) {
        super(context, "myChatBot", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+chatbot+ "(messageCode VARCHAR ,message VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + chatbot);
    }


    public void insertMessage(int message_code,String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(messageCode,message_code);
        contentValues.put(myMessage,message);
        db.insert(chatbot,null,contentValues);
    }

    public void deleteTable(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+chatbot);

    }

}
