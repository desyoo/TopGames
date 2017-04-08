package com.example.desy.mytoptwitch.activity.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desy.mytoptwitch.R;
import com.example.desy.mytoptwitch.TwitchTopGames;
import com.example.desy.mytoptwitch.data.model.Game;
import com.example.desy.mytoptwitch.data.model.GameData;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by desy on 3/25/17.
 */

public class GameDetailsActivity extends AppCompatActivity{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.img_game_logo)
    ImageView imgGameLogo;
    @Bind(R.id.txt_game_title)
    TextView gameTitle;
    @Bind(R.id.txt_game_channels)
    TextView gameChannels;
    @Bind(R.id.txt_game_views)
    TextView gameViews;
    @Bind(R.id.img_game)
    ImageView imgGameBox;

    Game currentGame;

    @Inject
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_details_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        inject();

        retrieveGame();
        if (currentGame != null) {
            populate();
        }
    }

    void inject() {
        if (TwitchTopGames.getInstance() != null) {
            TwitchTopGames.getInstance().getAppComponent().inject(this);
        }
    }

    private void retrieveGame() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(Game.GAME_TAG)) {
            currentGame = extras.getParcelable(Game.GAME_TAG);
        }
    }

    void populate() {
        GameData gameData = currentGame.gameData;
        if (gameData.logoImages != null && gameData.logoImages.large != null) {
            picasso.load(gameData.logoImages.large)
                    .placeholder(R.drawable.camera_loading_land)
                    .into(imgGameLogo);
        }

        if (gameData.boxImages != null && gameData.boxImages.large != null) {
            picasso.load(gameData.boxImages.large)
                    .placeholder(R.drawable.camera_loading)
                    .into(imgGameBox);
        }

        gameTitle.setText(gameData.name);
        gameChannels.setText(Integer.toString(currentGame.channels));
        gameViews.setText(Integer.toString(currentGame.viewers));
    }
}
