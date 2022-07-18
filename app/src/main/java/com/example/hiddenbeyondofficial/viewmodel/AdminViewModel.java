package com.example.hiddenbeyondofficial.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.hiddenbeyondofficial.control.Repository;
import com.example.hiddenbeyondofficial.control.local.SharedPreference;
import com.example.hiddenbeyondofficial.control.rest.Callback;
import com.example.hiddenbeyondofficial.model.Admins;
import com.example.hiddenbeyondofficial.util.Const;
import com.example.hiddenbeyondofficial.util.Utility;
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

    public void checkAdmin(String name, String password, View view){
        if (TextUtils.isEmpty(name)) {
            Utility.Notice.snack(view, Const.Error.name);
        } else if (TextUtils.isEmpty(password)) {
            Utility.Notice.snack(view, Const.Error.password);
        } else {
            repository.getAdmin(new Callback() {
                @Override
                public void getAdmin(List<Admins> list) {
                    super.getAdmin(list);
                    for (Admins admins : list){
                        if (admins.getName().equals(name) && admins.getPassword().equals(password)){
                            Utility.Notice.snack(view, Const.Success.login);
                            AdminActivity.starter(view.getContext());
                            break;
                        } else {
                            Utility.Notice.snack(view, Const.Error.information);
                        }
                    }
                }
            });
        }
    }
}
