package com.example.hiddenbeyondofficial.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {
    private Animation uptoDown, downtoUp;

    private ActivityStartBinding binding;

    public static void starter(Context context) {
        Intent intent = new Intent(context, StartActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_start);

        uptoDown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoUp = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        binding.upside.setAnimation(uptoDown);
        binding.downside.setAnimation(downtoUp);

        binding.loginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.starter(StartActivity.this);
            }
        });

        binding.signupActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpActivity.starter(StartActivity.this);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}