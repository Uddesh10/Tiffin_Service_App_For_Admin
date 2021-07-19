package com.uddesh.tiffinserviceappforadmin.Activity;

import android.os.CountDownTimer;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.uddesh.tiffinserviceappforadmin.DataModels.LoginModel;
import com.uddesh.tiffinserviceappforadmin.DataModels.SignupModel;
import com.uddesh.tiffinserviceappforadmin.Helpers.GenericTextWatcher;
import com.uddesh.tiffinserviceappforadmin.Helpers.OtpVerification;
import com.uddesh.tiffinserviceappforadmin.Helpers.SharedPreferencesHelper;
import com.uddesh.tiffinserviceappforadmin.Helpers.ToastHelper;
import com.uddesh.tiffinserviceappforadmin.R;
import com.uddesh.tiffinserviceappforadmin.Repository.RetrofitViewModel;

public class MainActivity extends AppCompatActivity {
    private Button sign_up_button, login_button, signup_up_form_signup_button, login_form_login_button, otp_verify_button;
    private TextView didnt_receive_code_textview , or_text_view, login_form_signup_textview ,sign_up_form_already_have_account_textview, sign_up_form_login_textview, otp_mobile_textview , resend_code_textview , login_form_dont_have_account_textview;
    private EditText signup_form_user_name_edit_text, sign_up_form_mobile_edit_text, signup_form_password_edit_text, login_form_username_edittext, login_form_password_edittext , otp_edittext1 ,otp_edittext4,otp_edittext5,otp_edittext2, otp_edittext3 , otp_edittext6;
    private SharedPreferencesHelper sharedPreferences;
    private ToastHelper toast;
    private RetrofitViewModel viewModel;
    private OtpVerification otpVerification;
    private String username, password , mobileNo;
    private boolean loginFromLoginPage;
    private String otpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = new SharedPreferencesHelper(this);
        viewModel = new RetrofitViewModel(getApplication());
        if(sharedPreferences.getSharedPreferences("loggedIn").equals("true"))
        {
            String username = sharedPreferences.getSharedPreferences("username");
            String password = sharedPreferences.getSharedPreferences("password");
            viewModel.adminLogin(new LoginModel(username , password)).observe(this , result-> sharedPreferences.setSharedPreferences("authToken" , result.getToken()));
            Intent intent = new Intent(this , HomePageActivity.class);
            startActivity(intent);
            finish();
        }
        setContentView(R.layout.activity_main);
        grantPermission();
        initializeComponent();
        clickListeners();
    }



    // private functions
    private void initializeComponent()
    {
        toast = new ToastHelper(this);
        otpVerification = new OtpVerification(this);
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
        otp_edittext6 = findViewById(R.id.otp_edittext6);
        otp_verify_button = findViewById(R.id.otp_verify_button);
        didnt_receive_code_textview = findViewById(R.id.didnt_receive_code_textview);
        EditText[] otpEditText = {otp_edittext4 , otp_edittext1 , otp_edittext2 , otp_edittext3 , otp_edittext5 , otp_edittext6};
        otp_edittext1.addTextChangedListener(new GenericTextWatcher(otp_edittext1 , otpEditText));
        otp_edittext2.addTextChangedListener(new GenericTextWatcher(otp_edittext2, otpEditText));
        otp_edittext3.addTextChangedListener(new GenericTextWatcher(otp_edittext3, otpEditText));
        otp_edittext4.addTextChangedListener(new GenericTextWatcher(otp_edittext4, otpEditText));
        otp_edittext5.addTextChangedListener(new GenericTextWatcher(otp_edittext5, otpEditText));
        otp_edittext6.addTextChangedListener(new GenericTextWatcher(otp_edittext6, otpEditText));
    }

    private void clickListeners()
    {
        sign_up_button.setOnClickListener(v -> {
            animateStartButtons();
            animateSignUpForm(1 , View.VISIBLE);
        });

        login_button.setOnClickListener(v -> {
            animateStartButtons();
            animateLoginForm(1 , View.VISIBLE);
        });

        signup_up_form_signup_button.setOnClickListener(v -> {
            username = signup_form_user_name_edit_text.getText().toString();
            password = signup_form_password_edit_text.getText().toString();
            mobileNo = sign_up_form_mobile_edit_text.getText().toString();
            if(username.equals("") || password.equals("") || mobileNo.length()!=10)
            {
                toast.makeToast("Check entered credentials" , Toast.LENGTH_LONG);
            }
            else {
                viewModel.adminSignup(new SignupModel(username , password , mobileNo)).observe(this , result->{
                    if(result)
                    {
                        otpText = "Please enter the verification code\n sent to "+mobileNo;
                        animateSignUpForm(0 , View.GONE);
                        animateOtpForm();
                        otpVerification.sendVerificationCode(mobileNo);
                    }
                    else{
                        toast.makeToast("Username already taken" , Toast.LENGTH_LONG);
                    }
                });
            }
        });

        login_form_login_button.setOnClickListener(v -> {
            username = login_form_username_edittext.getText().toString();
            password = login_form_password_edittext.getText().toString();
            viewModel.adminLogin(new LoginModel(username , password)).observe(this , result->{
                if(result==null)
                {
                    toast.makeToast("Invalid credentials" , Toast.LENGTH_LONG);
                }
                else {
                    mobileNo = result.getMobileno();
                    otpVerification.sendVerificationCode(mobileNo);
                    loginFromLoginPage = true;
                    otpText = "Please enter the verification code\n sent to "+mobileNo;
                    animateOtpForm();
                    animateLoginForm(0 , View.GONE);
                }
            });
        });

        otp_verify_button.setOnClickListener(v -> {
            String otp = otp_edittext4.getText().toString()+
                    otp_edittext1.getText().toString()+
                    otp_edittext2.getText().toString()+
                    otp_edittext3.getText().toString()+
                    otp_edittext5.getText().toString()+
                    otp_edittext6.getText().toString();
            if(otp.length()!=6)
                toast.makeToast("Invalid code" , Toast.LENGTH_LONG);
            else {
                otpVerification.verifyCode(otp).observe(this , result->{
                    if(result)
                    {
                        viewModel.adminLogin(new LoginModel(username , password)).observe(this , token->{
                            sharedPreferences.setSharedPreferences("username" , username);
                            sharedPreferences.setSharedPreferences("password" , password);
                            sharedPreferences.setSharedPreferences("authToken" , token.getToken());
                            sharedPreferences.setSharedPreferences("loggedIn" , "true");
                        });
                        Intent intent;
                        if(loginFromLoginPage) {
                            intent = new Intent(this, HomePageActivity.class);
                        }
                        else{
                            intent = new Intent(getApplicationContext(), SetAddressActivity.class);
                        }
                        startActivity(intent);
                        finish();
                    }
                    else{
                        toast.makeToast("Invalid code" , Toast.LENGTH_LONG);
                    }
                });
            }
        });

        sign_up_form_login_textview.setOnClickListener(view -> {
            animateSignUpForm(0 , View.GONE);
            animateLoginForm(1 , View.VISIBLE);
        });

        login_form_signup_textview.setOnClickListener(view -> {
            animateLoginForm(0 , View.GONE);
            animateSignUpForm(1 , View.VISIBLE);
        });

        resend_code_textview.setOnClickListener(view -> {
            otpVerification.sendVerificationCode(mobileNo);
            otpTimer();
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
        otp_edittext6.setVisibility(View.VISIBLE);
        otp_mobile_textview.setVisibility(View.VISIBLE);
        otp_mobile_textview.setText(otpText);
        otp_verify_button.setVisibility(View.VISIBLE);
        resend_code_textview.setVisibility(View.VISIBLE);
        didnt_receive_code_textview.setVisibility(View.VISIBLE);
        otp_edittext1.animate().alpha(1).setDuration(500);
        otp_edittext2.animate().alpha(1).setDuration(500);
        otp_edittext3.animate().alpha(1).setDuration(500);
        otp_edittext4.animate().alpha(1).setDuration(500);
        otp_edittext5.animate().alpha(1).setDuration(500);
        otp_edittext6.animate().alpha(1).setDuration(500);
        otp_mobile_textview.animate().alpha(1).setDuration(500);
        resend_code_textview.animate().alpha(1).setDuration(500);
        otp_verify_button.animate().alpha(1).setDuration(500);
        didnt_receive_code_textview.animate().alpha(1).setDuration(500);
        otpTimer();
    }

    private void grantPermission()
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

    }

    private void otpTimer()
    {
        resend_code_textview.setClickable(false);
        new CountDownTimer(60000 , 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String resend = "Resend code("+millisUntilFinished/1000+")";
                resend_code_textview.setText(resend);
            }

            @Override
            public void onFinish() {
                resend_code_textview.setClickable(true);
            }
        }.start();
    }
}