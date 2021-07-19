package com.uddesh.tiffinserviceappforadmin.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesHelper {
    private final Context context;

    public SharedPreferencesHelper(Context context) {
        this.context = context;
    }

    public String getSharedPreferences(String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPreferences" , Context.MODE_APPEND);
        return sharedPreferences.getString(key , "");
    }
    public void setSharedPreferences(String key , String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPreferences" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key , value);
        editor.apply();
    }
}
