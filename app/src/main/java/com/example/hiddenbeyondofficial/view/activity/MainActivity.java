package com.example.hiddenbeyondofficial.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.databinding.ActivityMainBinding;
import com.example.hiddenbeyondofficial.view.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private final int FRAGMENT_HOME_INDEX = 0;
    private final int FRAGMENT_VIDEO_INDEX = 1;
    private final int FRAGMENT_NEWS_INDEX = 2;
    private final int FRAGMENT_SEARCH_VIDEO_INDEX = 3;
    private final int FRAGMENT_SEARCH_NEWS_INDEX = 4;

    private ActivityMainBinding binding;

    public static void starter(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 3);
        binding.viewPager.setAdapter(viewPagerAdapter);
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case FRAGMENT_HOME_INDEX: binding.bottomNavigationView.getMenu().findItem(R.id.home_tab).setCheckable(true);
                        break;
                    case FRAGMENT_VIDEO_INDEX: binding.bottomNavigationView.getMenu().findItem(R.id.video_tab).setCheckable(true);
                        break;
                    case FRAGMENT_NEWS_INDEX: binding.bottomNavigationView.getMenu().findItem(R.id.news_tab).setCheckable(true);
                        break;
                    case FRAGMENT_SEARCH_VIDEO_INDEX: binding.bottomNavigationView.getMenu().findItem(R.id.search_video_tab).setCheckable(true);
                        break;
                    case FRAGMENT_SEARCH_NEWS_INDEX: binding.bottomNavigationView.getMenu().findItem(R.id.search_news_tab).setCheckable(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home_tab:
                        binding.viewPager.setCurrentItem(FRAGMENT_HOME_INDEX);
                        break;
                    case R.id.video_tab:
                        binding.viewPager.setCurrentItem(FRAGMENT_VIDEO_INDEX);
                        break;
                    case R.id.news_tab:
                        binding.viewPager.setCurrentItem(FRAGMENT_NEWS_INDEX);
                        break;
                    case R.id.search_video_tab:
                        binding.viewPager.setCurrentItem(FRAGMENT_SEARCH_VIDEO_INDEX);
                        break;
                    case R.id.search_news_tab:
                        binding.viewPager.setCurrentItem(FRAGMENT_SEARCH_NEWS_INDEX);
                        break;
                }
                return true;
            }
        });
    }
}