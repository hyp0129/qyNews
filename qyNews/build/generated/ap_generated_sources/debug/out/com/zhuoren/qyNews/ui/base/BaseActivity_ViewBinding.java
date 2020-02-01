// Generated code from Butter Knife. Do not modify!
package com.zhuoren.qyNews.ui.base;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zhuoren.qyNews.R;

import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseActivity_ViewBinding implements Unbinder {
  private BaseActivity target;

  @UiThread
  public BaseActivity_ViewBinding(BaseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BaseActivity_ViewBinding(BaseActivity target, View source) {
    this.target = target;

    target.mSimpleMultiStateView = Utils.findOptionalViewAsType(source, R.id.SimpleMultiStateView, "field 'mSimpleMultiStateView'", SimpleMultiStateView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BaseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mSimpleMultiStateView = null;
  }
}
