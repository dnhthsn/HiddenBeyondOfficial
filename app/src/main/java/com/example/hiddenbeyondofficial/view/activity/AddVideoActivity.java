package com.example.hiddenbeyondofficial.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.control.Repository;
import com.example.hiddenbeyondofficial.databinding.ActivityAddVideoBinding;
import com.example.hiddenbeyondofficial.model.Videos;
import com.example.hiddenbeyondofficial.viewmodel.VideoViewModel;

public class AddVideoActivity extends AppCompatActivity {
    private ActivityAddVideoBinding binding;
    private VideoViewModel videoViewModel;

    public static void starter(Context context) {
        Intent intent = new Intent(context, AddVideoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_video);
        videoViewModel = new VideoViewModel();

        binding.addVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.inputVideoName.getText().toString();
                String image = binding.inputVideoImage.getText().toString();
                String video = binding.inputVideo.getText().toString();
                String category = binding.videoCategory.getSelectedItem().toString();

                Videos videos = new Videos(name, image, video, category);
                videoViewModel.addVideo(videos, view);

                binding.inputVideoName.setText("");
                binding.inputVideoImage.setText("");
                binding.inputVideo.setText("");
                binding.videoCategory.setSelection(0);
            }
        });

        binding.logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}