package com.uddesh.tiffinserviceappforadmin.Repository;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.uddesh.tiffinserviceappforadmin.DataModels.*;
import com.uddesh.tiffinserviceappforadmin.Helpers.RetrofitHelper;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class RetrofitRepository {
    private final RetrofitHelper retrofitHelper = new RetrofitHelper();
    private static RetrofitRepository retrofitRepository;
    public static RetrofitRepository getInstance()
    {
        if(retrofitRepository==null)
        {
            retrofitRepository = new RetrofitRepository();
        }
        return retrofitRepository;
    }

    public MutableLiveData<Boolean> adminSignup(SignupModel model)
    {
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        retrofitHelper.apiCalls.adminSignup(model).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    data.setValue(true);
                }
                else{
                    data.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call,@NonNull Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<LoggedInDataModel> adminLogin(LoginModel model)
    {
        MutableLiveData<LoggedInDataModel> data = new MutableLiveData<>();
        retrofitHelper.apiCalls.adminLogin(model).enqueue(new Callback<LoggedInDataModel>() {
            @Override
            public void onResponse(@NonNull Call<LoggedInDataModel> call,@NonNull Response<LoggedInDataModel> response) {
                if(response.isSuccessful())
                {
                    data.setValue(response.body());
                }
                else{
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoggedInDataModel> call,@NonNull Throwable t) {

            }
        });
        return data;
    }


    public MutableLiveData<Boolean> addService(String username , String authToken ,MultipartBody.Part model,MultipartBody.Part file)
    {
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        retrofitHelper.apiCalls.addService(username , authToken , model , file).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,@NonNull Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    data.setValue(true);
                }
                else{
                    data.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call,@NonNull Throwable t) {

            }
        });
        return data;
    }


    public MutableLiveData<Boolean> updateAdminLocation(String username , AdminLocationModel model , String authToken)
    {
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        retrofitHelper.apiCalls.updateAdminLocation(username , model , authToken).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,@NonNull Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    data.setValue(true);
                }
                else{
                    data.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call,@NonNull Throwable t) {

            }
        });
        return data;
    }


    public MutableLiveData<Boolean> deleteService(int id , String username , String authToken)
    {
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        retrofitHelper.apiCalls.deleteService(id, username , authToken).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,@NonNull Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    data.setValue(true);
                }
                else{
                    data.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call,@NonNull Throwable t) {

            }
        });
        return data;
    }


    public MutableLiveData<List<UserDetailsModel>> getUserDetails(String username , String authToken)
    {
        MutableLiveData<List<UserDetailsModel>> data = new MutableLiveData<>();
        retrofitHelper.apiCalls.getUserDetails(username , authToken).enqueue(new Callback<List<UserDetailsModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserDetailsModel>> call,@NonNull Response<List<UserDetailsModel>> response) {
                if(response.isSuccessful())
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<UserDetailsModel>> call,@NonNull Throwable t) {

            }
        });
        return data;
    }


    public MutableLiveData<List<GetServiceModel>> getAllServiceDetails(String username , String authToken)
    {
        MutableLiveData<List<GetServiceModel>> data = new MutableLiveData<>();
        retrofitHelper.apiCalls.getAllServiceDetails(username , authToken).enqueue(new Callback<List<GetServiceModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<GetServiceModel>> call,@NonNull Response<List<GetServiceModel>> response) {
                if(response.isSuccessful())
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<GetServiceModel>> call,@NonNull Throwable t) {

            }
        });
        return data;
    }


    public MutableLiveData<PersonalDetailsModel> getPersonalDetails(String username , String authToken)
    {
        MutableLiveData<PersonalDetailsModel> data = new MutableLiveData<>();
        retrofitHelper.apiCalls.getPersonalDetails(username , authToken).enqueue(new Callback<List<PersonalDetailsModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<PersonalDetailsModel>> call,@NonNull Response<List<PersonalDetailsModel>> response) {
                if(response.isSuccessful())
                {
                    data.setValue(response.body().get(0));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PersonalDetailsModel>> call,@NonNull Throwable t) {

            }
        });
        return data;
    }


    public MutableLiveData<AddServiceModel> getServiceDetails(int id ,String username , String authToken)
    {
        MutableLiveData<AddServiceModel> data = new MutableLiveData<>();
        retrofitHelper.apiCalls.getServiceDetails(id , username , authToken).enqueue(new Callback<List<AddServiceModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<AddServiceModel>> call,@NonNull Response<List<AddServiceModel>> response) {
                if(response.isSuccessful())
                {
                    data.setValue(response.body().get(0));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<AddServiceModel>> call,@NonNull Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<Boolean> updateService(String username , int id , String authToken ,MultipartBody.Part model,MultipartBody.Part file)
    {
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        retrofitHelper.apiCalls.updateService(username , id ,  authToken , model , file).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,@NonNull Response<ResponseBody> response) {
                if(response.isSuccessful())
                {
                    data.setValue(true);
                }
                else{
                    data.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call,@NonNull Throwable t) {
                Log.i("data" , t+"");
            }
        });
        return data;
    }

    public MutableLiveData<Boolean> updateServiceStatus(String username, int id , boolean value , String authToken){
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        retrofitHelper.apiCalls.updateServiceStatus(username , id , value , authToken).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,@NonNull Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    data.setValue(true);
                }
                else{
                    data.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call,@NonNull Throwable t) {

            }
        });
        return data;
    }

    public MutableLiveData<Boolean> updatePersonalDetails(String username , String authToken ,MultipartBody.Part model,MultipartBody.Part file){
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        retrofitHelper.apiCalls.updatePersonalDetails(username , authToken , model , file).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,@NonNull Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    data.setValue(true);
                }
                else{
                    data.setValue(false);
                }
                Log.i("data" , response.message()+"");
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call,@NonNull Throwable t) {

            }
        });
        return data;
    }

}
