package com.zhuoren.qyNews.component;

import com.zhuoren.qyNews.ui.news.NewsListFragment;

import dagger.Component;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/7/31 22:19 .
 */
@Component(dependencies = ApplicationComponent.class)
public interface NewsComponent {
    void inject(NewsListFragment newsFragment);

}
