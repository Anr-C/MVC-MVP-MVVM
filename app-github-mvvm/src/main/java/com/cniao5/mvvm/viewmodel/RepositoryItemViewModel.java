package com.cniao5.mvvm.viewmodel;

import android.content.Context;

import com.cniao5.mvvm.R;
import com.cniao5.mvvm.model.Repository;

/**
 * Created by xzhang on 2017/6/5.
 */

public class RepositoryItemViewModel {

    private Context mContext ;
    private Repository mRepository ;

    public RepositoryItemViewModel(Context context, Repository repository){
        this.mContext = context ;
        this.mRepository = repository ;
    }

    public String getName(){
        return mRepository.getName() ;
    }
    public String getDescription(){
        return mRepository.getDescription() ;
    }
    public String getWatchers(){
        return mContext.getResources().getString(R.string.text_watchers,mRepository.getWatchers()) ;
    }

    public String getStars(){
        return mContext.getResources().getString(R.string.text_stars,mRepository.getStargazers_count()) ;
    }

    public String getForks(){
        return mContext.getResources().getString(R.string.text_forks,mRepository.getForks()) ;
    }


    public void setRepository(Repository repository) {
        this.mRepository = repository ;
    }
}
