package com.example.desy.mytoptwitch.injection.module;

import com.example.desy.mytoptwitch.TwitchTopGames;
import com.example.desy.mytoptwitch.data.server.RetrofitClient;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by desy on 3/25/17.
 */

@Module
public class AppModule {
    private TwitchTopGames application;

    public AppModule(TwitchTopGames application) {
        this.application = application;
    }
    @Provides
    @Singleton
    public TwitchTopGames providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Picasso providesPicasso(TwitchTopGames application) {
        return new Picasso.Builder(application).downloader(new OkHttp3Downloader(application, Integer.MAX_VALUE))
                .build();
    }

    @Provides
    @Singleton
    public RetrofitClient providesRetrofit() {
        return new RetrofitClient();
    }
}
