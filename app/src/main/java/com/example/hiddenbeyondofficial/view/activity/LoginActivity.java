package com.example.hiddenbeyondofficial.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.control.local.SharedPreference;
import com.example.hiddenbeyondofficial.control.rest.Callback;
import com.example.hiddenbeyondofficial.control.Repository;
import com.example.hiddenbeyondofficial.databinding.ActivityLoginBinding;
import com.example.hiddenbeyondofficial.model.Users;
import com.example.hiddenbeyondofficial.util.Const;
import com.example.hiddenbeyondofficial.util.Utility;
import com.example.hiddenbeyondofficial.viewmodel.UserViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private SharedPreference sharedPreference;

    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;

    public static void starter(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreference = new SharedPreference(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        userViewModel = new UserViewModel(this);

        binding.loginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginAdminActivity.starter(LoginActivity.this);
            }
        });

        binding.clickLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.inputName.getText().toString();
                String password = binding.inputPassword.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Utility.Notice.snack(view, Const.Error.name);
                } else if (TextUtils.isEmpty(password)) {
                    Utility.Notice.snack(view, Const.Error.password);
                } else {
                    if (binding.rememberUser.isChecked()) {
                        sharedPreference.saveUser(name, password);
                    } else {
                        sharedPreference.removeUser();
                    }

                    userViewModel.checkUser(name, password, LoginActivity.this);
                    binding.wrongInfo.setText(userViewModel.getMessage());
                }
            }
        });

        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPasswordActivity.starter(LoginActivity.this);
            }
        });

        Users users = new Users();
        sharedPreference.getUser(users);

        String name = users.getName();
        String password = users.getPassword();

        binding.inputName.setText(name);
        binding.inputPassword.setText(password);

        binding.createAccount.setOnClickListener(view -> {
            SignUpActivity.starter(LoginActivity.this);
        });
    }

    @Override
    public void onBackPressed() {
    }
}