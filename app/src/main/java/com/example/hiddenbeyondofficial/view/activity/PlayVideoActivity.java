package com.example.hiddenbeyondofficial.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.databinding.ActivityPlayVideoBinding;
import com.example.hiddenbeyondofficial.databinding.ActivityPlayVideoBindingImpl;
import com.example.hiddenbeyondofficial.util.Const;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    private final String API_KEY = "AIzaSyCkDZJy0HAUw0-6or1uyzxYXa5xh3yS_M4";

    private ActivityPlayVideoBinding binding;

    public static void starter(Context context, String url) {
        Intent intent = new Intent(context, PlayVideoActivity.class);
        intent.putExtra(Const.Sender.url, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_play_video);
        binding.playVideo.initialize(API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        String key = getIntent().getStringExtra(Const.Sender.url);
        youTubePlayer.cueVideo(key);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(PlayVideoActivity.this, 123);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123){
            binding.playVideo.initialize(API_KEY, PlayVideoActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}