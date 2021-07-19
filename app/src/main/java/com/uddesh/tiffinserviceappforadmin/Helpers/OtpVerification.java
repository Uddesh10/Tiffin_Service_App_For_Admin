package com.uddesh.tiffinserviceappforadmin.Helpers;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

import java.util.concurrent.TimeUnit;

public class OtpVerification {
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String verificationId;
    private final Activity activity;

    public OtpVerification(Activity activity) {
        this.activity = activity;
    }

    public void sendVerificationCode(String number) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+number)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(activity)
                        .setCallbacks(mCallBack)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private final OnVerificationStateChangedCallbacks mCallBack = new OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

        @Override
        public void onCodeSent(@NonNull String code, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
           verificationId = code;
        }
    };

    public MutableLiveData<Boolean> verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        return signInWithCredential(credential);
    }

    private MutableLiveData<Boolean> signInWithCredential(PhoneAuthCredential credential) {
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        data.setValue(true);
                    }
                    else{
                        data.setValue(false);
                    }
                });
        return data;
    }
}
