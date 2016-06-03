package cn.cfanr.ultraptrdemo.view.ptr;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import cn.cfanr.ultraptrdemo.R;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * @author xifan
 * @time 2016/6/2
 * @desc 自定义的下拉刷新的头部
 */
public class PtrCustomHeader extends FrameLayout implements PtrUIHandler {
    private int mRotateAniTime = 150;
    private RotateAnimation mFlipAnimation;
    private RotateAnimation mReverseFlipAnimation;
    private ImageView mRotateView;
    private ImageView mProgressView;
    private TextView mStateTxt;
    private ImageView mPtrImg;

    public PtrCustomHeader(Context context) {
        super(context);
        initViews();
    }

    public PtrCustomHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public PtrCustomHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews(){
        buildAnimation();
        View header = LayoutInflater.from(getContext()).inflate(R.layout.ptr_custom_header, this);

        mRotateView= (ImageView) header.findViewById(R.id.ptr_header_refresh_arrows);
        mProgressView= (ImageView) header.findViewById(R.id.ptr_header_progress_anim);
        mStateTxt= (TextView) header.findViewById(R.id.ptr_header_state_text);
        mPtrImg= (ImageView) header.findViewById(R.id.ptr_header_ptr_img);

        mProgressView.setImageResource(R.drawable.ptr_header_loading);
        ((AnimationDrawable) mProgressView.getDrawable()).start();

        resetView();
    }

    private void buildAnimation() {
        mFlipAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(mRotateAniTime);
        mFlipAnimation.setFillAfter(true);

        mReverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
        mReverseFlipAnimation.setDuration(mRotateAniTime);
        mReverseFlipAnimation.setFillAfter(true);
    }

    private void resetView() {
        hideRotateView();
        mProgressView.setVisibility(INVISIBLE);
    }

    private void hideRotateView() {
        mRotateView.clearAnimation();
        mRotateView.setVisibility(INVISIBLE);
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        resetView();
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        mProgressView.setVisibility(INVISIBLE);

        mRotateView.setVisibility(VISIBLE);
        mStateTxt.setVisibility(VISIBLE);
        mStateTxt.setText("释放立即刷新...");
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        hideRotateView();
        mProgressView.setVisibility(VISIBLE);
        mStateTxt.setVisibility(INVISIBLE);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        hideRotateView();
        mProgressView.setVisibility(INVISIBLE);

        mStateTxt.setVisibility(VISIBLE);
        mStateTxt.setText("释放立即刷新...");
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();

        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
//                crossRotateLineFromBottomUnderTouch(frame);
                if (mRotateView != null) {
                    mRotateView.clearAnimation();
                    mRotateView.startAnimation(mReverseFlipAnimation);
                }
            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
//                crossRotateLineFromTopUnderTouch(frame);
                if (mRotateView != null) {
                    mRotateView.clearAnimation();
                    mRotateView.startAnimation(mFlipAnimation);
                }
            }
        }
    }

    private void crossRotateLineFromTopUnderTouch(PtrFrameLayout frame) {
        if (!frame.isPullToRefresh()) {
            mStateTxt.setVisibility(VISIBLE);
            mStateTxt.setText(in.srain.cube.views.ptr.R.string.cube_ptr_release_to_refresh);
        }
    }

    private void crossRotateLineFromBottomUnderTouch(PtrFrameLayout frame) {
        mStateTxt.setVisibility(VISIBLE);
        if (frame.isPullToRefresh()) {
            mStateTxt.setText(getResources().getString(in.srain.cube.views.ptr.R.string.cube_ptr_pull_down_to_refresh));
        } else {
            mStateTxt.setText(getResources().getString(in.srain.cube.views.ptr.R.string.cube_ptr_pull_down));
        }
    }
}
