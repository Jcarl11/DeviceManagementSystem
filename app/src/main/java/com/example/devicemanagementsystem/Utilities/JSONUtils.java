package com.example.devicemanagementsystem.Utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class JSONUtils {
    private static final String TAG = "JSONUtils";
    private JSONObject source = null;

    public JSONUtils() {
        source = new JSONObject();
    }

    public HashMap<String, String> parseData(JSONObject source) {
        this.source = source;
        HashMap<String, String> result = new HashMap<>();
        try {
            result.put("DEVICE_ID", source.getString("DEVICE_ID"));
            result.put("DEVICE_NAME", source.getString("DEVICE_NAME"));
            result.put("DEVICE_BRAND", source.getString("DEVICE_BRAND"));
            result.put("DEVICE_TYPE", source.getString("DEVICE_TYPE"));
            result.put("DEVICE_IN", source.getString("DEVICE_IN"));
            result.put("DEVICE_OUT", source.getString("DEVICE_OUT"));
            result.put("DEVICE_DEPARTMENT", source.getString("DEVICE_DEPARTMENT"));
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    public String generateJSON(HashMap<String, String> mySource) {
        String stringFormat = new String();
        try {
            stringFormat = new JSONObject(mySource).toString(2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stringFormat;
    }

    public JSONObject getSource() {
        return source;
    }

    public void setSource(JSONObject source) {
        this.source = source;
    }
}
