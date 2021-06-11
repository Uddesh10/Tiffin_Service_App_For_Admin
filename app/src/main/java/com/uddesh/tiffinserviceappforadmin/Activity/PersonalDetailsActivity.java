package com.uddesh.tiffinserviceappforadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uddesh.tiffinserviceappforadmin.R;

public class PersonalDetailsActivity extends AppCompatActivity {
    CheckBox dinner_checkbox,veg_checkbox,non_veg_checkbox,lunch_checkbox;
    EditText service_provider_name_edittext,contact_number_edittext, upi_edittext,editTextTime,editTextTime2,editTextTime3,editTextTime4;
    LinearLayout linearLayout;
    TextView textView,textView2;
    Button confirm_button,update_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        dinner_checkbox = findViewById(R.id.dinner_checkBox);
        veg_checkbox = findViewById(R.id.veg_checkBox);
        non_veg_checkbox = findViewById(R.id.non_veg_checkbox);
        lunch_checkbox = findViewById(R.id.lunch_checkBox);
        service_provider_name_edittext = findViewById(R.id.service_provider_name_edittext);
        contact_number_edittext = findViewById(R.id.contact_number_edittext);
        upi_edittext = findViewById(R.id.upi_edittext);
        editTextTime = findViewById(R.id.editTextTime);
        editTextTime2 = findViewById(R.id.editTextTime2);
        editTextTime3 = findViewById(R.id.editTextTime3);
        editTextTime4 = findViewById(R.id.editTextTime4);
        linearLayout = findViewById(R.id.linearLayout);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        confirm_button = findViewById(R.id.confirm_button);
        update_button = findViewById(R.id.update_button);


        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }
}
