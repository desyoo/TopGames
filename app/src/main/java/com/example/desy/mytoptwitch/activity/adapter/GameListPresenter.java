package com.example.desy.mytoptwitch.activity.adapter;

import com.example.desy.mytoptwitch.TwitchTopGames;
import com.example.desy.mytoptwitch.data.cache.GamesCache;
import com.example.desy.mytoptwitch.data.model.Game;
import com.example.desy.mytoptwitch.data.model.TopGames;
import com.example.desy.mytoptwitch.data.server.ResponseHandler;
import com.example.desy.mytoptwitch.data.server.TopGameApiService;

import javax.inject.Inject;

/**
 * Created by desy on 3/25/17.
 */

public class GameListPresenter implements GameListContract.Presenter, ResponseHandler<TopGames> {
    GameListContract.View view;

    @Inject
    GamesCache gamesCache;
    @Inject
    TopGameApiService apiServices;

    TopGames topGamesCache;

    @Inject
    public GameListPresenter(GameListContract.View view) {
        this.view = view;
        inject();
    }

    void inject() {
        if (TwitchTopGames.getInstance() != null) {
            TwitchTopGames.getInstance().getAppComponent().inject(this);
        }
    }

    @Override
    public void onSuccess(TopGames response) {
        if (view != null) {
            view.dismissRefreshing();
            if (response != null && response.games != null && !response.games.isEmpty()) {
                topGamesCache = response;
                gamesCache.save(response);
                view.updateTopGames(response.games);
            } else {
                //TODO: use cache here if response is malformatted.
                view.showEmptyResult();
            }
        }
    }

    @Override
    public void onError(int responseCode, String message) {
        view.dismissRefreshing();
        view.showError();
        if (isCacheEmpty(topGamesCache)) {
            view.showEmptyResult();
        }
    }

    @Override
    public void onCreate() {
        topGamesCache = retrieveCache();
        refreshTopGames();
    }

    TopGames retrieveCache() {
        TopGames topGames = gamesCache.retrieve();
        if (!isCacheEmpty(topGames)) {
            view.updateTopGames(topGames.games);
        } else {
            view.showLoadingFirst();
        }
        return topGames;
    }

    @Override
    public void refreshTopGames() {
        if (view != null) {
            view.showRefreshing();
            if (isCacheEmpty(topGamesCache)) {
                view.showLoadingFirst();
            }
            apiServices.getGames(this, true);
        }
    }

    boolean isCacheEmpty(TopGames topGamesCache) {
        return topGamesCache == null || topGamesCache.games.isEmpty();
    }

    @Override
    public void onDestroy() {
        apiServices.cancel();
    }

    @Override
    public void onGameSelected(Game game) {
        if (view != null) {
            view.showGameDetails(game);
        }
    }
}
