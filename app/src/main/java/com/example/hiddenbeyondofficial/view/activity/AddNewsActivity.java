package com.example.hiddenbeyondofficial.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.databinding.ActivityAddNewsBinding;
import com.example.hiddenbeyondofficial.databinding.ActivityAddVideoBinding;
import com.example.hiddenbeyondofficial.model.News;
import com.example.hiddenbeyondofficial.model.Videos;
import com.example.hiddenbeyondofficial.viewmodel.NewsViewModel;
import com.example.hiddenbeyondofficial.viewmodel.VideoViewModel;

public class AddNewsActivity extends AppCompatActivity {
    private ActivityAddNewsBinding binding;
    private NewsViewModel newsViewModel;

    public static void starter(Context context) {
        Intent intent = new Intent(context, AddNewsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_news);
        newsViewModel = new NewsViewModel(getApplicationContext());

        binding.addNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.inputNewsTitle.getText().toString();
                String image = binding.inputNewsImage.getText().toString();
                String content = binding.inputNewsContent.getText().toString();

                News news = new News(name, image, content);
                newsViewModel.addNews(news, view);

                binding.inputNewsImage.setText("");
                binding.inputNewsContent.setText("");
                binding.inputNewsTitle.setText("");
            }
        });

        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivity.starter(AddNewsActivity.this);
                finish();
            }
        });
    }
}