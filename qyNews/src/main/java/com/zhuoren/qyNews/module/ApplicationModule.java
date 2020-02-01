package com.zhuoren.qyNews.module;

import android.content.Context;

import com.zhuoren.qyNews.MyApp;

import dagger.Module;
import dagger.Provides;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 9:10 .
 */
@Module
public class ApplicationModule {

    private Context mContext;
    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    MyApp provideApplication() {
        return (MyApp) mContext.getApplicationContext();
    }
    @Provides
    Context provideContext() {
        return mContext;
    }
}
