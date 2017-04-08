package com.example.desy.mytoptwitch.data.cache;

import com.example.desy.mytoptwitch.data.model.TopGames;

import io.paperdb.Paper;

/**
 * Created by desy on 3/25/17.
 */

public class GamesCache {
    public void save(TopGames topGames) {
        Paper.book().write(TopGames.TOPGAMES_TAG, topGames);
    }

    public TopGames retrieve() {
        return Paper.book().read(TopGames.TOPGAMES_TAG, null);
    }
}
