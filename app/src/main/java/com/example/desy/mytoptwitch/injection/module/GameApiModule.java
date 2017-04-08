package com.example.desy.mytoptwitch.injection.module;

import com.example.desy.mytoptwitch.data.server.RetrofitClient;
import com.example.desy.mytoptwitch.data.server.TopGameApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by desy on 3/25/17.
 */

@Module
public class GameApiModule {
    @Provides
    @Singleton
    public TopGameApiService providesGamesApiService(RetrofitClient retrofitClient) {
        return new TopGameApiService(retrofitClient);
    }
}
