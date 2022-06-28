package com.example.asabri.api;
import com.example.asabri.activity.otp.OurDataSetOtp;
import com.example.asabri.model.AccessToken;
import com.example.asabri.model.DetailOtp;
import com.example.asabri.model.GetResponseToken;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
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



        @POST("v1/oauth_mobile/new_access_token")
        @FormUrlEncoded
        Call<AccessToken> refresh(@Field("refresh_token") String refreshToken
        );



//        @POST("v1/otp/send_otp_login")
//         Call<DetailOtp> sendotp(@Body DetailOtp detailOtp);


//        @POST("v1/mobile_otp/confirmation")
//        @FormUrlEncoded
//        Call<DetailOtp> otpconfrimation(@Body DetailOtp detailOtp);

//        @Multipart
//        @POST("v1/otp/send_otp_login")
//        Call<GetResponseToken> sendOtp(
//                @Part("mobile_number") RequestBody mobile_number
//        );
        @Multipart
        @POST("v1/mobile_otp/confirmation")
        Call<GetResponseToken> otpConfirmation(
            @Part("mobile_number") RequestBody mobile_number,
            @Part("otp") RequestBody otp
    );

}