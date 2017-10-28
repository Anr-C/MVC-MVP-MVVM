package com.cniao5.mvp.view;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public interface BaseView<T> {

    void showProgress() ;
    void showErrorMessage() ;
    void showRecyclerView(T t) ;
}
