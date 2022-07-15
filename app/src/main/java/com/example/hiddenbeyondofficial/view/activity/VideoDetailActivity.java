package com.example.hiddenbeyondofficial.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.databinding.ActivityVideoDetailBinding;
import com.example.hiddenbeyondofficial.util.Const;

public class VideoDetailActivity extends AppCompatActivity {
    private String mName, mImage, mVideo;

    private ActivityVideoDetailBinding binding;

    public static void starter(Context context, Bundle bundle) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_detail);

        mName = getIntent().getStringExtra(Const.Sender.videoName);
        mImage = getIntent().getStringExtra(Const.Sender.videoImageUrl);
        mVideo = getIntent().getStringExtra(Const.Sender.videoUrl);

        Glide.with(this).load(mImage).into(binding.movieImage);
        binding.movieName.setText(mName);

        binding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVideoActivity.starter(VideoDetailActivity.this, mVideo);
            }
        });

    }
}