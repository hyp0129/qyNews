package com.zhuoren.qyNews.ui.news.contract;

import com.zhuoren.qyNews.bean.NewsDetailBean;
import com.zhuoren.qyNews.ui.base.BaseContract;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 14:53 .
 */
public interface ArticleReadContract {

    interface View extends BaseContract.BaseView{
        void loadData(NewsDetailBean articleBean);

    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        void getData(String aid);

    }


}
