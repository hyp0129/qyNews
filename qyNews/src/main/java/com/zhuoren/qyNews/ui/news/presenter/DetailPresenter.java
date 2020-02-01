package com.zhuoren.qyNews.ui.news.presenter;

import android.util.Log;

import com.zhuoren.qyNews.bean.NewsListItemBean;
import com.zhuoren.qyNews.net.BaseObserver;
import com.zhuoren.qyNews.net.NewsApi;
import com.zhuoren.qyNews.net.NewsUtils;
import com.zhuoren.qyNews.net.RxSchedulers;
import com.zhuoren.qyNews.ui.base.BasePresenter;
import com.zhuoren.qyNews.ui.news.contract.DetailContract;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;


/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/4/8 .
 */
public class DetailPresenter extends BasePresenter<DetailContract.View> implements DetailContract.Presenter {
    private static final String TAG = "DetailPresenter";

    NewsApi mNewsApi;

    @Inject
    public DetailPresenter(NewsApi newsApi) {
        this.mNewsApi = newsApi;
    }

    @Override
    public void getData(final String id, final String action, int pullNum) {
        mNewsApi.getNewsDetail(id, action, pullNum)
                .compose(RxSchedulers.<NewsListItemBean>applySchedulers())
                .filter(new Predicate<NewsListItemBean>() {
                    @Override
                    public boolean test(@NonNull NewsListItemBean newsDetail) throws Exception {
                        if (NewsUtils.isBannerNews(newsDetail)) {
                            mView.loadBannerData(newsDetail);
                        }
                        if (NewsUtils.isTopNews(newsDetail)) {
                            mView.loadTopNewsData(newsDetail);
                        }
                        return NewsUtils.isListNews(newsDetail);
                    }
                })
                .map(new Function<NewsListItemBean, List<NewsListItemBean.ItemBean>>() {
                    @Override
                    public List<NewsListItemBean.ItemBean> apply(@NonNull NewsListItemBean newsDetail) throws Exception {
                        Iterator<NewsListItemBean.ItemBean> iterator = newsDetail.getItem().iterator();
                        while (iterator.hasNext()) {
                            try {
                                NewsListItemBean.ItemBean bean = iterator.next();
                                if (bean.getType().equals(NewsUtils.TYPE_DOC)) {
                                    if (bean.getStyle().getView() != null) {
                                        if (bean.getStyle().getView().equals(NewsUtils.VIEW_TITLEIMG)) {
                                            bean.itemType = NewsListItemBean.ItemBean.TYPE_DOC_TITLEIMG;
                                        } else {
                                            bean.itemType = NewsListItemBean.ItemBean.TYPE_DOC_SLIDEIMG;
                                        }
                                    }
                                } else if (bean.getType().equals(NewsUtils.TYPE_SLIDE)) {
                                    if (bean.getLink().getType().equals("doc")){
                                        if (bean.getStyle().getView().equals(NewsUtils.VIEW_SLIDEIMG)) {
                                            bean.itemType = NewsListItemBean.ItemBean.TYPE_DOC_SLIDEIMG;
                                        } else {
                                            bean.itemType = NewsListItemBean.ItemBean.TYPE_DOC_TITLEIMG;
                                        }
                                    }else {
                                        bean.itemType = NewsListItemBean.ItemBean.TYPE_SLIDE;
                                    }
                                }
                                else {
                                    iterator.remove();
                                }
                            } catch (Exception e) {
                                iterator.remove();
                                e.printStackTrace();
                            }
                        }
                        return newsDetail.getItem();
                    }
                })
                .compose(mView.<List<NewsListItemBean.ItemBean>>bindToLife())
                .subscribe(new BaseObserver<List<NewsListItemBean.ItemBean>>() {
                    @Override
                    public void onSuccess(List<NewsListItemBean.ItemBean> itemBeen) {
                        if (!action.equals(NewsApi.ACTION_UP)) {
                            mView.loadData(itemBeen);
                        } else {
                            mView.loadMoreData(itemBeen);
                        }
                    }

                    @Override
                    public void onFail(Throwable e) {
                        Log.i(TAG, "onFail: " + e.getMessage().toString());
                        if (!action.equals(NewsApi.ACTION_UP)) {
                            mView.loadData(null);
                        } else {
                            mView.loadMoreData(null);
                        }
                    }
                });
    }
}
