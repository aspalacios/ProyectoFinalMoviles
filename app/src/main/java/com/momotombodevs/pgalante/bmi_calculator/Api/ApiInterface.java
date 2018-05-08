package com.momotombodevs.pgalante.bmi_calculator.Api;

import com.momotombodevs.pgalante.bmi_calculator.models.AdviceModel;
import com.momotombodevs.pgalante.bmi_calculator.models.LoginModel;
import com.momotombodevs.pgalante.bmi_calculator.models.LoginResult;
import com.momotombodevs.pgalante.bmi_calculator.models.UserModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("users")
    Call<UserModel> createUser(@Body UserModel user);

    @POST("users/login")
    Call<LoginResult> login(@Body LoginModel body);

    @GET("users/{id}")
    Call<UserModel> getUser(@Header("Authorization") String token, @Body UserModel user);

    @PUT("users/{id}")
    Call<UserModel> updateUser(@Header("Authorization") String token, @Path("id") String id);

    @DELETE("users/{id}")
    Call<UserModel> deleteUser(@Path("id") String id);

    @GET ("advices")
    Call<ArrayList<AdviceModel>> getAdvices();

    @POST ("advices")
    Call<AdviceModel> createAdvice(@Body AdviceModel adviceModel);

    @PUT ("advices/{id}")
    Call<AdviceModel> updateAdvice (@Path("id") String id, @Body AdviceModel adviceModel);

    @DELETE ("advices/{id}")
    Call<AdviceModel> deleteAdvice (@Path("id") String id);

}