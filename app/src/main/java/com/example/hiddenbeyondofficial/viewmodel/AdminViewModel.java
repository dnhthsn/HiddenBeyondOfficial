package com.example.hiddenbeyondofficial.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.hiddenbeyondofficial.control.Repository;
import com.example.hiddenbeyondofficial.control.local.SharedPreference;
import com.example.hiddenbeyondofficial.control.rest.Callback;
import com.example.hiddenbeyondofficial.model.Admins;
import com.example.hiddenbeyondofficial.util.Const;
import com.example.hiddenbeyondofficial.view.activity.AddVideoActivity;
import com.example.hiddenbeyondofficial.view.activity.AdminActivity;

import java.util.List;

public class AdminViewModel extends ViewModel {
    private Repository repository;
    private SharedPreference sharedPreference;

    public AdminViewModel(Context context) {
        this.repository = new Repository();
        this.sharedPreference = new SharedPreference(context);
    }

    public void checkAdmin(String name, String password, Context context){
        repository.getAdmin(new Callback() {
            @Override
            public void getAdmin(List<Admins> list) {
                super.getAdmin(list);
                for (Admins admins : list){
                    if (admins.getName().equals(name) && admins.getPassword().equals(password)){
                        Toast.makeText(context, Const.Success.login, Toast.LENGTH_SHORT).show();
                        AdminActivity.starter(context);
                        break;
                    } else {
                        Toast.makeText(context, Const.Error.information, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
