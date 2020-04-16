package com.example.studentmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.net.URI;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    MyDatabaseHelper db;
    Mydatabase db2;
    AlertDialog alert;
    LinearLayout sliderdotspanel;
    private int dotscount=3;
    private ImageView [] dots;
    NavigationView navigationView;
    Cursor cur;
    public static ArrayList<Announcement> arrayList_announcement;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager viewPager;
    TextView changeStudent;
    ImageView adduser,edituser,deleteuser,showall,announcement,addfees;
    int totalstudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar=findViewById(R.id.home_toolbar);
        viewPager=findViewById(R.id.viewpager);
        drawerLayout=findViewById(R.id.drawer);
        db=new MyDatabaseHelper(this);
        db2=new Mydatabase(this);
        sliderdotspanel=(LinearLayout) findViewById(R.id.dotspanel);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DashBoard");
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.Open_nav_drawer,R.string.Close_nav_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        arrayList_announcement=new ArrayList<>();
        Async async=new Async();
        async.execute();

        dots=new ImageView[3];
        for (int i=0;i<3;i++){
            dots[i]=new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonactive));
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);
            sliderdotspanel.addView(dots[i],params);
        }
        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.activedot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i=0;i<3;i++){
                    dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonactive));
                }
                dots[position].setImageDrawable(getResources().getDrawable(R.drawable.activedot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationView=findViewById(R.id.navigation_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.add_student_nav:
                        Intent intent=new Intent(HomeActivity.this,AddStudent.class);
                        startActivity(intent);
                        break;
                    case R.id.delete_student_nav:
                        final AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
                        LayoutInflater inflater=getLayoutInflater();
                        final View b=inflater.inflate(R.layout.deletedialog,null);
                        builder.setView(b);
                        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText editText=b.findViewById(R.id.delete_registration);
                                String id=editText.getText().toString();
                                if(db.delete(id)==false)
                                    Toast.makeText(HomeActivity.this, "Error deleting", Toast.LENGTH_SHORT).show();
                                else Toast.makeText(HomeActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                alert.dismiss();
                            }
                        });
                        alert=builder.create();
                        builder.show();
                        break;
                    case R.id.update_student_nav:
                        Intent intent2=new Intent(HomeActivity.this,EditStudent.class);
                        startActivity(intent2);

                        break;
                    case R.id.message_nav:
                        Intent intent1=new Intent(Intent.ACTION_SENDTO);
                        intent1.setData(Uri.parse("mailto:amankumarproductions@gmail.com"));
                        startActivity(intent1);
                        break;
                    case R.id.call_nav:
                        Intent intent3=new Intent(Intent.ACTION_DIAL);
                        intent3.setData(Uri.parse("tel:9680555555"));
                        startActivity(intent3);
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else super.onBackPressed();
    }

    public void grid(View v){
        switch (v.getId()){

            case R.id.addstudent:
                Intent intent=new Intent(this,AddStudent.class);
                startActivity(intent);
                break;
            case R.id.editstudent:
                Intent intent2=new Intent(this,EditStudent.class);
                startActivity(intent2);
                break;
            case R.id.deletestudent:

                final AlertDialog.Builder builder=new AlertDialog.Builder(this);
                LayoutInflater inflater=getLayoutInflater();
                final View b=inflater.inflate(R.layout.deletedialog,null);
                builder.setView(b);
                builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText=b.findViewById(R.id.delete_registration);
                        String id=editText.getText().toString();
                        if(db.delete(id)==false)
                            Toast.makeText(HomeActivity.this, "Error deleting", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(HomeActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });
                alert=builder.create();
                builder.show();
                break;
            case R.id.showallstudents:
                Intent intent4=new Intent(this,ShowAll.class);
                startActivity(intent4);
                break;
            case R.id.createannoucement:
                Intent intent5=new Intent(this,CreateAnnouncement.class);
                startActivity(intent5);
                break;
            case R.id.addfees:
                AlertDialog.Builder builder1=new AlertDialog.Builder(this);
                LayoutInflater layoutInflater=getLayoutInflater();
                final View c=layoutInflater.inflate(R.layout.addfeeslayout,null);
                builder1.setView(c);
                builder1.setPositiveButton(R.string.Add_fee, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText reg=c.findViewById(R.id.fees_registration);
                        EditText fee=c.findViewById(R.id.fee);
                        if(db.addfees(reg.getText().toString(),fee.getText().toString())==false)
                            Toast.makeText(HomeActivity.this, "Error adding fee", Toast.LENGTH_SHORT).show();
                        else Toast.makeText(HomeActivity.this, "Fee Updated", Toast.LENGTH_SHORT).show();
                        alert.dismiss();
                    }
                });
                alert=builder1.create();
                builder1.show();
                break;
        }
    }

    public class Async extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Cursor cursor=db.showall();
            while (cursor.moveToNext()){
                totalstudent++;
            }

                if(db2.fetch().getCount()==3) {
                    cur=db2.fetch();
                    while (cur.moveToNext()) {
                        arrayList_announcement.add(new Announcement(cur.getString(1), cur.getString(2), cur.getString(3)));
                    }
                }
           else {

                arrayList_announcement.add((new Announcement("01//1/2021","Coronavirus","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, ")));
                arrayList_announcement.add((new Announcement("02//1/2021","Aman is good","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod temporLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, ")));
                arrayList_announcement.add((new Announcement("03//1/2021","LPU Failed","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor Lorem ipsum dolor sit amet,")));
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            changeStudent=findViewById(R.id.change_students);
            changeStudent.setText(totalstudent+"");

            viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(viewPagerAdapter);
        }
    }

}
