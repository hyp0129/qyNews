package com.zhuoren.qyNews.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuoren.qyNews.component.ApplicationComponent;


/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 14:17 .
 */
public interface IBase {

    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    View getView();
    int getContentLayout();
    void initInjector(ApplicationComponent appComponent);
    void bindView(View view, Bundle savedInstanceState);
    void initData();

}
