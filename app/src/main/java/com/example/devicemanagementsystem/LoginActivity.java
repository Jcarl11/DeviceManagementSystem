package com.example.devicemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.devicemanagementsystem.Tasks.LoginTask;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.login_username) TextInputLayout login_username;
    @BindView(R.id.login_password) TextInputLayout login_password;
    @BindView(R.id.login_submit) Button login_submit;
    @BindView(R.id.login_register) Button login_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_submit)
    void submitClicked() {
        String username = login_username.getEditText().getText().toString();
        String password = login_password.getEditText().getText().toString();


        if(!check(login_username) | !check(login_password)) {
            return;
        }
        new LoginTask(LoginActivity.this, username, password).execute((Void)null);
    }

    @OnClick(R.id.login_register)
    void registerClicked() {

    }

    private boolean check(TextInputLayout textInputLayout) {
        String value = textInputLayout.getEditText().getText().toString().trim();
        if(value.isEmpty()) {
            textInputLayout.setError("Field cannot be empty");
            return false;
        } else {
            textInputLayout.setError(null);
            return true;
        }
    }
}
