package com.example.devicemanagementsystem.Tasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.devicemanagementsystem.LoginActivity;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.example.devicemanagementsystem.Utilities.Loading;
import com.parse.ParseUser;

public class LogoutTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private AlertDialog dialog;

    public LogoutTask(Context context) {
        this.context = context;
        dialog = Loading.getInstance().showLoading(context);
    }

    @Override
    protected String doInBackground(Void... voids) {
        ParseUser.logOut();
        return GlobalConstants.LOGIN_SUCCESS;
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.dismiss();
        if(s == GlobalConstants.LOGIN_SUCCESS) {
            context.startActivity(new Intent(context, LoginActivity.class));
            ((Activity) context).finish();
        } else {
            Loading.getInstance().showAlertBox("Result", "Logout failed, Please try again", context);
        }
    }
}
