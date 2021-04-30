package com.yhyy.qwframe.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

public class GlideUtil {
    //N（Normal）
    //L（Local）
    //I（Image）
    //C（Circle）
    //B（Blur）高斯模糊

    public static void GlideNI(Context context, String imgUrl, int emptyImg, int errorImg, ImageView imageView) {
        Glide.with(context).load(imgUrl)
                .placeholder(emptyImg).error(errorImg)
                .centerCrop().into(imageView);
    }

    public static void GlideLI(Context context, int imgUrl, int emptyImg, int errorImg, ImageView imageView) {
        Glide.with(context).load(imgUrl)
                .placeholder(emptyImg).error(errorImg)
                .centerCrop().into(imageView);
    }

    public static void GlideCI(Context context, String imgUrl, int emptyImg, int errorImg, ImageView imageView) {
        Glide.with(context).load(imgUrl)
                .placeholder(emptyImg).error(errorImg)
                .centerCrop().apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imageView);
    }

    public static void GlideBI(Context context, String imgUrl, int emptyImg, int errorImg, ImageView imageView) {
        Glide.with(context).load(imgUrl)
                .placeholder(emptyImg).error(errorImg)
                .transform(new BlurUtil(context, 100))
                .into(imageView);
    }
}
