package com.mahtab.jokes.ui.JokesList;

import com.mahtab.jokes.models.JokeListResponse;

public interface JokesListView {

    void showWait();

    void hideWait();

    void onFailure();

    void getJokeListSuccess(JokeListResponse jokeListResponse);
}
