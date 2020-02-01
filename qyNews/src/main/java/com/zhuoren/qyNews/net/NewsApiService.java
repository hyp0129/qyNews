package com.zhuoren.qyNews.net;

import com.zhuoren.qyNews.bean.NewsDetailBean;
import com.zhuoren.qyNews.bean.NewsListItemBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 9:52 .
 */
public interface NewsApiService {

    @GET("ClientNews")
    Observable<List<NewsListItemBean>> getNewsDetail(@Query("id") String id,
                                                     @Query("action") String action,
                                                     @Query("pullNum") int pullNum
    );

    @GET("api_vampire_article_detail")
    Observable<NewsDetailBean> getNewsArticleWithSub(@Query("aid") String aid);

    @GET
    Observable<NewsDetailBean> getNewsArticleWithCmpp(@Url String url,
                                                      @Query("aid") String aid);






}
