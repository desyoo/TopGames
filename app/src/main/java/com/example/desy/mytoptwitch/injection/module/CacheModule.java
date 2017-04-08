package com.example.desy.mytoptwitch.injection.module;

import com.example.desy.mytoptwitch.data.cache.GamesCache;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by desy on 3/25/17.
 */

@Module
public class CacheModule {
    @Provides
    @Singleton
    GamesCache providesCache() {
        return new GamesCache();
    }
}
