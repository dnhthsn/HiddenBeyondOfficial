package com.example.hiddenbeyondofficial.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.hiddenbeyondofficial.view.fragment.FragmentHome;
import com.example.hiddenbeyondofficial.view.fragment.FragmentNews;
import com.example.hiddenbeyondofficial.view.fragment.FragmentSearchNews;
import com.example.hiddenbeyondofficial.view.fragment.FragmentSearchVideo;
import com.example.hiddenbeyondofficial.view.fragment.FragmentVideo;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int pageNum;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNum = 5;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new FragmentVideo();
            case 2:
                return new FragmentNews();
            case 3:
                return new FragmentSearchVideo();
            case 4:
                return new FragmentSearchNews();
            default:
                return new FragmentHome();
        }
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}
