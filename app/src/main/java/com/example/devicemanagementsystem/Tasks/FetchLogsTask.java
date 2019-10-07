package com.example.devicemanagementsystem.Tasks;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.devicemanagementsystem.Adapters.LogsAdapter;
import com.example.devicemanagementsystem.DB.IOperations;
import com.example.devicemanagementsystem.DB.LogsDAO;
import com.example.devicemanagementsystem.Models.Logs;
import com.example.devicemanagementsystem.Utilities.Loading;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class FetchLogsTask extends AsyncTask<Void, Void, List<Logs>> {
    private static final String TAG = "FetchLogsTask";
    private Context context;
    private RecyclerView recyclerView;
    private IOperations<Logs> logsIOperations = new LogsDAO();
    private AlertDialog dialog;
    private LogsAdapter logsAdapter;

    public FetchLogsTask(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        dialog = Loading.getInstance().showLoading(context);
    }

    @Override
    protected List<Logs> doInBackground(Void... voids) {
        return logsIOperations.getAll();
    }

    @Override
    protected void onPreExecute() {dialog.show();}

    @Override
    protected void onPostExecute(List<Logs> logs) {
        dialog.dismiss();
        Log.d(TAG, "onPostExecute: Size: " + logs.size());
        if(logs.size() == 0) {
            Loading.getInstance().showAlertBox("Result", "No records found", context);
            return;
        }
        logsAdapter = new LogsAdapter(context, logs);
        recyclerView.setAdapter(logsAdapter);
    }


}
