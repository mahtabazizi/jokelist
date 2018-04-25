package com.mahtab.jokes.network;

import com.mahtab.jokes.models.JokeListResponse;


import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Service {

    private final NetworkService networkService;

    public Service(NetworkService networkService) {
        this.networkService = networkService;
    }
    public Subscription getJokeList (final GetJokeListCallback callback) {

        return networkService.getJokeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends JokeListResponse>>() {
                    @Override
                    public Observable<? extends JokeListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<JokeListResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError();

                    }

                    @Override
                    public void onNext(JokeListResponse jokeListResponse) {
                        callback.onSuccess(jokeListResponse);

                    }
                });
    }

    public interface GetJokeListCallback{
        void onSuccess(JokeListResponse cityListResponse);

        void onError();
    }
}

