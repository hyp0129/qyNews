package com.zhuoren.qyNews.net;

import com.zhuoren.qyNews.bean.NewsListItemBean;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 9:54 .
 */
public class NewsUtils {

    //顶部banner新闻
    public static final String TYPE_BANNER = "focus";
    //置顶新闻
    public static final String TYPE_TOP = "top";
    //常规新闻
    public static final String TYPE_List = "list";
    //文章类型
    public static final String TYPE_DOC = "doc";

    //图片类型
    public static final String TYPE_SLIDE = "slide";

    //显示形式单图
    public static final String VIEW_TITLEIMG = "titleimg";
    //显示形式多图
    public static final String VIEW_SLIDEIMG = "slideimg";

    public static boolean isBannerNews(NewsListItemBean detail) {
        return detail.getType().equals(TYPE_BANNER);
    }

    public static boolean isTopNews(NewsListItemBean detail) {
        return detail.getType().equals(TYPE_TOP);
    }

    public static boolean isListNews(NewsListItemBean detail) {
        return detail.getType().equals(TYPE_List);
    }

}
