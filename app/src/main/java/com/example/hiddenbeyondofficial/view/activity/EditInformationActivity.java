package com.example.hiddenbeyondofficial.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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

import com.example.hiddenbeyondofficial.R;
import com.example.hiddenbeyondofficial.control.Repository;
import com.example.hiddenbeyondofficial.databinding.ActivityEditInformationBinding;
import com.example.hiddenbeyondofficial.model.Users;
import com.example.hiddenbeyondofficial.util.Const;
import com.example.hiddenbeyondofficial.util.Utility;
import com.example.hiddenbeyondofficial.viewmodel.UserViewModel;

public class EditInformationActivity extends AppCompatActivity {
    private ActivityEditInformationBinding binding;
    private UserViewModel userViewModel;

    public static void starter(Context context, Bundle bundle) {
        Intent intent = new Intent(context, EditInformationActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_information);
        userViewModel = new UserViewModel(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            binding.inputName.setText(bundle.getString(Const.Sender.name));
            binding.inputPassword.setText(bundle.getString(Const.Sender.password));
            binding.inputPhone.setText(bundle.getString(Const.Sender.phone));
            binding.inputAddress.setText(bundle.getString(Const.Sender.address));
        }

        binding.clickBack.setOnClickListener(view -> finish());

        binding.clickUpdate.setOnClickListener(view -> {
            String name = binding.inputName.getText().toString();
            String phone = binding.inputPhone.getText().toString();
            String password = binding.inputPassword.getText().toString();
            String address = binding.inputAddress.getText().toString();

            int genderGrID = binding.genderGroup.getCheckedRadioButtonId();
            RadioButton genderRad = findViewById(genderGrID);
            String gender = genderRad.getText().toString();
            userViewModel.updateUser(name, phone, password, address, gender, view);
        });
    }
}