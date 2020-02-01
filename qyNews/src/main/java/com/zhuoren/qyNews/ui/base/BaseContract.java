package com.zhuoren.qyNews.ui.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 14:05 .
 */
public interface BaseContract {

    interface BasePresenter<T extends BaseContract.BaseView> {

        void attachView(T view);
        void detachView();
    }
    interface BaseView {
        //显示请求成功
        void showSuccess();

        //失败重试
        void showFaild();

        //重试
        void onRetry();

        /**
         * 绑定生命周期
         *
         * @param <T>
         * @return
         */
        <T> LifecycleTransformer<T> bindToLife();

    }
}
