package com.example.desy.mytoptwitch;

import android.app.Application;

import com.example.desy.mytoptwitch.injection.AppComponent;
import com.example.desy.mytoptwitch.injection.DaggerAppComponent;
import com.example.desy.mytoptwitch.injection.module.AppModule;

import io.paperdb.Paper;

/**
 * Created by desy on 3/25/17.
 */

public class TwitchTopGames extends Application {
    private static TwitchTopGames twitchApp;

    public AppComponent component;

    public static TwitchTopGames getInstance() {
        return twitchApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        twitchApp = this;
        Paper.init(this);

        component = createAppComponent();
    }

    public AppComponent getAppComponent() {
        return component;
    }

    AppComponent createAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
