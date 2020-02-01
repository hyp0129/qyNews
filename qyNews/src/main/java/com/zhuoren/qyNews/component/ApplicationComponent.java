package com.zhuoren.qyNews.component;

import android.content.Context;

import com.zhuoren.qyNews.module.ApplicationModule;
import com.zhuoren.qyNews.module.HttpModule;
import com.zhuoren.qyNews.net.NewsApi;

import dagger.Component;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/7/31 22:10 .
 */
@Component(modules = {ApplicationModule.class,HttpModule.class})
public interface ApplicationComponent {


    NewsApi getNetEaseApi();

    Context getContext();



}
