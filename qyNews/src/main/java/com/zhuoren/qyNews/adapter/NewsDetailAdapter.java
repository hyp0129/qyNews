package com.zhuoren.qyNews.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhuoren.qyNews.R;
import com.zhuoren.qyNews.bean.NewsListItemBean;
import com.zhuoren.qyNews.utils.ImageLoaderUtil;

import java.util.List;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 10:38 .
 */
public class NewsDetailAdapter extends BaseMultiItemQuickAdapter<NewsListItemBean.ItemBean, BaseViewHolder> {
    private Context mContext;


    public NewsDetailAdapter(List<NewsListItemBean.ItemBean> data, Context context) {
        super(data);
        this.mContext = context;
        addItemType(NewsListItemBean.ItemBean.TYPE_DOC_TITLEIMG, R.layout.item_detail_doc);
        addItemType(NewsListItemBean.ItemBean.TYPE_DOC_SLIDEIMG, R.layout.item_detail_doc_slideimg);
        addItemType(NewsListItemBean.ItemBean.TYPE_SLIDE, R.layout.item_detail_slide);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsListItemBean.ItemBean bean) {
        switch (baseViewHolder.getItemViewType()) {
            case NewsListItemBean.ItemBean.TYPE_DOC_TITLEIMG:
                baseViewHolder.setText(R.id.tv_title, bean.getTitle());
                baseViewHolder.setText(R.id.tv_source, bean.getSource());
//                baseViewHolder.setText(R.id.tv_commnetsize,
//                        String.format(mContext.getResources().getString(R.string.news_commentsize), bean.getCommentsall()));
                ImageLoaderUtil.LoadImage(mContext, bean.getThumbnail(), (ImageView) baseViewHolder.getView(R.id.iv_logo));
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsListItemBean.ItemBean.TYPE_DOC_SLIDEIMG:
                baseViewHolder.setText(R.id.tv_title, bean.getTitle());
                baseViewHolder.setText(R.id.tv_source, bean.getSource());
//                baseViewHolder.setText(R.id.tv_commnetsize,
//                        String.format(mContext.getResources().getString(R.string.news_commentsize), bean.getCommentsall()));
                try {
                    ImageLoaderUtil.LoadImage(mContext, bean.getStyle().getImages().get(0), (ImageView) baseViewHolder.getView(R.id.iv_1));
                    ImageLoaderUtil.LoadImage(mContext, bean.getStyle().getImages().get(1), (ImageView) baseViewHolder.getView(R.id.iv_2));
                    ImageLoaderUtil.LoadImage(mContext, bean.getStyle().getImages().get(2), (ImageView) baseViewHolder.getView(R.id.iv_3));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;
            case NewsListItemBean.ItemBean.TYPE_SLIDE:
                baseViewHolder.setText(R.id.tv_title, bean.getTitle());
                baseViewHolder.setText(R.id.tv_source, bean.getSource());
//                baseViewHolder.setText(R.id.tv_commnetsize,
//                        String.format(mContext.getResources().getString(R.string.news_commentsize), bean.getCommentsall()));
                ImageLoaderUtil.LoadImage(mContext, bean.getThumbnail(), (ImageView) baseViewHolder.getView(R.id.iv_logo));
                baseViewHolder.addOnClickListener(R.id.iv_close);
                break;


        }
    }
}
