package com.zhuoren.qyNews.ui.news.contract;

import com.zhuoren.qyNews.ui.base.BaseContract;

import java.util.List;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 15:05 .
 */
public interface NewsContract {

    interface View extends BaseContract.BaseView{
        void loadData(List<Channel> channels, List<Channel> otherChannels);

    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        /**
         * 初始化频道
         */
        void getChannel();

    }

}
