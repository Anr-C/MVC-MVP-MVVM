package com.cniao5.demo.mvp.presenter;


import com.cniao5.demo.mvp.view.OtherBaseView;

/**
 *
 * 只为OtherActivity提供业务逻辑
 * Created by xzhang
 */

public interface OtheerPresenter extends BasePresenter<OtherBaseView>{



    void uploadImage(String path) ;
}
