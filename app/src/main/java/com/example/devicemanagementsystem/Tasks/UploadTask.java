package com.example.devicemanagementsystem.Tasks;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.devicemanagementsystem.DB.DevicesDAO;
import com.example.devicemanagementsystem.DB.IOperations;
import com.example.devicemanagementsystem.Models.Device;
import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.example.devicemanagementsystem.Utilities.Loading;

public class UploadTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private Device device;
    private IOperations<Device> deviceDAO = new DevicesDAO();
    private AlertDialog dialog;

    public UploadTask(Context context, Device device) {
        this.context = context;
        this.device = device;
        dialog = Loading.getInstance().showLoading(context);
    }

    @Override
    protected String doInBackground(Void... voids) {
        return deviceDAO.insert(device) != null ?
                GlobalConstants.LOGIN_SUCCESS :
                GlobalConstants.LOGIN_FAILED;
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        dialog.dismiss();
        if(s == GlobalConstants.LOGIN_SUCCESS) {
            Loading.getInstance().showAlertBox("Result", "Record added successfully", context);
        } else {
            Loading.getInstance().showAlertBox("Result", "Operation Failed, Please try again", context);
        }
    }
}
