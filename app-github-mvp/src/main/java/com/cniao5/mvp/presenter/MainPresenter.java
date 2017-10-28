package com.cniao5.mvp.presenter;

import com.cniao5.mvp.view.MainBaseView;

/**
 * <p>Description:
 * 处理MainActivity当中的业务逻辑,只为MainActivity服务
 *
 * @author xzhang
 */

public interface MainPresenter extends BasePresenter<MainBaseView> {
    //在这里添加MainPresenter特有的方法
    void loadGitHubJava() ;
}
