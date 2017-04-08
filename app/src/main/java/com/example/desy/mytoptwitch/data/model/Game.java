package com.example.desy.mytoptwitch.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

/**
 * Created by desy on 3/22/17.
 */

public class Game implements Parcelable {
    public static final String GAME_TAG = "game";
    public final int viewers;
    public final int channels;

    @Json(name = "game")
    public final GameData gameData;

    public Game() {
        this(0, 0, null);
    }

    public Game(int viewers, int channels, GameData gameData) {
        this.viewers = viewers;
        this.channels = channels;
        this.gameData = gameData;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.viewers);
        dest.writeInt(this.channels);
        dest.writeParcelable(this.gameData, flags);
    }

    protected Game(Parcel in) {
        this.viewers = in.readInt();
        this.channels = in.readInt();
        this.gameData = in.readParcelable(GameData.class.getClassLoader());
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel source) {
            return new Game(source);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };
}
