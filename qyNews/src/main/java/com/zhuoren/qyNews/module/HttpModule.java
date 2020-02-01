package com.zhuoren.qyNews.module;

import com.zhuoren.qyNews.MyApp;
import com.zhuoren.qyNews.net.ApiConstants;
import com.zhuoren.qyNews.net.NewsApi;
import com.zhuoren.qyNews.net.NewsApiService;
import com.zhuoren.qyNews.net.RetrofitConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 9:12 .
 */
@Module
public class HttpModule {

    @Provides
    OkHttpClient.Builder provideOkHttpClient() {
        // 指定缓存路径,缓存大小100Mb


        Cache cache = new Cache(new File(MyApp.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);

        return new OkHttpClient().newBuilder().cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(RetrofitConfig.sLoggingInterceptor)
                .addInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS);
    }

    @Provides
    NewsApi provideNetEaseApis(OkHttpClient.Builder builder) {
        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return NewsApi.getInstance(retrofitBuilder
                .baseUrl(ApiConstants.sIFengApi)
                .build().create(NewsApiService.class));
    }


}
