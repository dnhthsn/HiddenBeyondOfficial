package com.example.hiddenbeyondofficial.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.control.local.SharedPreference;
import com.example.hiddenbeyondofficial.databinding.ActivityInformationBinding;
import com.example.hiddenbeyondofficial.model.Users;
import com.example.hiddenbeyondofficial.util.Const;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

public class InformationActivity extends AppCompatActivity {
    private SharedPreference sharedPreference;
    private ActivityInformationBinding binding;

    public static void starter(Context context) {
        Intent intent = new Intent(context, InformationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_information);

        sharedPreference = new SharedPreference(this);

        Users users = sharedPreference.getCurrentUser();

        String uName = users.getName();
        String uPass = users.getPassword();
        String uPhone = users.getPhone();
        String uAddress = users.getAddress();
        String uGender = users.getGender();

        binding.userName.setText(uName);
        binding.password.setText("Password: " + uPass);
        binding.phoneNumber.setText(uPhone);
        binding.address.setText("Address: " + uAddress);
        binding.gender.setText("Gender: " + uGender);

        binding.clickBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.editInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(Const.Sender.name, uName);
                bundle.putString(Const.Sender.password, uPass);
                bundle.putString(Const.Sender.phone, uPhone);
                bundle.putString(Const.Sender.address, uAddress);
                bundle.putString(Const.Sender.gender, uGender);
                EditInformationActivity.starter(InformationActivity.this, bundle);
            }
        });

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null){
            String name = account.getDisplayName();
            String mail = account.getEmail();

            Glide.with(this).load(account.getPhotoUrl()).into(binding.avatar);
            binding.userName.setText(name);
            binding.password.setText("");
            binding.phoneNumber.setText("Email: " + mail);
            binding.address.setText("");
            binding.gender.setText("");

            binding.editInfor.setVisibility(View.INVISIBLE);
            binding.password.setVisibility(View.INVISIBLE);
            binding.address.setVisibility(View.INVISIBLE);
            binding.gender.setVisibility(View.INVISIBLE);
        }
    }
}