package com.zhuoren.qyNews.ui.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.zhuoren.qyNews.R;
import com.zhuoren.qyNews.component.ApplicationComponent;
import com.zhuoren.qyNews.component.DaggerNewsComponent;
import com.zhuoren.qyNews.ui.base.BaseFragment;
import com.zhuoren.qyNews.ui.news.contract.NewsContract;
import com.zhuoren.qyNews.ui.news.presenter.NewsListPresenter;

import com.zhuoren.qyNews.widget.CustomViewPager;


import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;



/**
 * desc: 新闻页面 .
 * author: Zhuoren.
 * date: 2018/8/1 15:16 .
 */

public class NewsListFragment extends BaseFragment<NewsListPresenter> implements NewsContract.View {

    @BindView(R.id.viewpager)
    CustomViewPager mViewpager;

    @BindView(R.id.SlidingTabLayout)
    com.flyco.tablayout.SlidingTabLayout SlidingTabLayout;

    private ChannelPagerAdapter mChannelPagerAdapter;

    private List<Channel> mSelectedDatas;
    private List<Channel> mUnSelectedDatas;

    private int selectedIndex;
    private String selectedChannel;

    public static NewsListFragment newInstance() {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_news_new;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {
        DaggerNewsComponent.builder()
                .applicationComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedIndex = position;
                selectedChannel = mSelectedDatas.get(position).getChannelName();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void initData() {
        mSelectedDatas = new ArrayList<>();
        mUnSelectedDatas = new ArrayList<>();
        mPresenter.getChannel();
    }

    @Override
    public void onRetry() {

    }

    @Override
    public void loadData(List<Channel> channels, List<Channel> unSelectedDatas) {
        if (channels != null) {
            mSelectedDatas.clear();

            //删除一些频道
            for(int i=0;i<channels.size();i++){
                if(channels.get(i).getChannelId().equals("JS83,FOCUSJS83")){
                    channels.remove(i);
                }
                if(channels.get(i).getChannelId().equals("SS78,FOCUSSS78")){
                    channels.remove(i);
                }
                if (channels.get(i).getChannelId().equals("SH133,FOCUSSH133")){
                    channels.remove(i);
                }
                if (channels.get(i).getChannelId().equals("TW73")){
                    channels.remove(i);
                }
                if (channels.get(i).getChannelId().equals("XZ09,FOCUSXZ09")){
                    channels.remove(i);
                }
            }

            //保留前5个频道
            for (int z=channels.size()-1;z>=5;z--){
                channels.remove(z);
            }
            for(int j=0;j<channels.size();j++){
                Log.d("channels",""+channels.get(j).getChannelName());
            }


            mSelectedDatas.addAll(channels);
            mUnSelectedDatas.clear();
            mUnSelectedDatas.addAll(unSelectedDatas);
            mChannelPagerAdapter = new ChannelPagerAdapter(getChildFragmentManager(), channels);
            mViewpager.setAdapter(mChannelPagerAdapter);
            mViewpager.setOffscreenPageLimit(2);
            mViewpager.setCurrentItem(0, false);
            SlidingTabLayout.setViewPager(mViewpager);
        } else {
            T("数据异常");
        }
    }



}
