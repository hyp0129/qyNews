// Generated code from Butter Knife. Do not modify!
package com.zhuoren.qyNews.ui.news;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.zhuoren.qyNews.R;
import com.zhuoren.qyNews.ui.base.BaseActivity_ViewBinding;

import java.lang.IllegalStateException;
import java.lang.Override;

public class ImageBrowseActivity_ViewBinding extends BaseActivity_ViewBinding {
  private ImageBrowseActivity target;

  private View view2131296318;

  @UiThread
  public ImageBrowseActivity_ViewBinding(ImageBrowseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ImageBrowseActivity_ViewBinding(final ImageBrowseActivity target, View source) {
    super(target, source);

    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_titlebar_left, "field 'mBtnTitlebarLeft' and method 'onViewClicked'");
    target.mBtnTitlebarLeft = Utils.castView(view, R.id.btn_titlebar_left, "field 'mBtnTitlebarLeft'", ImageView.class);
    view2131296318 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.mTvTitlebarName = Utils.findRequiredViewAsType(source, R.id.tv_titlebar_name, "field 'mTvTitlebarName'", TextView.class);
    target.mRlTop = Utils.findRequiredViewAsType(source, R.id.rl_top, "field 'mRlTop'", RelativeLayout.class);
    target.mTvInfo = Utils.findRequiredViewAsType(source, R.id.tv_info, "field 'mTvInfo'", TextView.class);
    target.mScrollview = Utils.findRequiredViewAsType(source, R.id.scrollview, "field 'mScrollview'", MyScrollView.class);
    target.mRelativeLayout = Utils.findRequiredViewAsType(source, R.id.relativeLayout, "field 'mRelativeLayout'", RelativeLayout.class);
    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'mViewPager'", HackyViewPager.class);
    target.mSwipeBackLayout = Utils.findRequiredViewAsType(source, R.id.swipe_layout, "field 'mSwipeBackLayout'", SwipeBackLayout.class);
  }

  @Override
  public void unbind() {
    ImageBrowseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBtnTitlebarLeft = null;
    target.mTvTitlebarName = null;
    target.mRlTop = null;
    target.mTvInfo = null;
    target.mScrollview = null;
    target.mRelativeLayout = null;
    target.mViewPager = null;
    target.mSwipeBackLayout = null;

    view2131296318.setOnClickListener(null);
    view2131296318 = null;

    super.unbind();
  }
}
