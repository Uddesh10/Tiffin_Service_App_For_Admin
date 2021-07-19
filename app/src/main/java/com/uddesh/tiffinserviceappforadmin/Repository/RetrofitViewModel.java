package com.uddesh.tiffinserviceappforadmin.Repository;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.uddesh.tiffinserviceappforadmin.DataModels.*;
import com.uddesh.tiffinserviceappforadmin.Helpers.SharedPreferencesHelper;
import okhttp3.MultipartBody;
import java.util.List;

public class RetrofitViewModel extends AndroidViewModel {
    private final SharedPreferencesHelper sharedPreferences;
    RetrofitRepository repository;

    public RetrofitViewModel(@NonNull Application application) {
        super(application);
        sharedPreferences = new SharedPreferencesHelper(application.getApplicationContext());
        repository = RetrofitRepository.getInstance();
    }

    public MutableLiveData<Boolean> adminSignup(SignupModel model) {
        return repository.adminSignup(model);
    }

    public MutableLiveData<LoggedInDataModel> adminLogin(LoginModel model) {
        return repository.adminLogin(model);
    }

    public MutableLiveData<Boolean> addService(MultipartBody.Part model, MultipartBody.Part file) {
        String authToken = sharedPreferences.getSharedPreferences("authToken");
        String username = sharedPreferences.getSharedPreferences("username");
        return repository.addService(username, "Bearer " + authToken, model, file);
    }

    public MutableLiveData<Boolean> updateAdminLocation(AdminLocationModel model) {
        String authToken = sharedPreferences.getSharedPreferences("authToken");
        String username = sharedPreferences.getSharedPreferences("username");
        return repository.updateAdminLocation(username, model, "Bearer " + authToken);
    }

    public MutableLiveData<Boolean> deleteService(int id) {
        String authToken = sharedPreferences.getSharedPreferences("authToken");
        String username = sharedPreferences.getSharedPreferences("username");
        return repository.deleteService(id, username, "Bearer " + authToken);
    }

    public MutableLiveData<List<UserDetailsModel>> getUserDetails() {
        String authToken = sharedPreferences.getSharedPreferences("authToken");
        String username = sharedPreferences.getSharedPreferences("username");
        return repository.getUserDetails(username, "Bearer " + authToken);
    }

    public MutableLiveData<List<GetServiceModel>> getAllServiceDetails() {
        String authToken = sharedPreferences.getSharedPreferences("authToken");
        String username = sharedPreferences.getSharedPreferences("username");
        return repository.getAllServiceDetails(username, "Bearer " + authToken);
    }

    public MutableLiveData<PersonalDetailsModel> getPersonalDetails() {
        String authToken = sharedPreferences.getSharedPreferences("authToken");
        String username = sharedPreferences.getSharedPreferences("username");
        return repository.getPersonalDetails(username, "Bearer " + authToken);
    }

    public MutableLiveData<AddServiceModel> getServiceDetails(int id) {
        String authToken = sharedPreferences.getSharedPreferences("authToken");
        String username = sharedPreferences.getSharedPreferences("username");
        return repository.getServiceDetails(id, username, "Bearer " + authToken);
    }

    public MutableLiveData<Boolean> updateService(int id, MultipartBody.Part model, MultipartBody.Part file) {
        String authToken = sharedPreferences.getSharedPreferences("authToken");
        String username = sharedPreferences.getSharedPreferences("username");
        return repository.updateService(username, id, "Bearer " + authToken, model, file);
    }

    public MutableLiveData<Boolean> updateServiceStatus(int id, boolean value) {
        String authToken = sharedPreferences.getSharedPreferences("authToken");
        String username = sharedPreferences.getSharedPreferences("username");
        return repository.updateServiceStatus(username, id, value, "Bearer " + authToken);
    }

    public MutableLiveData<Boolean> updatePersonalDetails(MultipartBody.Part model,MultipartBody.Part file){
        String authToken = sharedPreferences.getSharedPreferences("authToken");
        String username = sharedPreferences.getSharedPreferences("username");
        return repository.updatePersonalDetails(username ,"Bearer "+authToken , model , file);
    }
}

