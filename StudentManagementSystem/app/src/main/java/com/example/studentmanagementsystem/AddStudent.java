package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity {
    MyDatabaseHelper db;
    Toolbar toolbar;
    EditText StudentName, FatherName, RegistrationNo,PhoneNo;
    Spinner Section,Branch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        toolbar=findViewById(R.id.toolbar_addstudent);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Student");
        db=new MyDatabaseHelper(this);

        StudentName=findViewById(R.id.StudentName);
        RegistrationNo=findViewById(R.id.regno);
        FatherName=findViewById(R.id.FatherName);
        PhoneNo=findViewById(R.id.phoneno);
        Section=findViewById(R.id.Section_spinner);
        Branch=findViewById(R.id.Branch_spinner);

    }

    public void AddStudent_click(View v){
        String studentname=StudentName.getText().toString();
        String fathername=FatherName.getText().toString();
        String phoneno=PhoneNo.getText().toString();
        String regString=RegistrationNo.getText().toString();
        if(studentname.equals("") || fathername.equals("") || phoneno.equals("") || regString.equals("")) {
            Toast.makeText(this, "All Field are necessary", Toast.LENGTH_SHORT).show();
        }
        else {
            int id;
            try {
               id= Integer.parseInt(regString);
            }
            catch (Exception e){
                Toast.makeText(this, "Registration number must be Integer", Toast.LENGTH_SHORT).show();
                return;
            }

            String branch = Branch.getSelectedItem().toString();
            String section = Section.getSelectedItem().toString();
            boolean res = db.add(id, studentname, fathername, branch, section, phoneno);
            if (res == false)
                Toast.makeText(this, "Error Adding to Database", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Student Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
