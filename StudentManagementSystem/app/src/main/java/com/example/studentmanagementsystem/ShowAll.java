package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;

public class ShowAll extends AppCompatActivity {
    MyDatabaseHelper db;
    public static ArrayList<item> arrayList;
    MyAdapter myAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        arrayList=new ArrayList<>();
        db=new MyDatabaseHelper(this);
        AsyncTask asyncTask=new AsyncTask();
        asyncTask.execute();

    }

    public class AsyncTask extends android.os.AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            Cursor cur=db.showall();
            while (cur.moveToNext()){
                arrayList.add(new item(cur.getString(0),cur.getString(1),cur.getString(4),cur.getString(5),cur.getString(6)));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            myAdapter=new MyAdapter(arrayList);
            layoutManager=new LinearLayoutManager(ShowAll.this);
            recyclerView=findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myAdapter);
        }
    }
}
