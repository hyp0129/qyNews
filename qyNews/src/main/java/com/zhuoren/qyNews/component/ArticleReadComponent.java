package com.zhuoren.qyNews.component;

import com.zhuoren.qyNews.ui.news.NewsDetailActivity;
//import com.zhuoren.zr10.ui.news.ImageBrowseActivity;

import dagger.Component;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/7/31 22:13 .
 */
@Component(dependencies = ApplicationComponent.class)
public interface ArticleReadComponent {
    void inject(NewsDetailActivity articleReadActivity);
    void inject(ImageBrowseActivity browseActivity);
}
