package com.uddesh.tiffinserviceappforadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.uddesh.tiffinserviceappforadmin.R;

public class AddServiceActivity extends AppCompatActivity {
    private TextView tab_textview;
    private ImageView delete_service_imageview;
    private EditText service_name_edittext,service_description_edittext,price_edittext;
    private Button confirm_button,update_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        initialiseComponents();
        clickListeners();

    }

    // private functions

    private void initialiseComponents()
    {
        tab_textview = findViewById(R.id.tab_textview);
        delete_service_imageview = findViewById(R.id.delete_service_imageview);
        service_name_edittext = findViewById(R.id.service_name_edittext);
        service_description_edittext = findViewById(R.id.service_description_edittext);
        price_edittext = findViewById(R.id.price_edittext);
        confirm_button = findViewById(R.id.confirm_button);
        update_button = findViewById(R.id.update_button);
    }

    private void clickListeners()
    {
        confirm_button.setOnClickListener(v -> {

        });

        update_button.setOnClickListener(v -> {

        });
    }
}
