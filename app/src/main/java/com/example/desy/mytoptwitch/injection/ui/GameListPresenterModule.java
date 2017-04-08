package com.example.desy.mytoptwitch.injection.ui;

import com.example.desy.mytoptwitch.activity.adapter.GameListContract;
import com.example.desy.mytoptwitch.activity.adapter.GameListPresenter;
import com.example.desy.mytoptwitch.injection.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by desy on 3/25/17.
 */

@Module
public class GameListPresenterModule {
    private GameListContract.View view;

    private GameListPresenter presenter;

    public GameListPresenterModule(GameListContract.View view) {
        this.view = view;
        presenter = new GameListPresenter(view);
    }

    @PerActivity
    @Provides
    GameListContract.View providesGameListView() {
        return view;
    }

    @PerActivity
    @Provides
    GameListPresenter providesGameListPresenter(GameListContract.View view) {
        return presenter;
    }

    @PerActivity
    @Provides
    GameListContract.Presenter providesPresenter() {
        return presenter;
    }
}
