package com.cniao5.mvvm.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.cniao5.mvvm.model.GithubService;
import com.cniao5.mvvm.model.Repository;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xzhang on 2017/6/5.
 */

public class UserRepoViewModel {


    public ObservableInt searchButtonVisibility ;
    public ObservableInt progressVisibility;
    public ObservableInt infoMessageVisibility;
    public ObservableInt recyclerViewVisibility;

    public String editTextUsernameValue ;

    public List<Repository> repositoryList ;

    private RepositoryDataListener mListener ;

    private Context mContext ;
    public UserRepoViewModel(Context context, RepositoryDataListener listener){
        this.mContext = context ;
        this.mListener = listener ;
        searchButtonVisibility = new ObservableInt(View.VISIBLE);
        progressVisibility = new ObservableInt(View.VISIBLE) ;
        infoMessageVisibility = new ObservableInt(View.GONE) ;
        recyclerViewVisibility = new ObservableInt(View.GONE) ;

    }



    //搜素按钮的点击事件
    public void onClickSearch(View view) {
        loadGithubRepos(editTextUsernameValue);
    }

    public TextWatcher usernameEditTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextUsernameValue = s.toString() ;
                searchButtonVisibility.set(s.length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } ;
    }

    public void loadGithubRepos(String userName){
        if(TextUtils.isEmpty(userName))return ;
        progressVisibility.set(View.VISIBLE);
        infoMessageVisibility.set(View.GONE);
        recyclerViewVisibility.set(View.GONE);

        GithubService.Factory.create().publicRepositories(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repository>>() {

                    @Override
                    public void onCompleted() {
                        if(mListener != null)
                            mListener.repositoryDataChanage(repositoryList);
                        progressVisibility.set(View.GONE);
                        if(repositoryList != null && repositoryList.size() > 0){
                            recyclerViewVisibility.set(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressVisibility.set(View.GONE);
                        recyclerViewVisibility.set(View.GONE);
                        infoMessageVisibility.set(View.VISIBLE);
                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        UserRepoViewModel.this.repositoryList = repositories ;
                    }
                });
    }

    public interface RepositoryDataListener{
        void repositoryDataChanage(List<Repository> repositories) ;
    }


}
