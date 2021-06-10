package com.uddesh.tiffinserviceappforadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.uddesh.tiffinserviceappforadmin.Helpers.GenericTextWatcher;
import com.uddesh.tiffinserviceappforadmin.R;

public class MainActivity extends AppCompatActivity {
    ImageView logo_image_imageview;
    Button sign_up_button, login_button, signup_up_form_signup_button, login_form_login_button, otp_verify_button;
    TextView didnt_receive_code_textview , app_name_text_view, or_text_view, login_form_signup_textview ,sign_up_form_already_have_account_textview, sign_up_form_login_textview, otp_mobile_textview , resend_code_textview , login_form_dont_have_account_textview;
    EditText signup_form_user_name_edit_text, sign_up_form_mobile_edit_text, signup_form_password_edit_text, login_form_username_edittext, login_form_password_edittext , otp_edittext1 ,otp_edittext4,otp_edittext5,otp_edittext2, otp_edittext3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponent();
        clickListeners();
    }

    // private functions
    private void initializeComponent()
    {
        logo_image_imageview = findViewById(R.id.logo_image_view);
        app_name_text_view = findViewById(R.id.app_name_text_view);
        sign_up_button = findViewById(R.id.sign_up_button);
        login_button = findViewById(R.id.login_button);
        or_text_view = findViewById(R.id.or_text_view);
        signup_form_user_name_edit_text = findViewById(R.id.signup_form_user_name_edit_text);
        sign_up_form_mobile_edit_text = findViewById(R.id.sign_up_form_mobile_edit_text);
        signup_form_password_edit_text = findViewById(R.id.signup_form_password_edit_text);
        signup_up_form_signup_button = findViewById(R.id.signup_up_form_signup_button);
        sign_up_form_already_have_account_textview = findViewById(R.id.sign_up_form_already_have_account_textview);
        sign_up_form_login_textview = findViewById(R.id.sign_up_form_login_textview);
        login_form_username_edittext = findViewById(R.id.login_form_username_edittext);
        login_form_login_button = findViewById(R.id.login_form_login_button);
        login_form_dont_have_account_textview = findViewById(R.id.login_form_dont_have_account_textview);
        resend_code_textview = findViewById(R.id.resend_code_textview);
        login_form_signup_textview = findViewById(R.id.login_form_signup_textview);
        login_form_password_edittext = findViewById(R.id.login_form_password_edittext);
        otp_mobile_textview = findViewById(R.id.otp_mobile_textview);
        otp_edittext1 = findViewById(R.id.otp_edittext1);
        otp_edittext4 = findViewById(R.id.otp_edittext4);
        otp_edittext5 = findViewById(R.id.otp_edittext5);
        otp_edittext2 = findViewById(R.id.otp_edittext2);
        otp_edittext3 = findViewById(R.id.otp_edittext3);
        otp_verify_button = findViewById(R.id.otp_verify_button);
        didnt_receive_code_textview = findViewById(R.id.didnt_receive_code_textview);
        EditText[] otpEditText = {otp_edittext4 , otp_edittext1 , otp_edittext2 , otp_edittext3 , otp_edittext5};
        otp_edittext1.addTextChangedListener(new GenericTextWatcher(otp_edittext1 , otpEditText));
        otp_edittext2.addTextChangedListener(new GenericTextWatcher(otp_edittext2, otpEditText));
        otp_edittext3.addTextChangedListener(new GenericTextWatcher(otp_edittext3, otpEditText));
        otp_edittext4.addTextChangedListener(new GenericTextWatcher(otp_edittext4, otpEditText));
        otp_edittext5.addTextChangedListener(new GenericTextWatcher(otp_edittext5, otpEditText));
    }

    private void clickListeners()
    {
        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateStartButtons();
                animateSignUpForm(1 , View.VISIBLE);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateStartButtons();
                animateLoginForm(1 , View.VISIBLE);
            }
        });

        signup_up_form_signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateSignUpForm(0 , View.GONE);
                animateOtpForm();
            }
        });

        login_form_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateLoginForm(0 , View.GONE);
                animateOtpForm();
            }
        });

        otp_verify_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setAddressActivityIntent = new Intent(MainActivity.this , SetAddressActivity.class);
                startActivity(setAddressActivityIntent);
                finish();
            }
        });

        sign_up_form_login_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateSignUpForm(0 , View.GONE);
                animateLoginForm(1 , View.VISIBLE);
            }
        });

        login_form_signup_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateLoginForm(0 , View.GONE);
                animateSignUpForm(1 , View.VISIBLE);
            }
        });

        resend_code_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    private void animateSignUpForm(int alpha , int visibility)
    {
        signup_form_user_name_edit_text.setVisibility(visibility);
        signup_form_password_edit_text.setVisibility(visibility);
        sign_up_form_mobile_edit_text.setVisibility(visibility);
        sign_up_form_already_have_account_textview.setVisibility(visibility);
        sign_up_form_login_textview.setVisibility(visibility);
        signup_up_form_signup_button.setVisibility(visibility);
        signup_form_user_name_edit_text.animate().alpha(alpha).setDuration(500);
        signup_form_password_edit_text.animate().alpha(alpha).setDuration(500);
        sign_up_form_mobile_edit_text.animate().alpha(alpha).setDuration(500);
        sign_up_form_already_have_account_textview.animate().alpha(alpha).setDuration(500);
        sign_up_form_login_textview.animate().alpha(alpha).setDuration(500);
        signup_up_form_signup_button.animate().alpha(alpha).setDuration(500);
    }

    private void animateLoginForm(int alpha , int visibility)
    {
        login_form_login_button.setVisibility(visibility);
        login_form_signup_textview.setVisibility(visibility);
        login_form_password_edittext.setVisibility(visibility);
        login_form_username_edittext.setVisibility(visibility);
        login_form_dont_have_account_textview.setVisibility(visibility);
        login_form_username_edittext.animate().alpha(alpha).setDuration(500);
        login_form_password_edittext.animate().alpha(alpha).setDuration(500);
        login_form_signup_textview.animate().alpha(alpha).setDuration(500);
        login_form_login_button.animate().alpha(alpha).setDuration(500);
        login_form_dont_have_account_textview.animate().alpha(alpha).setDuration(500);
    }

    private void animateStartButtons()
    {
        sign_up_button.animate().alpha(0).setDuration(500);
        or_text_view.animate().alpha(0).setDuration(500);
        login_button.animate().alpha(0).setDuration(500);
        sign_up_button.setVisibility(View.GONE);
        or_text_view.setVisibility(View.GONE);
        login_button.setVisibility(View.GONE);
    }

    private void animateOtpForm()
    {
        otp_edittext1.setVisibility(View.VISIBLE);
        otp_edittext5.setVisibility(View.VISIBLE);
        otp_edittext4.setVisibility(View.VISIBLE);
        otp_edittext3.setVisibility(View.VISIBLE);
        otp_edittext2.setVisibility(View.VISIBLE);
        otp_mobile_textview.setVisibility(View.VISIBLE);
        otp_verify_button.setVisibility(View.VISIBLE);
        resend_code_textview.setVisibility(View.VISIBLE);
        didnt_receive_code_textview.setVisibility(View.VISIBLE);
        otp_edittext1.animate().alpha(1).setDuration(500);
        otp_edittext2.animate().alpha(1).setDuration(500);
        otp_edittext3.animate().alpha(1).setDuration(500);
        otp_edittext4.animate().alpha(1).setDuration(500);
        otp_edittext5.animate().alpha(1).setDuration(500);
        otp_mobile_textview.animate().alpha(1).setDuration(500);
        resend_code_textview.animate().alpha(1).setDuration(500);
        otp_verify_button.animate().alpha(1).setDuration(500);
        didnt_receive_code_textview.animate().alpha(1).setDuration(500);
    }
}