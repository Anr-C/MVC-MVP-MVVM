package com.cniao5.mvvm.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.inputmethod.InputMethodManager;

import com.cniao5.mvvm.R;
import com.cniao5.mvvm.adapter.RepositoryAdapter;
import com.cniao5.mvvm.databinding.ActivityUserRepoBinding;
import com.cniao5.mvvm.model.Repository;
import com.cniao5.mvvm.viewmodel.UserRepoViewModel;

import java.util.List;

public class UserRepoActivity extends AppCompatActivity implements UserRepoViewModel.RepositoryDataListener {

    private ActivityUserRepoBinding binding ;
    private UserRepoViewModel viewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_repo) ;
        viewModel = new UserRepoViewModel(this,this) ;
        binding.setViewModel(viewModel);
        setSupportActionBar(binding.toolbar);

        String username = getIntent().getStringExtra("username");
        viewModel.loadGithubRepos(username) ;
        binding.editTextUsername.setText(username);

    }

    @Override
    public void repositoryDataChanage(List<Repository> repositories) {
        RepositoryAdapter adapter = new RepositoryAdapter(this,repositories) ;
        binding.reposRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.reposRecyclerView.setAdapter(adapter);
        hideSoftKeyboard();
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.editTextUsername.getWindowToken(),0) ;
    }
}
