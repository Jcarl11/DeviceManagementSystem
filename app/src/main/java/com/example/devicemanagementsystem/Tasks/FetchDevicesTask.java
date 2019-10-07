package com.example.devicemanagementsystem.Tasks;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.devicemanagementsystem.Adapters.DevicesAdapter;
import com.example.devicemanagementsystem.DB.DevicesDAO;
import com.example.devicemanagementsystem.DB.IOperations;
import com.example.devicemanagementsystem.Models.Device;
import com.example.devicemanagementsystem.Utilities.Loading;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class FetchDevicesTask extends AsyncTask<Void, Void, List<Device>> {

    private Context context;
    private RecyclerView recyclerView;
    private AlertDialog dialog;
    private DevicesAdapter devicesAdapter;
    private IOperations<Device> deviceIOperations = new DevicesDAO();

    public FetchDevicesTask(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        dialog = Loading.getInstance().showLoading(context);
    }

    @Override
    protected List<Device> doInBackground(Void... voids) {
        return deviceIOperations.getAll();
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected void onPostExecute(List<Device> devices) {
        dialog.dismiss();
        if(devices.size() == 0) {
            Loading.getInstance().showAlertBox("Result", "No Records found", context);
            return;
        }
        devicesAdapter = new DevicesAdapter(context, devices);
        recyclerView.setAdapter(devicesAdapter);

    }
}
