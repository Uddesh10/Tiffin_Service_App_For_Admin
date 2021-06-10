package com.uddesh.tiffinserviceappforadmin.Helpers;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.uddesh.tiffinserviceappforadmin.R;


public class GenericTextWatcher implements TextWatcher
{
    private View view;
    private EditText[] otpEditText;
    Context context;
    public GenericTextWatcher(View view , EditText[] otpEditText)
    {
        this.view = view;
        this.otpEditText = otpEditText;

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        switch(view.getId())
        {

            case R.id.otp_edittext4:
                if(text.length()==1)
                    otpEditText[1].requestFocus();
                break;
            case R.id.otp_edittext1:
                if(text.length()==1)
                    otpEditText[2].requestFocus();
                else if(text.length()==0)
                    otpEditText[0].requestFocus();
                break;
            case R.id.otp_edittext2:
                if(text.length()==1)
                    otpEditText[3].requestFocus();
                else if(text.length()==0)
                    otpEditText[1].requestFocus();
                break;
            case R.id.otp_edittext3:
                if(text.length()==1)
                    otpEditText[4].requestFocus();
                else if(text.length()==0)
                    otpEditText[2].requestFocus();
                break;
            case R.id.otp_edittext5:
                if(text.length()==0)
                    otpEditText[3].requestFocus();


        }
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
    }
}
