package com.cniao5.mvp.presenter;

/**
 * <p>Description:
 * 业务逻辑处理的基类
 *
 * @author xzhang
 */

public interface BasePresenter<T> {

    void attachView(T view) ;

    void detachView() ;

}
