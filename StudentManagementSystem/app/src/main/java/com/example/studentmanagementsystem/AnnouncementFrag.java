package com.example.studentmanagementsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AnnouncementFrag extends Fragment {

    TextView title,date,des;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_announcement, container, false);
        title=v.findViewById(R.id.change_title);
        date=v.findViewById(R.id.change_date);
        des=v.findViewById(R.id.change_descrip);
        Announcement announcement=HomeActivity.arrayList_announcement.get(getArguments().getInt("position"));
        title.setText(announcement.getTitle());
        date.setText(announcement.getDate());
        des.setText(announcement.getMessage());
        return v;
    }
}
