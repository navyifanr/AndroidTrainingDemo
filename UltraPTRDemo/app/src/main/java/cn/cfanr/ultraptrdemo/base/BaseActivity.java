package cn.cfanr.ultraptrdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author xifan
 * @time 2016/5/4
 * @desc
 */
public abstract class BaseActivity extends AppCompatActivity{

    private long firstClick;
    private long lastClick;
    private int count;  // 计算点击的次数

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(getLayoutResId());
        initView();
        initEvent();
    }

    public void setBaseContentView(@LayoutRes int layoutResId){
        setContentView(layoutResId);
    }

    public void setDoubleClickBarToTop(Toolbar toolbar, final RecyclerView recyclerView){
        toolbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 如果第二次点击 距离第一次点击时间过长 那么将第二次点击看为第一次点击
                        if (firstClick != 0 && System.currentTimeMillis() - firstClick > 300) {
                            count = 0;
                        }
                        count++;
                        if (count == 1) {
                            firstClick = System.currentTimeMillis();
                        } else if (count == 2) {
                            lastClick = System.currentTimeMillis();
                            // 两次点击小于300ms 也就是连续点击
                            if (lastClick - firstClick < 300) {// 判断是否是执行了双击事件
                                recyclerView.scrollToPosition(0);
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });
    }

    protected abstract int getLayoutResId();

    public abstract void initView();

    public abstract void initEvent();

    public <T extends View>T $(@IdRes int resId){
        return (T) super.findViewById(resId);
    }

    public Activity getActivity(){
        return this;
    }


    public String getClassMethodName(){
        String currentMethodName="";
        try {
            currentMethodName= this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[3].getMethodName();
        }catch (Exception e){
            currentMethodName=this.getClass().getName()+"."+Thread.currentThread().getStackTrace()[0].getMethodName();
        }
        Log.e("BaseActivity", currentMethodName);
        return currentMethodName;
    }

}
