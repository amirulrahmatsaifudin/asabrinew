package com.example.asabri.util;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServiceRaw {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("v1/mobile_otp/send_otp_login")
    Call<JsonObject> sendotp(@Body JsonObject jsonObject);

    @POST("v1/mobile_otp/confirmation")
    Call<JsonObject> otpconfirmation(@Body JsonObject jsonObject);
}
