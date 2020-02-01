package com.zhuoren.qyNews.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/8/1 15:41 .
 */
public class ImageLoaderUtil {

    /**
     * 常规使用
     *
     * @param context   上下文
     * @param url       图片链接
     * @param imageView 目标view
     */
    public static void LoadImage(Context context, Object url, ImageView imageView) {
        Glide.with(context).load(url)
                .apply(new RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(new DrawableTransitionOptions().crossFade(800))
                .into(imageView);
    }


    /**
     * 需要回调时使用
     *
     * @param context         上下文
     * @param url             图片链接
     * @param imageViewTarget 回调需求
     */
    public static void LoadImage(Context context, Object url, ImageViewTarget imageViewTarget) {
        Glide.with(context).load(url)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .transition(new DrawableTransitionOptions().crossFade(800))
                .into(imageViewTarget);
    }






}
