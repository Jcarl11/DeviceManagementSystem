package com.example.devicemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.ganfra.materialspinner.MaterialSpinner;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.devicemanagementsystem.Utilities.GlobalConstants;
import com.example.devicemanagementsystem.Utilities.JSONUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;

public class GenerateActivity extends AppCompatActivity {

    JSONUtils jsonUtils = new JSONUtils();
    @BindView(R.id.generate_devicename) TextInputLayout generate_devicename;
    @BindView(R.id.generate_devicebrand) TextInputLayout generate_devicebrand;
    @BindView(R.id.generate_generateddata) TextInputLayout generate_generateddata;
    @BindView(R.id.generate_devicetype) MaterialSpinner generate_devicetype;
    @BindView(R.id.generate_devicedepartment) MaterialSpinner generate_devicedepartment;
    @BindView(R.id.generate_button) Button generate_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        ButterKnife.bind(this);
        ArrayAdapter<String> deviceTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, GlobalConstants.deviceTypes);
        deviceTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> deviceDepartmentAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, GlobalConstants.departmentsList);
        deviceDepartmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        generate_devicetype.setAdapter(deviceTypeAdapter);
        generate_devicedepartment.setAdapter(deviceDepartmentAdapter);

    }

    @OnClick(R.id.generate_button)
    void generateClicked() {
        HashMap<String, String> data = new HashMap<>();
        data.put(GlobalConstants.DEVICE_NAME, generate_devicename.getEditText().getText().toString());
        data.put(GlobalConstants.DEVICE_BRAND, generate_devicebrand.getEditText().getText().toString());
        data.put(GlobalConstants.DEVICE_TYPE, generate_devicetype.getSelectedItem().toString());
        data.put(GlobalConstants.DEVICE_DEPARTMENT, generate_devicedepartment.getSelectedItem().toString());
        String jsonString = new JSONUtils().generateJSON(data);
        generate_generateddata.setVisibility(View.VISIBLE);
        generate_generateddata.getEditText().setText(jsonString);
    }
}
