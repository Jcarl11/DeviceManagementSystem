package com.example.devicemanagementsystem.Tasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.devicemanagementsystem.MainActivity;
import com.example.devicemanagementsystem.Models.UserModel;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.example.devicemanagementsystem.Utilities.Loading;
import com.parse.ParseException;
import com.parse.ParseUser;

import dmax.dialog.SpotsDialog;

public class LoginTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = "LoginTask";
    private Context context;
    private UserModel userModel;
    AlertDialog dialog;

    public LoginTask(Context context, UserModel userModel) {
        this.context = context;
        this.userModel = userModel;
        dialog = Loading.getInstance().showLoading(context);
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            ParseUser user = ParseUser.logIn(userModel.getUsername(), userModel.getPassword());
            if(user != null) {
                Log.d(TAG, "doInBackground: Logged in!");
                return GlobalConstants.LOGIN_SUCCESS;
            } else {
                Log.d(TAG, "doInBackground: Login Failed!");
                return GlobalConstants.LOGIN_FAILED;
            }
        } catch (ParseException e) {
            Log.d(TAG, "doInBackground: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.dismiss();
        if(s == GlobalConstants.LOGIN_FAILED) {
            Loading.getInstance().showAlertBox("Result", "Wrong credentials", context);
            return;
        } else if(s == GlobalConstants.LOGIN_SUCCESS) {
            Log.d(TAG, "onPostExecute: Logged in");
            context.startActivity(new Intent(context, MainActivity.class));
        }

    }
}
