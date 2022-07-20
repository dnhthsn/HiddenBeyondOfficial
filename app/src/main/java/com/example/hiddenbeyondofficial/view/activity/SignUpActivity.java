package com.example.hiddenbeyondofficial.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.databinding.ActivitySignUpBinding;
import com.example.hiddenbeyondofficial.model.Users;
import com.example.hiddenbeyondofficial.control.Repository;
import com.example.hiddenbeyondofficial.util.Const;
import com.example.hiddenbeyondofficial.util.Utility;
import com.example.hiddenbeyondofficial.viewmodel.UserViewModel;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private UserViewModel userViewModel;

    public static void starter(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        userViewModel = new UserViewModel(this);

        binding.clickSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.inputName.getText().toString();
                String phone = binding.inputPhone.getText().toString();
                String password = binding.inputPassword.getText().toString();
                String address = binding.inputAddress.getText().toString();

                int genderGrID = binding.genderGroup.getCheckedRadioButtonId();
                RadioButton genderRad = findViewById(genderGrID);
                String gender = genderRad.getText().toString();

                userViewModel.addUser(name, phone, password, address, gender, view);
            }
        });
    }
}