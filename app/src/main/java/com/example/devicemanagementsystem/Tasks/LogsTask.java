package com.example.devicemanagementsystem.Tasks;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.devicemanagementsystem.DB.IOperations;
import com.example.devicemanagementsystem.DB.LogsDAO;
import com.example.devicemanagementsystem.Models.Logs;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.example.devicemanagementsystem.Utilities.Loading;

public class LogsTask extends AsyncTask<Void, Void, String> {
    private Context context;
    private Logs logs;
    private IOperations<Logs> logsIOperations = new LogsDAO();
    private AlertDialog dialog;

    public LogsTask(Context context, Logs logs) {
        this.context = context;
        this.logs = logs;
        dialog = Loading.getInstance().showLoading(context);
    }

    @Override
    protected String doInBackground(Void... voids) {
        return logsIOperations.insert(logs) != null ?
                GlobalConstants.LOGIN_SUCCESS : GlobalConstants.LOGIN_FAILED;
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.dismiss();
        if(s == GlobalConstants.LOGIN_SUCCESS) {
            Loading.getInstance().showAlertBox("Result", "Record logged successfully", context);
        } else if(s == GlobalConstants.LOGIN_FAILED){
            Loading.getInstance().showAlertBox("Result", "Operation Failed, Please try again", context);
        }
    }
}
