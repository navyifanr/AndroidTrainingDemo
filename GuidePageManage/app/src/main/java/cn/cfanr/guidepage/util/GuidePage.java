package cn.cfanr.guidepage.util;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;


/**
 * author: xifan
 * date: 2016/12/6
 * desc:
 */
public class GuidePage {
    private int layoutId;
    private int knowViewId;
    private String pageTag;
    private boolean mCancel = false;

    private Activity activity;
    private FrameLayout rootLayout;
    private View layoutView;
    //设置为 protected或private, 不被外部直接调用
    protected GuidePage(){
    }

    public static class Builder{
        private GuidePage guidePage = new GuidePage();

        public Builder(Activity activity){
            guidePage.activity = activity;
        }

        public Builder setLayoutId(@LayoutRes int layoutId){
            guidePage.layoutId = layoutId;
            return this;
        }

        public Builder setKnowViewId(@IdRes int knowViewId){
            guidePage.knowViewId = knowViewId;
            return this;
        }

        /**
         * 引导唯一的标记，用作存储到SharedPreferences的key值，不同引导页必须不一样
         * @param pageTag
         * @return
         */
        public Builder setPageTag(String pageTag){
            guidePage.pageTag = pageTag;
            return this;
        }

        public Builder setCloseOnTouchOutside(boolean cancel){
            guidePage.mCancel = cancel;
            return this;
        }

        public GuidePage builder(){
            if(TextUtils.isEmpty(guidePage.pageTag)){
                throw new RuntimeException("the guide page must set page tag");
            }
            guidePage.setLayoutView();
            guidePage.setKnowEvent();
            guidePage.setCloseOnTouchOutside();
            return guidePage;
        }
    }

    public void setLayoutView(){
        rootLayout = (FrameLayout) activity.findViewById(android.R.id.content);
        layoutView = View.inflate(activity, layoutId, null);
    }

    public void setKnowEvent(){
        if(layoutView!=null) {
            layoutView.findViewById(knowViewId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                }
            });
        }
    }

    public void setCloseOnTouchOutside(){
        if(layoutView == null)
            return;
        layoutView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mCancel){
                    cancel();
                }
                return true;  //消费事件，不下发
            }
        });
    }

    public void apply(){
        if(GuidePageManager.hasNotShowed(activity, pageTag))
            return;
        rootLayout.addView(layoutView);
    }

    public void cancel(){
        if(rootLayout!=null && layoutView!=null) {
            rootLayout.removeView(layoutView);
            GuidePageManager.setHasShowedGuidePage(activity, pageTag, true);
        }
    }
}
