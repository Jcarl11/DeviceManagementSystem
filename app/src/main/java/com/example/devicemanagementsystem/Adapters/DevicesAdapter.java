package com.example.devicemanagementsystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devicemanagementsystem.Models.Device;
import com.example.devicemanagementsystem.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.DevicesViewHolder> {
    private Context context;
    private List<Device> deviceList;

    public DevicesAdapter(Context context, List<Device> deviceList) {
        this.context = context;
        this.deviceList = deviceList;
    }

    @NonNull
    @Override
    public DevicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.devices_rowlayout, parent, false);
        return new DevicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DevicesViewHolder holder, int position) {
        Device device = deviceList.get(position);
        holder.adapter_device_objectid.setText(device.getObjectId());
        holder.adapter_device_name.setText(device.getDeviceName());
        holder.adapter_device_brand.setText(device.getDeviceBrand());
        holder.adapter_device_type.setText(device.getDeviceType());
        holder.adapter_device_department.setText(device.getDepartment());
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }

    public class DevicesViewHolder extends RecyclerView.ViewHolder{

        TextView adapter_device_objectid;
        TextView adapter_device_name;
        TextView adapter_device_brand;
        TextView adapter_device_type;
        TextView adapter_device_department;

        public DevicesViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_device_objectid = (TextView)itemView.findViewById(R.id.adapter_device_objectid);
            adapter_device_name = (TextView)itemView.findViewById(R.id.adapter_device_name);
            adapter_device_brand = (TextView)itemView.findViewById(R.id.adapter_device_brand);
            adapter_device_type = (TextView)itemView.findViewById(R.id.adapter_device_type);
            adapter_device_department = (TextView)itemView.findViewById(R.id.adapter_device_department);
        }
    }
}
