package com.example.desy.mytoptwitch.data.server;

/**
 * Created by desy on 3/25/17.
 */

public interface ResponseHandler<T> {
    void onSuccess(T response);
    void onError(int responseCode, String message);
}
