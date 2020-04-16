package com.example.studentmanagementsystem;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        AnnouncementFrag announcementFrag=new AnnouncementFrag();
        Bundle bundle=new Bundle();
        bundle.putInt("position",position);
        announcementFrag.setArguments(bundle);
        return announcementFrag;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
