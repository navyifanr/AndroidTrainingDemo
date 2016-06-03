package cn.cfanr.ultraptrdemo.view.ptr;

import android.content.Context;
import android.util.AttributeSet;

import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @author xifan
 * @time 2016/6/2
 * @desc
 */
public class PtrCustomFrameLayout extends PtrFrameLayout {
    private PtrCustomHeader mPtrHeader;
    public PtrCustomFrameLayout(Context context) {
        super(context);
    }

    public PtrCustomFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PtrCustomFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void initViews() {
        mPtrHeader = new PtrCustomHeader(getContext());
        setHeaderView(mPtrHeader);
        addPtrUIHandler(mPtrHeader);
    }

    public PtrCustomHeader getHeader() {
        return mPtrHeader;
    }
}
