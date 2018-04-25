package com.mahtab.jokes.network;

import com.mahtab.jokes.models.JokeListResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface NetworkService {

    @GET("jokes/random/10")
    Observable<JokeListResponse> getJokeList();
}
