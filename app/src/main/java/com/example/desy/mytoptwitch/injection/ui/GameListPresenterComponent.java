package com.example.desy.mytoptwitch.injection.ui;

import com.example.desy.mytoptwitch.MainActivity;
import com.example.desy.mytoptwitch.injection.PerActivity;

import dagger.Component;

/**
 * Created by desy on 3/25/17.
 */

@PerActivity
@Component(modules = {GameListPresenterModule.class})
public interface GameListPresenterComponent {
    void inject(MainActivity mainActivity);
}
