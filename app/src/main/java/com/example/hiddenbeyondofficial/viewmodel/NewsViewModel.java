package com.example.hiddenbeyondofficial.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hiddenbeyondofficial.control.Repository;
import com.example.hiddenbeyondofficial.control.rest.Callback;
import com.example.hiddenbeyondofficial.model.News;
import com.example.hiddenbeyondofficial.model.Videos;

import java.util.List;

public class NewsViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<List<News>> news = new MutableLiveData<>();

    public NewsViewModel() {
        this.repository = new Repository();
    }

    public MutableLiveData<List<News>> getNews() {
        repository.getNews(new Callback() {
            @Override
            public void getNew(List<News> list) {
                super.getNew(list);
                news.setValue(list);
            }
        });
        return news;
    }

    public void addNews(News news, View view){
        repository.addNews(news, view);
    }
}
