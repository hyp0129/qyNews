package com.zhuoren.qyNews.component;

import com.zhuoren.qyNews.ui.news.DetailFragment;

import dagger.Component;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/7/31 22:15 .
 */
@Component(dependencies = ApplicationComponent.class)
public interface DetailComponent {
    void inject(DetailFragment detailFragment);

}
