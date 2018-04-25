package com.mahtab.jokes.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.mahtab.jokes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JokesListActivity extends AppCompatActivity {

    @BindView(R.id.activity_jokeslist_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R.id.activity_jokeslist_linear_waiting)
    LinearLayout mLinearWaiting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_list);
        ButterKnife.bind(this);
    }
}
