package com.cniao5.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.cniao5.mvvm.R;
import com.cniao5.mvvm.adapter.RepoListAdapter;
import com.cniao5.mvvm.databinding.ActivityMainBinding;
import com.cniao5.mvvm.model.Repo;
import com.cniao5.mvvm.viewmodel.MainViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainViewModel.DataListener{


    private ActivityMainBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainViewModel viewModel = new MainViewModel(this);
        binding.setViewModel(viewModel);

        setSupportActionBar(binding.toolbar);
        binding.textDescription.setText(getResources().getString(R.string.github_java));

    }


    @Override
    public void repoDataChanage(List<Repo> repoList) {
        Log.i("MainActivity","repoList:"+repoList.size()) ;

        RepoListAdapter adapter = new RepoListAdapter(this,repoList) ;
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
