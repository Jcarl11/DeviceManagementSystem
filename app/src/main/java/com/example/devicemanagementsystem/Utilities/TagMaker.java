package com.example.devicemanagementsystem.Utilities;

import org.json.JSONArray;
import java.util.List;

public class TagMaker {
    private static final String TAG = "TagMaker";

    public JSONArray fromList(List<String> source) {
        JSONArray jsonArray = new JSONArray();
        for (String values : source) {
            jsonArray.put(values);
        }
        return jsonArray;
    }
}
