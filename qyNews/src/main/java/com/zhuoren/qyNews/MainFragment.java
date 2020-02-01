package com.zhuoren.qyNews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhuoren.qyNews.component.ApplicationComponent;
import com.zhuoren.qyNews.ui.base.BaseFragment;
import com.zhuoren.qyNews.ui.base.SupportFragment;
import com.zhuoren.qyNews.ui.news.NewsListFragment;
import com.zhuoren.qyNews.ui.personal.PersonalFragment;
import com.zhuoren.qyNews.widget.BottomBar;
import com.zhuoren.qyNews.widget.BottomBarTab;


/**
 * Created by YoKeyword on 16/6/30.
 */
public class MainFragment extends BaseFragment {

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
//    public static final int FOURTH = 3;

    private SupportFragment[] mFragments = new SupportFragment[3];

    private BottomBar mBottomBar;


    public static MainFragment newInstance() {

        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(NewsListFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = NewsListFragment.newInstance();
            mFragments[SECOND] = PersonalFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(PersonalFragment.class);
        }

    }

    private void initView(View view) {

        mBottomBar = (BottomBar) view.findViewById(R.id.bottomBar);
        mBottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_home_page, getString(R.string.home)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_my, getString(R.string.my)));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        initView(view);
    }

    @Override
    public void initData() {

    }
}
