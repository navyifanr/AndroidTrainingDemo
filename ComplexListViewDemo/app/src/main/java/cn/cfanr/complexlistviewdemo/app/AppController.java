package cn.cfanr.complexlistviewdemo.app;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xifan on 2016/4/22.
 */
public class AppController extends Application {
    private static AppController instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static AppController getInstance() {
        if (null == instance) {
            instance = new AppController();
        }
        return instance;
    }
}
