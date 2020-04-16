package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditStudent extends AppCompatActivity {

    MyDatabaseHelper db;
    Toolbar toolbar;
    EditText Reg,name,father,phone;
    TextView txtsection,txtname,txtfather,txtphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        toolbar=findViewById(R.id.toolbar_editstudent);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Student");
        Reg=findViewById(R.id.edit_registrationNo);
        name=findViewById(R.id.newstudentname);
        father=findViewById(R.id.newFathername);
        phone=findViewById(R.id.newPhoneno);
        txtname=findViewById(R.id.changeName);
        txtsection=findViewById(R.id.changeSection);
        txtfather=findViewById(R.id.changeFather);
        txtphone=findViewById(R.id.changePhone);
        db=new MyDatabaseHelper(this);

    }

    public void fetchdetails(View v){
        int regno=0;
        try {
            regno = Integer.parseInt(Reg.getText().toString());
        }
        catch (Exception e){
            Toast.makeText(this, "Registration number must be Integer", Toast.LENGTH_SHORT).show();
        }
        Cursor cur=db.fetch(regno);
        if(cur.getCount()>0) {
            cur.moveToNext();
                txtname.setText(cur.getString(1));
                txtfather.setText(cur.getString(2));
                txtphone.setText(cur.getString(5));
                txtsection.setText(cur.getString(4));

        }
        else Toast.makeText(this, "No such Registration number found", Toast.LENGTH_SHORT).show();
    }

    public void updatestudent(View v){
        int regno=0;
        try {
            regno = Integer.parseInt(Reg.getText().toString());
        }
        catch (Exception e){
            Toast.makeText(this, "Registration number must be Integer", Toast.LENGTH_SHORT).show();
        }
       boolean res= db.update(regno,name.getText().toString(),father.getText().toString(),phone.getText().toString());
        if(res==false) Toast.makeText(this, "Not able to update", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Update successful", Toast.LENGTH_SHORT).show();
    }
}
