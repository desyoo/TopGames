package com.example.desy.mytoptwitch.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

/**
 * Created by desy on 3/25/17.
 */

public class GameData implements Parcelable{
    public final String name;
    @Json(name = "_id")
    public final int id;
    @Json(name = "giantbomb_id")
    public final int giantbombId;
    @Json(name = "box")
    public final Images boxImages;
    @Json(name = "logo")
    public final Images logoImages;
    @Json(name = "_links")
    public final GameLinks links;

    public GameData() {
        this(null, 0, 0, null, null, null);
    }

    public GameData(String name, int id, int giantbombId, Images boxImages, Images logoImages, GameLinks links) {
        this.name = name;
        this.id = id;
        this.giantbombId = giantbombId;
        this.boxImages = boxImages;
        this.logoImages = logoImages;
        this.links = links;
    }

    public static class Images implements Parcelable {
        public final String large, medium, small, template;

        public Images() {
            this(null, null, null, null);
        }

        public Images(String large, String medium, String small, String template) {
            this.large = large;
            this.medium = medium;
            this.small = small;
            this.template = template;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.large);
            dest.writeString(this.medium);
            dest.writeString(this.small);
            dest.writeString(this.template);
        }

        protected Images(Parcel in) {
            this.large = in.readString();
            this.medium = in.readString();
            this.small = in.readString();
            this.template = in.readString();
        }

        public static final Creator<Images> CREATOR = new Creator<Images>() {
            @Override
            public Images createFromParcel(Parcel source) {
                return new Images(source);
            }

            @Override
            public Images[] newArray(int size) {
                return new Images[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeInt(this.giantbombId);
        dest.writeParcelable(this.boxImages, flags);
        dest.writeParcelable(this.logoImages, flags);
        dest.writeParcelable(this.links, flags);
    }

    protected GameData(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
        this.giantbombId = in.readInt();
        this.boxImages = in.readParcelable(Images.class.getClassLoader());
        this.logoImages = in.readParcelable(Images.class.getClassLoader());
        this.links = in.readParcelable(GameLinks.class.getClassLoader());
    }

    public static final Creator<GameData> CREATOR = new Creator<GameData>() {
        @Override
        public GameData createFromParcel(Parcel source) {
            return new GameData(source);
        }

        @Override
        public GameData[] newArray(int size) {
            return new GameData[size];
        }
    };
}
