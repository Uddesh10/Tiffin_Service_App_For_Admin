package com.uddesh.tiffinserviceappforadmin.Helpers;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper {
    Context context;

    public ToastHelper(Context context) {
        this.context = context;
    }

    public void makeToast(String msg , int length) {
        Toast.makeText(context, msg , length).show();
    }
}
