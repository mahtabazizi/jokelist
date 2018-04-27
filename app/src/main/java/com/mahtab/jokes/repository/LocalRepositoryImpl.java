package com.mahtab.jokes.repository;

import android.os.Handler;

import com.mahtab.jokes.database.EntityValue;
import com.mahtab.jokes.database.JokeListDao;
import com.mahtab.jokes.models.JokeListResponse;
import com.mahtab.jokes.network.NetworkService;

import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Flowable;
import rx.Observable;


public class LocalRepositoryImpl implements NetworkService {

    private JokeListDao jokeListDao;
    private Executor executor;

    public LocalRepositoryImpl(JokeListDao jokeListDao) {
        jokeListDao = jokeListDao;
    }
/*
    public Flowable<List<EntityValue>> getAll() {
        return jokeListDao.getAll();
    }*/

    public void insert(final List<EntityValue> value) {

       new Handler().postDelayed(new Runnable(){
           @Override
           public void run() {
               jokeListDao.insert(value);
           }
       },100);


    }

    @Override
    public Observable<JokeListResponse> getJokeList() {
        return null;
    }
}
