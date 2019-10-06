package com.example.devicemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;

import com.example.devicemanagementsystem.Models.UserModel;
import com.example.devicemanagementsystem.Tasks.RegisterTask;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_fullname) TextInputLayout register_fullname;
    @BindView(R.id.register_email) TextInputLayout register_email;
    @BindView(R.id.register_username) TextInputLayout register_username;
    @BindView(R.id.register_password) TextInputLayout register_password;
    private UserModel userModel = new UserModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.register_submit)
    void submitClicked() {
        if(!check(register_fullname) | !check(register_email) |
                !check(register_username) | !check(register_password)) {
            return;
        }

        userModel.setFullname(register_fullname.getEditText().getText().toString().trim());
        userModel.setEmail(register_email.getEditText().getText().toString().trim());
        userModel.setUsername(register_username.getEditText().getText().toString().trim());
        userModel.setPassword(register_password.getEditText().getText().toString().trim());
        new RegisterTask(this, userModel).execute((Void)null);

    }

    private boolean check(TextInputLayout textInputLayout) {
        String value = textInputLayout.getEditText().getText().toString().trim();
        if(value.isEmpty()) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Cannot be empty");
            return false;
        } else {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
            return true;
        }
    }
}
