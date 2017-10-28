package com.cniao5.mvp.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cniao5.mvp.R;
import com.cniao5.mvp.adapter.RepoListAdapter;
import com.cniao5.mvp.model.Repo;
import com.cniao5.mvp.presenter.impl.MainPresenterImpl;
import com.cniao5.mvp.view.MainBaseView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainBaseView {

    private Toolbar toolbar ;
    private RecyclerView recyclerView;
    private TextView text_description ;
    private ProgressBar progress ;
    private TextView text_info ;

    private MainPresenterImpl mainPresenter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        text_description = (TextView) findViewById(R.id.text_description);
        progress = (ProgressBar) findViewById(R.id.progress);
        text_info = (TextView) findViewById(R.id.text_info);

        setSupportActionBar(toolbar);
        text_description.setText("GitHub Java");

        mainPresenter = new MainPresenterImpl();
        mainPresenter.attachView(this);
        mainPresenter.loadGitHubJava();


    }


    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        text_info.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        progress.setVisibility(View.GONE);
        text_info.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerView(List<Repo> repoList) {
        progress.setVisibility(View.GONE);
        text_info.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

        RepoListAdapter adapter = new RepoListAdapter(this,repoList) ;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }
}
