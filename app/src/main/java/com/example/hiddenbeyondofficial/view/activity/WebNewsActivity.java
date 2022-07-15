package com.example.hiddenbeyondofficial.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.databinding.ActivityWebNewsBinding;
import com.example.hiddenbeyondofficial.util.Const;

public class WebNewsActivity extends AppCompatActivity {
    private ActivityWebNewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_news);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_news);

        Intent intent = getIntent();
        String content = intent.getStringExtra(Const.Sender.contentUrl);

        binding.newsWeb.setWebViewClient(new WebViewClient());
        binding.newsWeb.loadUrl(content);

        WebSettings webSettings = binding.newsWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.newsWeb.canGoBack()) {
                    binding.newsWeb.goBack();
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (binding.newsWeb.canGoBack()) {
            binding.newsWeb.goBack();
        } else {
            super.onBackPressed();
        }
    }
}