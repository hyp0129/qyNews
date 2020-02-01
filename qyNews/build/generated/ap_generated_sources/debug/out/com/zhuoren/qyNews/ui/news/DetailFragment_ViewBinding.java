// Generated code from Butter Knife. Do not modify!
package com.zhuoren.qyNews.ui.news;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.zhuoren.qyNews.R;
import com.zhuoren.qyNews.ui.base.BaseFragment_ViewBinding;
import in.srain.cube.views.ptr.PtrFrameLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailFragment_ViewBinding extends BaseFragment_ViewBinding {
  private DetailFragment target;

  @UiThread
  public DetailFragment_ViewBinding(DetailFragment target, View source) {
    super(target, source);

    this.target = target;

    target.mPtrFrameLayout = Utils.findRequiredViewAsType(source, R.id.mPtrFrameLayout, "field 'mPtrFrameLayout'", PtrFrameLayout.class);
    target.mTvToast = Utils.findRequiredViewAsType(source, R.id.tv_toast, "field 'mTvToast'", TextView.class);
    target.mRlTopToast = Utils.findRequiredViewAsType(source, R.id.rl_top_toast, "field 'mRlTopToast'", RelativeLayout.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.mRecyclerView, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  public void unbind() {
    DetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mPtrFrameLayout = null;
    target.mTvToast = null;
    target.mRlTopToast = null;
    target.mRecyclerView = null;

    super.unbind();
  }
}
