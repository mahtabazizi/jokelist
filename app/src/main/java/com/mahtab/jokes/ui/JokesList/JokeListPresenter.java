package com.mahtab.jokes.ui.JokesList;

import com.mahtab.jokes.database.EntityValue;
import com.mahtab.jokes.models.JokeListResponse;
import com.mahtab.jokes.network.Service;
import java.util.List;

import io.reactivex.Flowable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class JokeListPresenter {

    private final Service service;
    private final JokesListView view;
    private CompositeSubscription subscriptions;

    public JokeListPresenter(Service service, JokesListView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }


    public void getJokeList() {
        view.showWait();

        Subscription subscription = service.getJokeList(new Service.GetJokeListCallback() {
            @Override
            public void onSuccess(JokeListResponse jokeListResponse) {
                view.hideWait();
                view.getJokeListSuccess(jokeListResponse);
            }

            @Override
            public void onError() {
                view.hideWait();
                view.onFailure();
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }
}
