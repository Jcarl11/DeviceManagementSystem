package com.example.devicemanagementsystem.Utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import dmax.dialog.SpotsDialog;

public class Loading {
    private static final Loading ourInstance = new Loading();

    public static Loading getInstance() {
        return ourInstance;
    }

    private Loading() { }

    public AlertDialog showLoading(Context ctx) {
        AlertDialog dialog = new SpotsDialog.Builder()
                .setMessage("Please wait")
                .setContext(ctx)
                .setCancelable(true)
                .build();
        return dialog;
    }

    public void showAlertBox(String title, String message, Context context)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alert11 = builder.create();
        alert11.show();
    }
}
