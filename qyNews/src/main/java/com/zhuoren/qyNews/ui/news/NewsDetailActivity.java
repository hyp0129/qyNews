package com.zhuoren.qyNews.ui.news;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.zhuoren.qyNews.R;
import com.zhuoren.qyNews.bean.NewsDetailBean;
import com.zhuoren.qyNews.component.ApplicationComponent;
import com.zhuoren.qyNews.component.DaggerArticleReadComponent;
import com.zhuoren.qyNews.ui.base.BaseActivity;
import com.zhuoren.qyNews.ui.news.contract.ArticleReadContract;
import com.zhuoren.qyNews.ui.news.presenter.NewsDetailPresenter;
import com.zhuoren.qyNews.utils.DateUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 15:10 .
 * date: 2018/8/1 15:10 .
 */
public class NewsDetailActivity extends BaseActivity<NewsDetailPresenter> implements ArticleReadContract.View {

    private static final String TAG = "NewsDetailActivity";

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_updateTime)
    TextView mTvUpdateTime;
    @BindView(R.id.webview)
    WebView mWebView;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.ScrollView)
    NestedScrollView mScrollView;
    @BindView(R.id.ConstraintLayout)
    RelativeLayout mConstraintLayout;
    @BindView(R.id.rl_top)
    RelativeLayout mRlTop;
    @BindView(R.id.iv_topLogo)
    ImageView mIvTopLogo;
    @BindView(R.id.tv_topname)
    TextView mTvTopName;
    @BindView(R.id.tv_TopUpdateTime)
    TextView mTvTopUpdateTime;

    @Override
    public int getContentLayout() {
        return R.layout.activity_article_read;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {
        DaggerArticleReadComponent.builder()
                .applicationComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setWebViewSetting();
        setStatusBarColor(Color.parseColor("#BDBDBD"), 30);
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > mConstraintLayout.getHeight()) {
                    mRlTop.setVisibility(View.VISIBLE);
                } else {
                    mRlTop.setVisibility(View.GONE);

                }
            }
        });
    }

    private void setWebViewSetting() {
        addjs(mWebView);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setVerticalScrollbarOverlay(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setHorizontalScrollbarOverlay(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.loadUrl("file:///android_asset/ifeng/post_detail.html");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                下面是动态发送网络请求的代码 12月08日
                String aid = getIntent().getStringExtra("aid");
                mPresenter.getData(aid);
            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    public void onRetry() {
        String aid = getIntent().getStringExtra("aid");
        mPresenter.getData(aid);
    }

    @Override
    public void loadData(final NewsDetailBean articleBean) {
        mTvTitle.setText(articleBean.getBody().getTitle());
        mTvUpdateTime.setText(DateUtil.getTimestampString(DateUtil.string2Date(articleBean.getBody().getUpdateTime(), "yyyy/MM/dd HH:mm:ss")));
        if (articleBean.getBody().getSubscribe() != null) {
            Glide.with(this).load(articleBean.getBody().getSubscribe().getLogo())
                    .apply(new RequestOptions()
                            .transform(new CircleCrop())
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(mIvLogo);
            Glide.with(this).load(articleBean.getBody().getSubscribe().getLogo())
                    .apply(new RequestOptions()
                            .transform(new CircleCrop())
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(mIvTopLogo);
            mTvTopName.setText(articleBean.getBody().getSubscribe().getCateSource());
            mTvName.setText(articleBean.getBody().getSubscribe().getCateSource());
            mTvTopUpdateTime.setText(articleBean.getBody().getSubscribe().getCatename());
        } else {
            mTvTopName.setText(articleBean.getBody().getSource());
            mTvName.setText(articleBean.getBody().getSource());
            mTvTopUpdateTime.setText(!TextUtils.isEmpty(articleBean.getBody().getAuthor()) ? articleBean.getBody().getAuthor() : articleBean.getBody().getEditorcode());
        }
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                final String content = articleBean.getBody().getText();
                String url = "javascript:show_content(\'" + content + "\')";
                mWebView.loadUrl(url);
                showSuccess();
            }
        });
    }


    private void addjs(final WebView webview) {

        class JsObject {
            @JavascriptInterface
            public void jsFunctionimg(final String i) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "run: " + i);
                    }
                });

            }

        }
        webview.addJavascriptInterface(new JsObject(), "jscontrolimg");

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }


}
