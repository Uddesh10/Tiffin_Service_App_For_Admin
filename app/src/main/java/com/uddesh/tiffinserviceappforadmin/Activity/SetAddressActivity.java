package com.uddesh.tiffinserviceappforadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.uddesh.tiffinserviceappforadmin.R;

public class SetAddressActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_address);
        Intent intent = new Intent(this , HomePageActivity.class);
        startActivity(intent);
    }
}