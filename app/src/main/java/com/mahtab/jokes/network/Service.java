package com.mahtab.jokes.network;

import android.graphics.ColorSpace;

import com.mahtab.jokes.database.EntityValue;
import com.mahtab.jokes.database.JokeListDao;
import com.mahtab.jokes.models.JokeListResponse;
import com.mahtab.jokes.models.Value;
import com.mahtab.jokes.repository.LocalRepositoryImpl;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Service {

    private final NetworkService networkService;
    //private LocalRepositoryImpl localRepository;
    private List<Value> mValues;
    private ArrayList<EntityValue> entityValue;

    public Service(NetworkService networkService) {

        this.networkService = networkService;
    }
   /* public Service(LocalRepositoryImpl localRepository){        this.localRepository=localRepository;
    }
*//*
    public Flowable<List<EntityValue>> getAll(){
        return localRepository.getAll();
    }*/
/*
    public void insertValue(List<EntityValue> value){
        localRepository.insert(value);
    }*/
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
                        setDataFromDb(jokeListResponse);
                        callback.onSuccess(jokeListResponse);

                    }
                });
    }


    private void setDataFromDb(JokeListResponse jokeListResponse) {
        mValues=jokeListResponse.getValue();
        convert(mValues);
      //  insertValue(entityValue);
    }
    public ArrayList<EntityValue> convert(List<Value> value){
         entityValue=new ArrayList<>();
        for (int i = 0; i < value.size(); i++) {
            Value mValue=value.get(i);
            EntityValue entity=new EntityValue();
            entity.setId(mValue.getId());
            entityValue.add(entity);
        }
        return entityValue;
    }
    public interface GetJokeListCallback{
        void onSuccess(JokeListResponse jokeListResponse);

        void onError();
    }
}

