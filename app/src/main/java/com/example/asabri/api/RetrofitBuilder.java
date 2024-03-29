package com.example.asabri.api;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    public static final String BASE_URL = "https://api-poc-asabri.picaso.id/api/";
    // public static final String BASE_URL_IMAGES = "";

    private final static OkHttpClient client = buildClient();
    private final static Retrofit retrofit = buildRetrofit(client);

    private static OkHttpClient buildClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Request.Builder builder1 = request.newBuilder()
                            .addHeader("Accept", "application/json")
                            .addHeader("Connection", "close");

                    request = builder1.build();

                    return chain.proceed(request);
                });
        return builder.build();
    }

    private static Retrofit buildRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T createService(Class<T> service) {
        return retrofit.create(service);
    }

    public static <T> T createServiceWithAuth(Class<T> service, final TokenManager tokenManager) {
        OkHttpClient newClient = client.newBuilder().addInterceptor(chain -> {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();

            if (tokenManager.getToken().getToken() != null) {
                builder.addHeader("Authorization", "Bearer " + tokenManager.getToken().getToken());
            }

            request = builder.build();
            return chain.proceed(request);
        }).authenticator(CustomAuthenticator.getInstance(tokenManager)).build();

        Retrofit newRetrofit = retrofit.newBuilder().client(newClient).build();
        return newRetrofit.create(service);
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }


}
