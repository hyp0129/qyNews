package com.zhuoren.qyNews.ui.news.presenter;

import android.util.Log;

import com.zhuoren.qyNews.MyApp;
import com.zhuoren.qyNews.R;
import com.zhuoren.qyNews.database.ChannelDao;
import com.zhuoren.qyNews.ui.base.BasePresenter;
import com.zhuoren.qyNews.ui.news.contract.NewsContract;

import org.litepal.crud.DataSupport;
import org.litepal.crud.callback.SaveCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;


/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/4/7 .
 */
public class NewsListPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {

    @Inject
    public NewsListPresenter() {
    }

    @Override
    public void getChannel() {
        List<Channel> channelList;
        List<Channel> myChannels = new ArrayList<>();
        List<Channel> otherChannels = new ArrayList<>();
        channelList = ChannelDao.getChannels();
        Log.d("channelList","="+channelList.size());
        if (channelList.size() < 1) {
            List<String> channelName = Arrays.asList(MyApp.getContext().getResources()
                    .getStringArray(R.array.news_channel));
            List<String> channelId = Arrays.asList(MyApp.getContext().getResources()
                    .getStringArray(R.array.news_channel_id));
            List<Channel> channels = new ArrayList<>();

            for (int i = 0; i < channelName.size(); i++) {
                Channel channel = new Channel();
                channel.setChannelId(channelId.get(i));
                channel.setChannelName(channelName.get(i));
                channel.setChannelType(i < 1 ? 1 : 0);
                channel.setChannelSelect(i < channelId.size() - 3);
                Log.d("test","没有执行");
                if (i < channelId.size() - 3) {
                    myChannels.add(channel);
                } else {
                    otherChannels.add(channel);
                }
                channels.add(channel);
            }

            DataSupport.saveAllAsync(channels).listen(new SaveCallback() {
                @Override
                public void onFinish(boolean success) {
                }
            });

            channelList = new ArrayList<>();
            channelList.addAll(channels);
            Log.d("test","channelList.size()<1");
        } else {
            channelList = ChannelDao.getChannels();
            Log.d("channelList","="+channelList.size());
            Iterator<Channel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                Channel channel = iterator.next();
                if (!channel.isChannelSelect()) {
                    otherChannels.add(channel);
                    iterator.remove();
                    Log.d("test","没被选中");
                }
            }
            myChannels.addAll(channelList);
            Log.d("test","channelList.size()>=1");
        }
        Log.d("myChannels","size="+myChannels.size());
        Log.d("otherChannels","size="+otherChannels.size());
        mView.loadData(myChannels, otherChannels);
    }
}
