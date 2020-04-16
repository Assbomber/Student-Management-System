package com.example.studentmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Student_database.db";
    private static final String TABLE_NAME="Student_table";
    private static final String REGISTRATION_ID="Registration_Number";
    private static final String STUDENT_NAME="Student_Name";
    private static final String FATHER_NAME="Father_Name";
    private static final String BRANCH="Branch";
    private static final String SECTION="Section";
    private static final String PHONE_NO="Phone_Number";



    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" (Registration_Number INTEGER PRIMARY KEY NOT NULL, Student_Name TEXT NOT NULL, Father_Name TEXT NOT NULL, Branch TEXT NOT NULL, Section TEXT NOT NULL, Phone_Number TEXT NOT NULL, Fees INTEGER DEFAULT 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }

    public  boolean add(int regno,String student_name,String father_name,String branch,String section,String phone_no){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(REGISTRATION_ID,regno);
        cv.put(STUDENT_NAME,student_name);
        cv.put(FATHER_NAME,father_name);
        cv.put(BRANCH,branch);
        cv.put(SECTION,section);
        cv.put(PHONE_NO,phone_no);
        long ret=db.insert(TABLE_NAME,null,cv);
        if(ret==-1) return false;
        else return true;
    }

    public Cursor fetch(int regno){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE Registration_Number="+regno,null);
        return cur;
    }
    public boolean update(int regno,String name,String father,String phone){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(STUDENT_NAME,name);
        cv.put(FATHER_NAME,father);
        cv.put(PHONE_NO,phone);
       int rows= db.update(TABLE_NAME,cv,"Registration_Number=?",new String []{ String.valueOf(regno) });
       if(rows==0) return false;
       else return true;
    }

    public boolean delete(String id){
        SQLiteDatabase db=getWritableDatabase();
        int res=db.delete(TABLE_NAME,"Registration_Number=?",new String []{ id });
        if(res==0) return false;
        else return true;
    }

    public Cursor showall(){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cur;
    }

    public boolean addfees(String reg,String fee){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Fees",fee);
       int rows= db.update(TABLE_NAME,cv,"Registration_Number=?",new String [] { reg });
       if(rows==0) return false;
       else return true;
    }

}
