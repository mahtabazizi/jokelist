package com.mahtab.jokes.deps;

import com.mahtab.jokes.network.NetworkModule;
import com.mahtab.jokes.ui.JokesList.JokesListActivity;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(JokesListActivity jokesListActivity);
}
