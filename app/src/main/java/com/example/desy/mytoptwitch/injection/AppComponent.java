package com.example.desy.mytoptwitch.injection;

import com.example.desy.mytoptwitch.activity.adapter.GameListAdapter;
import com.example.desy.mytoptwitch.activity.adapter.GameListPresenter;
import com.example.desy.mytoptwitch.activity.detail.GameDetailsActivity;
import com.example.desy.mytoptwitch.data.cache.GamesCache;
import com.example.desy.mytoptwitch.data.server.RetrofitClient;
import com.example.desy.mytoptwitch.data.server.TopGameApiService;
import com.example.desy.mytoptwitch.injection.module.AppModule;
import com.example.desy.mytoptwitch.injection.module.CacheModule;
import com.example.desy.mytoptwitch.injection.module.GameApiModule;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by desy on 3/25/17.
 */

@Singleton
@Component(modules = {AppModule.class, CacheModule.class, GameApiModule.class})
public interface AppComponent {

    RetrofitClient getRetrofit();

    GamesCache getCache();

    TopGameApiService getApiServices();

    Picasso getPicasso();

    void inject(GameDetailsActivity gameDetailsActivity);
    void inject(GameListAdapter adapter);
    void inject(GameListPresenter presenter);
}
