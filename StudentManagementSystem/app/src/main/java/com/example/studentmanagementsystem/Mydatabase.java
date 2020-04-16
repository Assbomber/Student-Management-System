package com.example.studentmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Mydatabase extends SQLiteOpenHelper {


    public Mydatabase(@Nullable Context context) {
        super(context, "Announcement_database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE announcements (_id INTEGER PRIMARY KEY AUTOINCREMENT,date TEXT NOT NULL,title TEXT NOT NULL,message TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS announcements");
        onCreate(db);
    }

    public boolean add(String date, String title, String message) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("date", date);
        cv.put("title", title);
        cv.put("message", message);
       long res= db.insert("announcements", null, cv);
       if(res==-1) return false;
       else return true;
    }

    public Cursor fetch() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM announcements ORDER BY _id DESC LIMIT 3", null);
        return cur;
    }
}
