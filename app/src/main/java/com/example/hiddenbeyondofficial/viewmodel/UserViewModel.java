package com.example.hiddenbeyondofficial.viewmodel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.control.Repository;
import com.example.hiddenbeyondofficial.control.local.SharedPreference;
import com.example.hiddenbeyondofficial.control.rest.Callback;
import com.example.hiddenbeyondofficial.model.Users;
import com.example.hiddenbeyondofficial.util.Const;
import com.example.hiddenbeyondofficial.view.activity.LoginActivity;
import com.example.hiddenbeyondofficial.view.activity.MainActivity;

import java.io.IOException;
import java.util.List;

public class UserViewModel extends ViewModel {
    private Repository repository;
    private SharedPreference sharedPreference;
    private String message;

    public UserViewModel(Context context) {
        this.repository = new Repository();
        sharedPreference = new SharedPreference(context);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void addUser(Users users, View view) {
        repository.addUser(users, view);
    }

    public void updateUser(Users users){
        repository.updateUser(users);
    }

    public void updatePassword(String name, String password, View view, Context context){
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.timo);
        mediaPlayer.start();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(LayoutInflater.from(context).inflate(R.layout.dialog, null));
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Users users = new Users();
                users.setName(name);
                users.setPassword(password);

                repository.updatePassword(users, view);
                LoginActivity.starter(context);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void checkUser(String name, String password, Context context) {
        repository.getUser(new Callback() {
            @Override
            public void getUser(List<Users> list) {
                super.getUser(list);
                for (Users user : list){
                    if (user.getName().equals(name) && user.getPassword().equals(password)){
                        sharedPreference.saveCurrentUser(user);
                        MainActivity.starter(context);
                        setMessage("");
                        break;
                    } else {
                        setMessage(Const.Error.information);
                    }
                }
            }
        });
    }
}
