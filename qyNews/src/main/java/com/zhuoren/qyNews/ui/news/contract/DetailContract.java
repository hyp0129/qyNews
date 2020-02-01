package com.zhuoren.qyNews.ui.news.contract;

import com.zhuoren.qyNews.bean.NewsListItemBean;
import com.zhuoren.qyNews.ui.base.BaseContract;

import java.util.List;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 14:57 .
 */
public interface DetailContract {

    interface View extends BaseContract.BaseView {

        /**
         * 加载顶部banner数据
         *
         * @param newsDetail
         */
        void loadBannerData(NewsListItemBean newsDetail);

        /**
         * 加载置顶新闻数据
         *
         * @param newsDetail
         */
        void loadTopNewsData(NewsListItemBean newsDetail);

        /**
         * 加载新闻数据
         *
         * @param itemBeanList
         */
        void loadData(List<NewsListItemBean.ItemBean> itemBeanList);

        /**
         * 加载更多新闻数据
         *
         * @param itemBeanList
         */
        void loadMoreData(List<NewsListItemBean.ItemBean> itemBeanList);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        /**
         * 获取新闻详细信息
         *
         * @param id      频道ID值
         * @param action  用户操作方式
         *                1：下拉 down
         *                2：上拉 up
         *                3：默认 default
         * @param pullNum 操作次数 累加
         */
        void getData(String id, String action, int pullNum);

    }

}
