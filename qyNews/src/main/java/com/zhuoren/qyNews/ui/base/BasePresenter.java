package com.zhuoren.qyNews.ui.base;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 14:09 .
 */

public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}