package com.zhuoren.qyNews;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zhuoren.qyNews.component.ApplicationComponent;
import com.zhuoren.qyNews.ui.base.BaseActivity;
import com.zhuoren.qyNews.ui.personal.PersonalFragment;


public class MainActivity extends BaseActivity implements PersonalFragment.OnFragmentInteractionListener {

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initInjector(ApplicationComponent appComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

            loadRootFragment(R.id.fl_container,MainFragment.newInstance());

    }

    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","onPause");
    }

    @Override
    public void finish() {
        super.finish();
        Log.d("MainActivity","finish");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","onDestroy");
    }
}
