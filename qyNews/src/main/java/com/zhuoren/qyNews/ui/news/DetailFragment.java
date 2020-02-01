package com.zhuoren.qyNews.ui.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import com.zhuoren.qyNews.R;
import com.zhuoren.qyNews.bean.NewsListItemBean;
import com.zhuoren.qyNews.component.ApplicationComponent;
import com.zhuoren.qyNews.component.DaggerDetailComponent;
import com.zhuoren.qyNews.net.NewsApi;
import com.zhuoren.qyNews.net.NewsUtils;
import com.zhuoren.qyNews.adapter.NewsDetailAdapter;
import com.zhuoren.qyNews.ui.base.BaseFragment;
import com.zhuoren.qyNews.ui.news.contract.DetailContract;
import com.zhuoren.qyNews.ui.news.presenter.DetailPresenter;
import com.zhuoren.qyNews.utils.ImageLoaderUtil;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 15:12 .
 */
public class DetailFragment extends BaseFragment<DetailPresenter> implements DetailContract.View {
    private static final String TAG = "DetailFragment";


    @BindView(R.id.mPtrFrameLayout)
    PtrFrameLayout mPtrFrameLayout;
    @BindView(R.id.tv_toast)
    TextView mTvToast;
    @BindView(R.id.rl_top_toast)
    RelativeLayout mRlTopToast;

    private View view_Focus;//顶部banner
    private Banner mBanner;


    //跟加载新闻列表有关的
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private String newsid;
    private int position;
    private List<NewsListItemBean.ItemBean> beanList;
    private NewsDetailAdapter detailAdapter;
    //跟加载新闻列表有关的

    private List<NewsListItemBean.ItemBean> mBannerList;


    private int upPullNum = 1;
    private int downPullNum = 1;
    private boolean isRemoveHeaderView = false;

    public static DetailFragment newInstance(String newsid, int position) {
        Bundle args = new Bundle();
        args.putString("newsid", newsid);
        args.putInt("position", position);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_detail;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {
        DaggerDetailComponent.builder()
                .applicationComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                Log.i(TAG, "onRefreshBegin: " + downPullNum);
                isRemoveHeaderView = true;
                mPresenter.getData(newsid, NewsApi.ACTION_DOWN, downPullNum);
            }
        });

        beanList = new ArrayList<>();
        mBannerList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(detailAdapter = new NewsDetailAdapter(beanList, getActivity()));
        detailAdapter.setEnableLoadMore(true);
        detailAdapter.setLoadMoreView(new CustomLoadMoreView());
        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        detailAdapter.setOnLoadMoreListener( new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.i(TAG, "onLoadMoreRequested: " + upPullNum);
                mPresenter.getData(newsid, NewsApi.ACTION_UP, upPullNum);

            }
        }, mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                NewsListItemBean.ItemBean itemBean = (NewsListItemBean.ItemBean) baseQuickAdapter.getItem(i);
                toRead(itemBean);

            }
        });

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                detailAdapter.remove(i);
            }
        });

        view_Focus = getView().inflate(getActivity(), R.layout.news_detail_headerview, null);
        mBanner = (Banner) view_Focus.findViewById(R.id.banner);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        //Glide 加载图片简单用法
                        ImageLoaderUtil.LoadImage(getActivity(), path, imageView);
                    }
                })
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (mBannerList.size() < 1) return;
                bannerToRead(mBannerList.get(position));
            }
        });


    }

    @Override
    public void initData() {
        if (getArguments() == null) return;
        newsid = getArguments().getString("newsid");
        position = getArguments().getInt("position");
        mPresenter.getData(newsid, NewsApi.ACTION_DEFAULT, 1);
    }

    @Override
    public void onRetry() {
        initData();
    }

    @Override
    public void loadBannerData(NewsListItemBean newsDetail) {
        Log.i(TAG, "loadBannerData: " + newsDetail.toString());
        List<String> mTitleList = new ArrayList<>();
        List<String> mUrlList = new ArrayList<>();
        mBannerList.clear();
        for (NewsListItemBean.ItemBean bean : newsDetail.getItem()) {
            if (!TextUtils.isEmpty(bean.getThumbnail())) {
                mTitleList.add(bean.getTitle());
                mBannerList.add(bean);
                mUrlList.add(bean.getThumbnail());
            }
        }
        if (mUrlList.size() > 0) {
            mBanner.setImages(mUrlList);
            mBanner.setBannerTitles(mTitleList);
            mBanner.start();
            if (detailAdapter.getHeaderLayoutCount()<1){
                detailAdapter.addHeaderView(view_Focus);
            }
        }
    }

    @Override
    public void loadTopNewsData(NewsListItemBean newsDetail) {
        Log.i(TAG, "loadTopNewsData: " + newsDetail.toString());
    }

    @Override
    public void loadData(List<NewsListItemBean.ItemBean> itemBeanList) {
        if (itemBeanList == null) {
            showFaild();
        } else {
            downPullNum++;
            if (isRemoveHeaderView) {
                detailAdapter.removeAllHeaderView();
            }
            detailAdapter.setNewData(itemBeanList);
            showToast(itemBeanList.size(), true);
            mPtrFrameLayout.refreshComplete();
            showSuccess();
            Log.i(TAG, "loadData: " + itemBeanList.toString());
        }
    }

    @Override
    public void loadMoreData(List<NewsListItemBean.ItemBean> itemBeanList) {
        if (itemBeanList == null) {
            detailAdapter.loadMoreFail();
        } else {
            upPullNum++;
            detailAdapter.addData(itemBeanList);
            detailAdapter.loadMoreComplete();
            Log.i(TAG, "loadMoreData: " + itemBeanList.toString());
        }
    }

    private void showToast(int num, boolean isRefresh) {
        if (isRefresh) {
            mTvToast.setText(String.format(getResources().getString(R.string.news_toast), num + ""));
        } else {
            mTvToast.setText("将为你减少此类内容");
        }
        mRlTopToast.setVisibility(View.VISIBLE);
        ViewAnimator.animate(mRlTopToast)
                .newsPaper()
                .duration(1000)
                .start()
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        ViewAnimator.animate(mRlTopToast)
                                .bounceOut()
                                .duration(1000)
                                .start();
                    }
                });
    }

    private void bannerToRead(NewsListItemBean.ItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        switch (itemBean.getType()) {
            case NewsUtils.TYPE_DOC:
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("aid", itemBean.getDocumentId());
                startActivity(intent);
                break;
            case NewsUtils.TYPE_SLIDE:
                ImageBrowseActivity.launch(getActivity(), itemBean);
                Log.d("ImageBrowseActivity","ImageBrowseActivity");
                break;

        }
    }

    private void toRead(NewsListItemBean.ItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        switch (itemBean.getItemType()) {
            case NewsListItemBean.ItemBean.TYPE_DOC_TITLEIMG:
            case NewsListItemBean.ItemBean.TYPE_DOC_SLIDEIMG:
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("aid", itemBean.getDocumentId());
                startActivity(intent);
                break;
            case NewsListItemBean.ItemBean.TYPE_SLIDE:
                ImageBrowseActivity.launch(getActivity(), itemBean);
                break;

        }
    }

}
