package com.mahtab.jokes.ui.JokesList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mahtab.jokes.R;
import com.mahtab.jokes.base.BaseApp;
import com.mahtab.jokes.models.JokeListResponse;
import com.mahtab.jokes.network.Service;
import com.mahtab.jokes.utils.ConnectionUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JokesListActivity extends BaseApp implements JokesListView {

    @BindView(R.id.activity_jokeslist_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.activity_jokeslist_linear_waiting)
    LinearLayout mLinearWaiting;
    @Inject
    public Service service;
    private JokeListPresenter presenter;
    private JokesListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.activity_jokes_list);
        ButterKnife.bind(this);
        checkNetworkConnection();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter = new JokeListPresenter(service, this);
        presenter.getJokeList();
    }

    private void checkNetworkConnection() {
        ConnectionUtil connect=new ConnectionUtil(getApplicationContext());
        if(!connect.isConnected()){
            Toast.makeText(this, "Please Check Network Connection.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "network is on", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showWait() {

        mLinearWaiting.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWait() {

        mLinearWaiting.setVisibility(View.GONE);
    }

    @Override
    public void onFailure() {

    }

    @Override
    public void getJokeListSuccess(JokeListResponse jokeListResponse) {

         adapter = new JokesListAdapter(getApplicationContext(), jokeListResponse.getValue());
        mRecyclerView.setAdapter(adapter);

    }
}