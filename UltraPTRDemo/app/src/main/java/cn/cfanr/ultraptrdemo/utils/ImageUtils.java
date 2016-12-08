package cn.cfanr.ultraptrdemo.utils;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import cn.cfanr.izhihudaily.app.AppController;

/**
 * @author xifan
 * @time 2016/5/6
 * @desc 图片加载工具类
 */
public class ImageUtils {

    public static void loadImage(ImageView imageView, String url, @DrawableRes int defaultImgId, @DrawableRes int failedImgId){
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, defaultImgId, failedImgId);
        ImageLoader imageLoader=AppController.getInstance().getImageLoader();
        imageLoader.get(url, listener);
    }

    public static void loadImage(ImageView imageView, String url){
        loadImage(imageView, url, 0, 0);
    }

    public static void loadImageBySize(ImageView imageView, String url, int imgWidth, int imgHeight){
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, 0, 0);
        ImageLoader imageLoader=AppController.getInstance().getImageLoader();
        imageLoader.get(url, listener,imgWidth, imgHeight);
    }
}
