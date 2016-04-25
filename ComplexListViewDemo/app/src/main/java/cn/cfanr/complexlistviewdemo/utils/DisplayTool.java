package cn.cfanr.complexlistviewdemo.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import cn.cfanr.complexlistviewdemo.app.AppController;

/**
 * Created by xifan on 2016/4/22.
 */
public class DisplayTool {
    private int wDip; // 屏幕宽度的dip
    private int hDip; // 屏幕长度的dip
    private int wScreen; // 获取屏幕的px
    private int hScreen; // 获取屏幕的px
    public  Context con;
    private Context context= AppController.getInstance();

    public int getwDip() {
        return wDip;
    }

    public int gethDip() {
        return hDip;
    }

    public int getwScreen() {
        return wScreen;
    }

    public int gethScreen() {
        return hScreen;
    }

    public  DisplayTool(){
        con = context;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        wScreen = dm.widthPixels; // 屏幕宽度的px
        hScreen = dm.heightPixels; // 屏幕宽度的px
        wDip = px2dip(wScreen); // 屏幕宽度的dip
        hDip = px2dip(hScreen); // 屏幕长度的dip
    }

    public  DisplayTool(Context context){
        con=context;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        wScreen = dm.widthPixels; // 屏幕宽度的px
        hScreen = dm.heightPixels; // 屏幕宽度的px
        wDip = px2dip(wScreen); // 屏幕宽度的dip
        hDip = px2dip(hScreen); // 屏幕长度的dip
    }
    public int dip2px(double dipValue) {

        float scale = con.getResources().getDisplayMetrics().density;

        return (int) (dipValue * scale + 0.5f);
    }

    public int px2dip(double pxValue) {

        float scale = con.getResources().getDisplayMetrics().density;

        return (int) (pxValue / scale + 0.5f);

    }
}
