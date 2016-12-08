package cn.cfanr.guidepage.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * author: xifan
 * date: 2016/12/5
 * desc:
 */
public class GuidePageManager {

    private GuidePageManager(){

    }
    /**
     * @param activity
     * @param pageKey 使用时，传入的值必须和GuidePage设置的值一样
     * @return
     */
    public static boolean hasNotShowed(Activity activity, String pageKey){
        return !hasShowedGuidePage(activity, pageKey);
    }

    public static void setHasShowedGuidePage(Context context, String key, boolean hasShowed){
        SharedPreferences settings= context.getSharedPreferences(context.getPackageName(), 0);
        SharedPreferences.Editor editor=settings.edit();
        editor.putBoolean(key, hasShowed);
        editor.commit();
    }

    private static boolean hasShowedGuidePage(Context context, String key){
        SharedPreferences settings=context.getSharedPreferences(context.getPackageName(), 0);
        boolean value=settings.getBoolean(key, false);
        return value;
    }
}
