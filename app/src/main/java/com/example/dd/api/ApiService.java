package com.example.dd.api;

import com.example.dd.login.dto.LoginRequest;
import com.example.dd.login.dto.LoginResponse;
import com.example.dd.signup.dto.SignUpRequest;
import com.example.dd.signup.dto.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/v1/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    @POST("/api/v1/auth/register")
    Call<SignUpResponse> signUp(@Body SignUpRequest signUpRequest);
//
//    @GET("/api/v1/tutorial")
//    Call<List<Tutorial>> findAllTutorial();

}
