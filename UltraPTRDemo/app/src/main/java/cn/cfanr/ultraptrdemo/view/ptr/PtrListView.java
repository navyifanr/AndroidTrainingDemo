package cn.cfanr.ultraptrdemo.view.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import cn.cfanr.ultraptrdemo.R;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * @author xifan
 * @time 2016/6/2
 * @desc
 */
public class PtrListView extends FrameLayout {
    private PtrFrameLayout mPtrFrame;
    private ListView mListView;
    private PtrCustomHeader ptrHeader;

    private PtrLoadMoreListener ptrLoadMoreListener;

    public void setPtrLoadMoreListener(PtrLoadMoreListener ptrLoadMoreListener){
        this.ptrLoadMoreListener=ptrLoadMoreListener;
    }

    public PtrListView(Context context) {
        super(context);
        initViews();
    }

    public PtrListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public PtrListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews(){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.ptr_list_view, null);  //not 'this'
        mListView= (ListView) view.findViewById(R.id.ptr_list_view_base);

        mPtrFrame= (PtrFrameLayout) view.findViewById(R.id.ptr_frame_list_view);

        ptrHeader=new PtrCustomHeader(getContext());
        ptrHeader.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        ptrHeader.onUIReset(mPtrFrame);

        mPtrFrame.setLoadingMinTime(500);
        mPtrFrame.setDurationToCloseHeader(500);
        mPtrFrame.setHeaderView(ptrHeader);
        mPtrFrame.addPtrUIHandler(ptrHeader);
        // frame.setPullToRefresh(true);
//        mPtrFrame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mPtrFrame.autoRefresh(true);
//            }
//        }, 100);

        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                if(ptrLoadMoreListener!=null) {
                    ptrLoadMoreListener.onRefresh();
                }
            }
        });
        this.addView(view);
    }

    public void setRefreshComplete(){
        mPtrFrame.refreshComplete();
    }

    public void autoRefresh(){
        mPtrFrame.autoRefresh(true);
    }

    public void setAdapter(ListAdapter adapter){
        if(adapter!=null){
            mListView.setAdapter(adapter);
        }
    }
}
