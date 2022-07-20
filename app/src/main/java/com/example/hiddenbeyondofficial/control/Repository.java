package com.example.hiddenbeyondofficial.control;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.hiddenbeyondofficial.control.local.DatabaseVideo;
import com.example.hiddenbeyondofficial.control.rest.Callback;
import com.example.hiddenbeyondofficial.model.Admins;
import com.example.hiddenbeyondofficial.model.News;
import com.example.hiddenbeyondofficial.model.Users;
import com.example.hiddenbeyondofficial.model.Videos;
import com.example.hiddenbeyondofficial.util.Const;
import com.example.hiddenbeyondofficial.util.Utility;
import com.example.hiddenbeyondofficial.view.activity.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Repository {
    private DatabaseVideo databaseVideo;

    public Repository(Context context) {
        this.databaseVideo = new DatabaseVideo(context);
    }

    public void getVideo(Callback callback) {
        Cursor cursor = databaseVideo.getVideo();
        Videos videos;
        List<Videos> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String category = cursor.getString(1);
            String image = cursor.getString(2);
            String video = cursor.getString(3);
            videos = new Videos(name, category, image, video);
            list.add(videos);
        }
        callback.getVideo(list);
        cursor.moveToFirst();
        cursor.close();
    }

    public void getAdmin(Callback callback) {
        Cursor cursor = databaseVideo.getAdmin();
        Admins admins;
        List<Admins> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String password = cursor.getString(1);
            admins = new Admins(name, password);
            list.add(admins);
        }
        callback.getAdmin(list);
        cursor.moveToFirst();
        cursor.close();
    }

    public void getUser(Callback callback) {
        Cursor cursor = databaseVideo.getUser();
        Users users;
        List<Users> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String phone = cursor.getString(1);
            String password = cursor.getString(2);
            String address = cursor.getString(3);
            String gender = cursor.getString(4);
            users = new Users(name, phone, password, address, gender);
            list.add(users);
        }
        callback.getUser(list);
        cursor.moveToFirst();
        cursor.close();
    }

    public void getNews(Callback callback) {
        Cursor cursor = databaseVideo.getNews();
        News news;
        List<News> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            String image = cursor.getString(1);
            String content = cursor.getString(2);
            news = new News(name, image, content);
            list.add(news);
        }
        callback.getNew(list);
        cursor.moveToFirst();
        cursor.close();
    }

    public void addUser(Users users, View view) {
        if (!databaseVideo.checkUser(users.getName()) && !databaseVideo.checkPhoneNumber(users.getPhone())){
            databaseVideo.addUser(users);
            LoginActivity.starter(view.getContext());
        } else {
            Utility.Notice.snack(view, Const.Error.existed);
        }
    }

    public void addVideo(Videos videos, View view) {
        if (!databaseVideo.checkVideo(videos.getName())){
            databaseVideo.addVideo(videos);
            Utility.Notice.snack(view, Const.Success.created);
        } else {
            Utility.Notice.snack(view, Const.Error.existed);
        }
    }

    public void addNews(News news, View view) {
        if (!databaseVideo.checkNews(news.getName())){
            databaseVideo.addNews(news);
            Utility.Notice.snack(view, Const.Success.created);
        } else {
            Utility.Notice.snack(view, Const.Error.existed);
        }
    }

    public void updateUser(Users users) {
        if (databaseVideo.checkUser(users.getName())){
            databaseVideo.updateUser(users);
        }
    }

    public void updatePassword(Users users, View view) {
        if (!databaseVideo.checkUser(users.getName())){
            Utility.Notice.snack(view, Const.Error.notexisted);
        }
        else if (databaseVideo.checkUserAndPhone(users.getPhone(), users.getName())){
            databaseVideo.updateUserPassword(users);
            LoginActivity.starter(view.getContext());
        } else {
            Utility.Notice.snack(view, Const.Error.wrongPhone);
        }
    }
}
