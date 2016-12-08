package cn.cfanr.ultraptrdemo.view.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import cn.cfanr.ultraptrdemo.R;
import cn.cfanr.ultraptrdemo.view.loadmore.LoadMoreContainer;
import cn.cfanr.ultraptrdemo.view.loadmore.LoadMoreHandler;
import cn.cfanr.ultraptrdemo.view.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * @author xifan
 * @time 2016/6/2
 * @desc
 */
public class PtrClassicListView extends FrameLayout {
    private PtrClassicFrameLayout mPtrFrame;
    private ListView mListView;

    private PtrLoadMoreListener ptrLoadMoreListener;

    public void setPtrLoadMoreListener(PtrLoadMoreListener ptrLoadMoreListener){
        this.ptrLoadMoreListener=ptrLoadMoreListener;
    }

    public PtrClassicListView(Context context) {
        super(context);
        initViews();
    }

    public PtrClassicListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public PtrClassicListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews(){
        View view= LayoutInflater.from(getContext()).inflate(R.layout.ptr_list_view, null);  //not 'this'
        mListView= (ListView) view.findViewById(R.id.ptr_list_view_base);

        mPtrFrame= (PtrClassicFrameLayout) view.findViewById(R.id.ptr_frame_list_view);
        final LoadMoreListViewContainer loadMoreListViewContainer = (LoadMoreListViewContainer) view.findViewById(R.id.ptr_list_view_load_more_container);
        loadMoreListViewContainer.useDefaultFooter();
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                if(ptrLoadMoreListener!=null) {
                    loadMoreContainer.loadMoreFinish(true, true);
                    ptrLoadMoreListener.onLoadMore();
                }
            }
        });

//        ptrHeader=new PtrCustomHeader(getContext());
//        ptrHeader.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
//        ptrHeader.onUIReset(mPtrFrame);

        mPtrFrame.setLoadingMinTime(500);
        mPtrFrame.setDurationToCloseHeader(500);
//        mPtrFrame.setHeaderView(ptrHeader);
//        mPtrFrame.addPtrUIHandler(ptrHeader);


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
                //注意这里是ListView，不是content，不然会出现头部被挡，无法加载更多的情况
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
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
