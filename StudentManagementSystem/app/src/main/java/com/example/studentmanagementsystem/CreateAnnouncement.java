package com.example.studentmanagementsystem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAnnouncement extends AppCompatActivity {
    Toolbar toolbar;
    Mydatabase db;
    EditText title,date,message;
    String tit,dat,mes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_announcement);
        toolbar=findViewById(R.id.toolbar_createannouncement);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Announcement");
        title=findViewById(R.id.AnnouncementTitle);
        date=findViewById(R.id.AnnouncementDate);
        message=findViewById(R.id.AnnouncementMessage);
        db=new Mydatabase(this);
    }
    public void addAnnouncement(View v){
        tit=title.getText().toString();
        dat=date.getText().toString();
        mes=message.getText().toString();
        if(db.add(dat,tit,mes)==false) Toast.makeText(this, "Error creating Announcement", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Announcement created", Toast.LENGTH_SHORT).show();

    }

}
