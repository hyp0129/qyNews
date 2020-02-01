package com.zhuoren.qyNews.ui.news.presenter;

import com.zhuoren.qyNews.bean.NewsDetailBean;
import com.zhuoren.qyNews.net.NewsApi;
import com.zhuoren.qyNews.net.RxSchedulers;
import com.zhuoren.qyNews.ui.base.BasePresenter;
import com.zhuoren.qyNews.ui.news.contract.ArticleReadContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/4/21 .
 */
public class NewsDetailPresenter extends BasePresenter<ArticleReadContract.View> implements ArticleReadContract.Presenter {
    NewsApi mNewsApi;
    @Inject
    public NewsDetailPresenter(NewsApi newsApi) {
        this.mNewsApi = newsApi;
    }

    @Override
    public void getData(String aid) {
        mNewsApi.getNewsArticle(aid)
                .compose(RxSchedulers.<NewsDetailBean>applySchedulers())
                .compose(mView.<NewsDetailBean>bindToLife())
                .subscribe(new Observer<NewsDetailBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull NewsDetailBean articleBean) {
                        mView.loadData(articleBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showFaild();
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}
