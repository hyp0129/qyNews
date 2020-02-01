package com.zhuoren.qyNews.net;

import android.support.annotation.StringDef;

import com.zhuoren.qyNews.bean.NewsDetailBean;
import com.zhuoren.qyNews.bean.NewsListItemBean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 9:36 .
 */
public class NewsApi {

    public static final String ACTION_DEFAULT = "default";
    public static final String ACTION_DOWN = "down";
    public static final String ACTION_UP = "up";

    @StringDef({ACTION_DEFAULT,ACTION_DOWN,ACTION_UP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Actions{

    }

    private volatile static NewsApi sInstance;
    private NewsApiService mService;
    private NewsApi(NewsApiService newsApiService) {
        this.mService = newsApiService;
    }

    public static NewsApi getInstance(NewsApiService newsApiService) {

        if (sInstance == null){
            synchronized (NewsApi.class){
                if (sInstance==null){
                    sInstance = new NewsApi(newsApiService);
                }
            }

        }
        return sInstance;
    }

    /**
     * 获取新闻详情
     *
     * @param id      频道ID值
     * @param action  用户操作方式
     *                1：下拉 down
     *                2：上拉 up
     *                3：默认 default
     * @param pullNum 操作次数 累加
     * @return
     */

    public Observable<NewsListItemBean> getNewsDetail(String id, @Actions String action, int pullNum) {
        return mService.getNewsDetail(id, action, pullNum)
                .flatMap(new Function<List<NewsListItemBean>, ObservableSource<NewsListItemBean>>() {
                    @Override
                    public ObservableSource<NewsListItemBean> apply(@NonNull List<NewsListItemBean> newsDetails) throws Exception {
                        return Observable.fromIterable(newsDetails);
                    }
                });
    }

    /**
     * 获取新闻文章详情
     * @param aid 文章aid  此处baseurl可能不同，需要特殊处理
     *          1：aid 以 cmpp 开头则调用 getNewsArticleWithCmpp
     * @return
     */


    public Observable<NewsDetailBean> getNewsArticle(String aid){
        if (aid.startsWith("sub")){
            return mService.getNewsArticleWithSub(aid);
        }else {
            return mService.getNewsArticleWithCmpp(ApiConstants.sGetNewsArticleDocCmppApi,aid);
        }
    }



}
