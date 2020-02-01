// Generated code from Butter Knife. Do not modify!
package com.zhuoren.qyNews.ui.news;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.internal.Utils;
import com.flyco.tablayout.SlidingTabLayout;
import com.zhuoren.qyNews.R;
import com.zhuoren.qyNews.ui.base.BaseFragment_ViewBinding;
import com.zhuoren.qyNews.widget.CustomViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NewsListFragment_ViewBinding extends BaseFragment_ViewBinding {
  private NewsListFragment target;

  @UiThread
  public NewsListFragment_ViewBinding(NewsListFragment target, View source) {
    super(target, source);

    this.target = target;

    target.mViewpager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'mViewpager'", CustomViewPager.class);
    target.SlidingTabLayout = Utils.findRequiredViewAsType(source, R.id.SlidingTabLayout, "field 'SlidingTabLayout'", SlidingTabLayout.class);
  }

  @Override
  public void unbind() {
    NewsListFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mViewpager = null;
    target.SlidingTabLayout = null;

    super.unbind();
  }
}
