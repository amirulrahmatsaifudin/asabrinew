package com.example.asabri.api;
import com.example.asabri.model.AccessToken;
import com.example.asabri.model.GetResponseToken;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

        @POST("v1/oauth_mobile/login")
        @FormUrlEncoded
        Call<GetResponseToken> login(
                @Field("retirement_number") String retirement_number,
                @Field("password") String password
        );

    @Multipart
    @POST("v1/oauth_mobile/register")
    Call<GetResponseToken> crete_user(
            @Part("name") RequestBody name,
            @Part("mobile_number") RequestBody mobile_number,
            @Part("retirement_number") RequestBody retirement_number,
            @Part("nik_number") RequestBody nik_number,
            @Part("address") RequestBody address,
            @Part("date_of_birth") RequestBody date_of_birth,
            @Part("place_of_birth") RequestBody place_of_birth,
            @Part MultipartBody.Part image,
            @Part("imagebase64") RequestBody imagebase64,
            @Part("password") RequestBody password,
            @Part("password_confirmation") RequestBody password_confirmation
    );

//        @POST("v1/oauth/register")
//        @FormUrlEncoded
//        Call<GetResponseToken> crete_user(
//                @Field("name") String name,
//                @Field("mobile_number") String mobile_number,
//                @Field("retirement_number") String retirement_number,
//                @Field("nik_number") String nik_number,
//                @Field("address") String address,
//                @Field("date_of_birth") String date_of_birth,
//                @Field("place_of_birth") String place_of_birth,
//                @Field("imagebase64") String imagebase64,
//                @Part MultipartBody.Part image,
//                @Field("password") String password,
//                @Field("password_confirmation") String password_confirmation
//        );

        @POST("v1/oauth_mobile/new_access_token")
        @FormUrlEncoded
        Call<AccessToken> refresh(@Field("refresh_token") String refreshToken
        );

        @POST("v1/otp/send_otp_login")
        @FormUrlEncoded
        Call<GetResponseToken> sendOtp(
                @Field("mobile_number") String mobile_number
        );

        @POST("otp/confirmation")
        @FormUrlEncoded
        Call<GetResponseToken> otpConfirmation(
                @Field("mobile_number") String mobile_number,
                @Field("otp") String otp
        );

    }