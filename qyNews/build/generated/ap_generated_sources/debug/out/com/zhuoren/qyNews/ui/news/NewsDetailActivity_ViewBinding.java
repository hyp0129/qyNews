// Generated code from Butter Knife. Do not modify!
package com.zhuoren.qyNews.ui.news;

import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zhuoren.qyNews.R;
import com.zhuoren.qyNews.ui.base.BaseActivity_ViewBinding;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewsDetailActivity_ViewBinding extends BaseActivity_ViewBinding {
  private NewsDetailActivity target;

  private View view2131296386;

  @UiThread
  public NewsDetailActivity_ViewBinding(NewsDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NewsDetailActivity_ViewBinding(final NewsDetailActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mIvLogo = Utils.findRequiredViewAsType(source, R.id.iv_logo, "field 'mIvLogo'", ImageView.class);
    target.mTvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'mTvName'", TextView.class);
    target.mTvUpdateTime = Utils.findRequiredViewAsType(source, R.id.tv_updateTime, "field 'mTvUpdateTime'", TextView.class);
    target.mWebView = Utils.findRequiredViewAsType(source, R.id.webview, "field 'mWebView'", WebView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'mIvBack' and method 'onViewClicked'");
    target.mIvBack = Utils.castView(view, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    view2131296386 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.mScrollView = Utils.findRequiredViewAsType(source, R.id.ScrollView, "field 'mScrollView'", NestedScrollView.class);
    target.mConstraintLayout = Utils.findRequiredViewAsType(source, R.id.ConstraintLayout, "field 'mConstraintLayout'", RelativeLayout.class);
    target.mRlTop = Utils.findRequiredViewAsType(source, R.id.rl_top, "field 'mRlTop'", RelativeLayout.class);
    target.mIvTopLogo = Utils.findRequiredViewAsType(source, R.id.iv_topLogo, "field 'mIvTopLogo'", ImageView.class);
    target.mTvTopName = Utils.findRequiredViewAsType(source, R.id.tv_topname, "field 'mTvTopName'", TextView.class);
    target.mTvTopUpdateTime = Utils.findRequiredViewAsType(source, R.id.tv_TopUpdateTime, "field 'mTvTopUpdateTime'", TextView.class);
  }

  @Override
  public void unbind() {
    NewsDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvTitle = null;
    target.mIvLogo = null;
    target.mTvName = null;
    target.mTvUpdateTime = null;
    target.mWebView = null;
    target.mIvBack = null;
    target.mScrollView = null;
    target.mConstraintLayout = null;
    target.mRlTop = null;
    target.mIvTopLogo = null;
    target.mTvTopName = null;
    target.mTvTopUpdateTime = null;

    view2131296386.setOnClickListener(null);
    view2131296386 = null;

    super.unbind();
  }
}
