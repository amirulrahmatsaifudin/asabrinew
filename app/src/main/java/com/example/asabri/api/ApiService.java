package com.example.asabri.api;
import com.example.asabri.model.AccessToken;
import com.example.asabri.model.GetResponseToken;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {
    @POST("login")
    @FormUrlEncoded
    Call<GetResponseToken> login(
            @Field("email") String email,
            @Field("password") String password,
            @Field("device_token") String device_token
    );
    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken
    );

}

