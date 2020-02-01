// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package com.zhuoren.qyNews.ui.base;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class BaseActivity_MembersInjector<T1 extends BaseContract.BasePresenter>
    implements MembersInjector<BaseActivity<T1>> {
  private final Provider<T1> mPresenterProvider;

  public BaseActivity_MembersInjector(Provider<T1> mPresenterProvider) {
    assert mPresenterProvider != null;
    this.mPresenterProvider = mPresenterProvider;
  }

  public static <T1 extends BaseContract.BasePresenter> MembersInjector<BaseActivity<T1>> create(
      Provider<T1> mPresenterProvider) {
    return new BaseActivity_MembersInjector<T1>(mPresenterProvider);
  }

  @Override
  public void injectMembers(BaseActivity<T1> instance) {
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    instance.mPresenter = mPresenterProvider.get();
  }

  public static <T1 extends BaseContract.BasePresenter> void injectMPresenter(
      BaseActivity<T1> instance, Provider<T1> mPresenterProvider) {
    instance.mPresenter = mPresenterProvider.get();
  }
}