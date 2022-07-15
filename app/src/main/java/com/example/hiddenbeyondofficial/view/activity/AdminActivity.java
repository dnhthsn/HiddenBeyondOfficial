package com.example.hiddenbeyondofficial.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {
    private ActivityAdminBinding binding;

    public static void starter(Context context) {
        Intent intent = new Intent(context, AdminActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin);

        binding.addNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewsActivity.starter(AdminActivity.this);
            }
        });

        binding.addMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddVideoActivity.starter(AdminActivity.this);
            }
        });
    }
}