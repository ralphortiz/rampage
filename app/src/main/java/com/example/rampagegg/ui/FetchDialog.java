package com.example.rampagegg.ui;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

import com.example.rampagegg.R;

public class FetchDialog {

    private final Activity activity;
    private AlertDialog dialog;

    public FetchDialog(Activity activity) {
        this.activity = activity;
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
    }

    public void dismissDialog() {
        dialog.dismiss();
    }
}
