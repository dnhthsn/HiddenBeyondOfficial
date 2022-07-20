package com.example.hiddenbeyondofficial.viewmodel;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hiddenbeyondofficial.control.Repository;
import com.example.hiddenbeyondofficial.control.rest.Callback;
import com.example.hiddenbeyondofficial.model.Videos;

import java.util.List;

public class VideoViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<List<Videos>> movies = new MutableLiveData<>();

    public VideoViewModel(Context context) {
        this.repository = new Repository(context);
    }

    public MutableLiveData<List<Videos>> getVideos() {
        repository.getVideo(new Callback() {
            @Override
            public void getVideo(List<Videos> list) {
                super.getVideo(list);
                movies.setValue(list);
            }
        });
        return movies;
    }

    public void addVideo(Videos movies, View view){
        repository.addVideo(movies, view);
    }
}
