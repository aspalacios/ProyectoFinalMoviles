package com.momotombodevs.pgalante.bmi_calculator.Api;

import android.media.session.MediaSession;

import com.momotombodevs.pgalante.bmi_calculator.models.LoginModel;
import com.momotombodevs.pgalante.bmi_calculator.models.TokenModel;
import com.momotombodevs.pgalante.bmi_calculator.models.UserModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @POST("users/login")
    Call<UserModel> login(@Body LoginModel login);

    @POST("users")
    Call<UserModel> createUser(@Body UserModel user);

    @PUT("users/{id}")
    Call<UserModel> updateUser(@Path("id") String id, @Body UserModel userModel);

    @DELETE("users/{id}")
    Call<UserModel> deleteUser(@Path("id") String id);

    @GET("users/login")
    Call<TokenModel> getToken(@Header("id")String id);
}