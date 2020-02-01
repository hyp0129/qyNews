package com.zhuoren.qyNews;



import com.zhuoren.qyNews.component.ApplicationComponent;
import com.zhuoren.qyNews.component.DaggerApplicationComponent;
import com.zhuoren.qyNews.data.model.DBHelper;
import com.zhuoren.qyNews.module.ApplicationModule;
import com.zhuoren.qyNews.module.HttpModule;
import com.zhuoren.qyNews.utils.ContextUtils;
//import com.zhuoren.zr10.component.DaggerApplicationComponent;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;


/** * desc:
 * author: Zhuoren.
 * date: 2018/4/2 .
 */
public class MyApp extends LitePalApplication {


    private static MyApp sMyApp;

    private ApplicationComponent mApplicationComponent;
    public static int width = 0;
    public static int height = 0;


    @Override
    public void onCreate() {
        super.onCreate();
        sMyApp = this;
        BGASwipeBackManager.getInstance().init(this);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .httpModule(new HttpModule())
                .build();
        LitePal.initialize(this);
        width = ContextUtils.getSreenWidth(MyApp.getContext());
        height = ContextUtils.getSreenHeight(MyApp.getContext());


    }

        public static MyApp getInstance() {
            return sMyApp;
        }
        public ApplicationComponent getApplicationComponent(){
            return mApplicationComponent;
        }

        //服务于登录注册模块
        private int flag;
        private DBHelper dbHelper=new DBHelper(this,"db_qy",null,1);
        public void setFlag(int flag){
        this.flag=flag;
    }
        public int getFlag(){
        return this.flag;
    }
        public DBHelper getDbHelper(){
        return this.dbHelper;
    }


        private HashSet<String> selectedLables = new HashSet<>();

        public HashSet<String> getSelectedLables() {
            return selectedLables;
        }
}
