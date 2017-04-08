package com.example.desy.mytoptwitch.activity.adapter;

import com.example.desy.mytoptwitch.data.model.Game;

import java.util.List;

/**
 * Created by desy on 3/24/17.
 */

public class GameListContract {
    public interface View {
        void showRefreshing();
        void dismissRefreshing();
        void showError();
        void updateTopGames(List<Game> games);
        void showGameDetails(Game game);
        void showEmptyResult();
        void showLoadingFirst();
    }

    public interface Presenter {
        void onCreate();
        void refreshTopGames();
        void onDestroy();
        void onGameSelected(Game game);
    }
}
