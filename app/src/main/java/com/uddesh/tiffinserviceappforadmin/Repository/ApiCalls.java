package com.uddesh.tiffinserviceappforadmin.Repository;

import com.uddesh.tiffinserviceappforadmin.DataModels.*;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

public interface ApiCalls {

    @POST("adminsignup")
    Call<ResponseBody> adminSignup(@Body SignupModel model);

    @POST("adminlogin")
    Call<LoggedInDataModel> adminLogin(@Body LoginModel model);

    @Multipart
    @POST("addservice")
    Call<ResponseBody> addService(@Query("username") String username , @Header("Authorization") String authToken ,  @Part() MultipartBody.Part model , @Part() MultipartBody.Part file);

    @PUT("adminlocation")
    Call<ResponseBody> updateAdminLocation(@Query("username") String username , @Body AdminLocationModel model , @Header("Authorization") String authToken);

    @DELETE("deleteservice/{id}")
    Call<ResponseBody> deleteService(@Query("id") int id , @Query("username") String username
            , @Header("Authorization") String authToken);

    @GET("userdetails")
    Call<List<UserDetailsModel>> getUserDetails(@Query("username") String username
            , @Header("Authorization") String authToken);
    @GET("getadminservice")
    Call<List<GetServiceModel>> getAllServiceDetails(@Query("username") String username
            , @Header("Authorization") String authToken);

    @GET("getpersonaldetails")
    Call<List<PersonalDetailsModel>> getPersonalDetails(@Query("username") String username
            , @Header("Authorization") String authToken);

    @GET("servicedetails")
    Call<List<AddServiceModel>> getServiceDetails(@Query("id") int id , @Query("username") String username
            , @Header("Authorization") String authToken);

    @Multipart
    @PUT("updateservice")
    Call<ResponseBody> updateService(@Query("username") String username , @Query("id") int id , @Header("Authorization") String authToken ,  @Part() MultipartBody.Part model , @Part() MultipartBody.Part file);

    @PUT("updatestatus")
    Call<ResponseBody> updateServiceStatus(@Query("username") String username , @Query("id") int id , @Query("active") boolean value , @Header("Authorization") String authToken);

    @Multipart
    @PUT("personaldetails")
    Call<ResponseBody> updatePersonalDetails(@Query("username") String username , @Header("Authorization") String authToken ,  @Part() MultipartBody.Part model , @Part() MultipartBody.Part file);

}
