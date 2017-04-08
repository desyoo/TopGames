package com.example.desy.mytoptwitch.data.server;

import com.example.desy.mytoptwitch.data.model.TopGames;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by desy on 3/25/17.
 */

public class TopGameApiService {
    private GamesApi gamesApi;

    private Call<TopGames> mCall;

    @Inject
    public TopGameApiService(RetrofitClient retrofitClient) {
        gamesApi = retrofitClient.create(GamesApi.class);
    }

    public void getGames(final ResponseHandler<TopGames> callBack,
                         boolean isCancelable) {
        Call<TopGames> call = gamesApi.getTopGames();
        if (isCancelable) {
            if (mCall != null) {
                return;
            } else {
                mCall = call;
            }
        }

        call.enqueue(new Callback<TopGames>() {
            @Override
            public void onResponse(Call<TopGames> call, Response<TopGames> response) {
                mCall = null;
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    callBack.onError(response.code(), response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<TopGames> call, Throwable t) {
                mCall = null;
                callBack.onError(-1, t.getMessage());
            }
        });
    }

    public void cancel() {
        if (mCall != null && !mCall.isCanceled()) {
            mCall.cancel();
            mCall = null;
        }
    }

    public interface GamesApi {
        @Headers({"Accept: application/vnd.twitchtv.v5+json",
                "Client-ID: "})
        @GET("games/top?limit=50")
        Call<TopGames> getTopGames();
    }
}
