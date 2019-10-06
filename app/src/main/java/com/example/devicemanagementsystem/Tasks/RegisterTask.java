package com.example.devicemanagementsystem.Tasks;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.devicemanagementsystem.LoginActivity;
import com.example.devicemanagementsystem.Models.UserModel;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.example.devicemanagementsystem.Utilities.Loading;
import com.parse.ParseException;
import com.parse.ParseUser;

public class RegisterTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "RegisterTask";
    private Context context;
    private UserModel userModel;
    private AlertDialog dialog;

    public RegisterTask(Context context, UserModel userModel) {
        this.context = context;
        this.userModel = userModel;
        dialog = Loading.getInstance().showLoading(context);
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        ParseUser parseUser = new ParseUser();
        parseUser.setEmail(userModel.getEmail());
        parseUser.setUsername(userModel.getUsername());
        parseUser.setPassword(userModel.getPassword());
        try {
            parseUser.signUp();
            result = GlobalConstants.LOGIN_SUCCESS;
        } catch (ParseException e) {
            result = GlobalConstants.LOGIN_FAILED;
            Log.d(TAG, "doInBackground: Exception: " + e.getMessage());
        }
        return result;
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.dismiss();
        if(s == GlobalConstants.LOGIN_SUCCESS) {
            Loading.getInstance().showAlertBox("Result", "Account Registered", context);
            context.startActivity(new Intent(context, LoginActivity.class));
        } else {
            Loading.getInstance().showAlertBox("Result", "Operation failed, Please try again", context);
        }
    }
}
