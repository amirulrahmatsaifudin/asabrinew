package com.example.asabri.api;
import com.example.asabri.model.AccessToken;
import com.example.asabri.model.GetResponseToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @POST("login")
    @FormUrlEncoded
    Call<GetResponseToken> login(
            @Field("retirement_number") String retirement_number,
            @Field("password") String password
    );
    @POST("crete_user")
    @FormUrlEncoded
    Call<GetResponseToken> crete_user(
            @Field("nama") String nama,
            @Field("mobile_number") String mobile_number,
            @Field("retirement_number") String retirement_number,
            @Field("nik_number") String nik_number,
            @Field("address") String address,
            @Field("date_of_birth") String date_of_birthretirement_number,
            @Field("place_of_birth") String your_place_of_birth,
            @Field("password") String password,
            @Field("image") String image,
            @Field("imagebase64") String imagebase64

    );

    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken
    );

}

