package com.cniao5.mvvm.viewmodel;

import android.databinding.ObservableInt;
import android.view.View;

import com.cniao5.mvvm.model.GithubService;
import com.cniao5.mvvm.model.Repo;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class MainViewModel {

    public ObservableInt progressVisibility = new ObservableInt(View.GONE) ;
    public ObservableInt erroMessageVisibility = new ObservableInt(View.GONE) ;
    public ObservableInt recyclerViewVisibility = new ObservableInt(View.GONE) ;

    private List<Repo> repoList ;

    private DataListener mListener ;


    public MainViewModel(DataListener listener){
        this.mListener = listener ;
        loadGitHubJava();
    }

    private void loadGitHubJava() {

        progressVisibility.set(View.VISIBLE);

        String url = "http://github.laowch.com/json/java_daily" ;
        GithubService.Factory.create().javaRepositories(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {
                        progressVisibility.set(View.GONE);
                        erroMessageVisibility.set(View.GONE);
                        recyclerViewVisibility.set(View.VISIBLE);
                        if(mListener != null && repoList != null){
                            mListener.repoDataChanage(repoList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        progressVisibility.set(View.GONE);
                        erroMessageVisibility.set(View.VISIBLE);
                        recyclerViewVisibility.set(View.GONE);
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        repoList = repos ;
                    }
                });
    }

    public interface DataListener {
        void repoDataChanage(List<Repo> repoList);
    }


}
