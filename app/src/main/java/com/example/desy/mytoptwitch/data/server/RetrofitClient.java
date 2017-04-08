package com.example.desy.mytoptwitch.data.server;

import com.example.desy.mytoptwitch.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by desy on 3/23/17.
 */

public class RetrofitClient {
    private static final String BASE_URL = "https://api.twitch.tv/kraken/";

    private final Retrofit mRetrofit;

    public RetrofitClient() {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create());

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();
            builder.client(client);
        }

        mRetrofit = builder.build();
    }

    public <T> T create(Class<T> apiRequestMethods) {
        return mRetrofit.create(apiRequestMethods);
    }


}
