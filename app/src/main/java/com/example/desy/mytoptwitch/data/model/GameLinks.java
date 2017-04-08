package com.example.desy.mytoptwitch.data.model;

import android.os.Parcel;
import android.os.Parcelable;


public class GameLinks implements Parcelable {
    public final String self, next;

    public GameLinks() {
        this(null, null);
    }

    public GameLinks(String self, String next) {
        this.self = self;
        this.next = next;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.self);
        dest.writeString(this.next);
    }

    protected GameLinks(Parcel in) {
        this.self = in.readString();
        this.next = in.readString();
    }

    public static final Creator<GameLinks> CREATOR = new Creator<GameLinks>() {
        @Override
        public GameLinks createFromParcel(Parcel source) {
            return new GameLinks(source);
        }

        @Override
        public GameLinks[] newArray(int size) {
            return new GameLinks[size];
        }
    };
}
