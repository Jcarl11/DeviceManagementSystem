package com.example.devicemanagementsystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devicemanagementsystem.Models.Logs;
import com.example.devicemanagementsystem.R;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.LogsViewHolder> {

    private Context context;
    private List<Logs> logsList;

    public LogsAdapter(Context context, List<Logs> logsList) {
        this.context = context;
        this.logsList = logsList;
    }

    @NonNull
    @Override
    public LogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.logs_rowlayout, parent, false);
        return new LogsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogsViewHolder holder, int position) {
        Logs logs = logsList.get(position);
        holder.adapter_logs_username.setText(logs.getUserUsername());
        holder.adapter_logs_email.setText(logs.getUserEmail());
        holder.adapter_logs_devicename.setText(logs.getDeviceName());
        holder.adapter_logs_brand.setText(logs.getDeviceBrand());
        holder.adapter_logs_type.setText(logs.getDeviceType());
        holder.adapter_logs_department.setText(logs.getDeviceDepartment());
        holder.adapter_logs_status.setText(logs.getDeviceStatus());
        holder.adapter_logs_timestamp.setText(logs.getTimestamp());
    }

    @Override
    public int getItemCount() {
        return logsList.size();
    }

    public class LogsViewHolder extends RecyclerView.ViewHolder{

        TextView adapter_logs_username;
        TextView adapter_logs_email;
        TextView adapter_logs_devicename;
        TextView adapter_logs_brand;
        TextView adapter_logs_type;
        TextView adapter_logs_department;
        TextView adapter_logs_status;
        TextView adapter_logs_timestamp;


        public LogsViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_logs_username = (TextView) itemView.findViewById(R.id.adapter_logs_username);
            adapter_logs_email = (TextView) itemView.findViewById(R.id.adapter_logs_email);
            adapter_logs_devicename = (TextView) itemView.findViewById(R.id.adapter_logs_devicename);
            adapter_logs_brand = (TextView) itemView.findViewById(R.id.adapter_logs_brand);
            adapter_logs_type = (TextView) itemView.findViewById(R.id.adapter_logs_type);
            adapter_logs_department = (TextView) itemView.findViewById(R.id.adapter_logs_department);
            adapter_logs_status = (TextView) itemView.findViewById(R.id.adapter_logs_status);
            adapter_logs_timestamp = (TextView) itemView.findViewById(R.id.adapter_logs_timestamp);
        }
    }
}
