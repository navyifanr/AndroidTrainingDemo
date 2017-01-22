package cn.cfanr.ultraptrdemo.view.ptr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import cn.cfanr.ultraptrdemo.R;
import cn.cfanr.ultraptrdemo.view.loadmore.LoadMoreContainer;
import cn.cfanr.ultraptrdemo.view.loadmore.LoadMoreHandler;
import cn.cfanr.ultraptrdemo.view.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * @author xifan
 * @time 2016/6/15
 * @desc
 */
public class PtrListView extends FrameLayout {
    private PtrFrameLayout mPtrFrame;
    private LoadMoreListViewContainer mLoadMoreContainer;
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
        mPtrFrame.setLoadingMinTime(500);
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

        ptrHeader=new PtrCustomHeader(getContext());
        ptrHeader.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        ptrHeader.onUIReset(mPtrFrame);
        mPtrFrame.setHeaderView(ptrHeader);
        mPtrFrame.addPtrUIHandler(ptrHeader);

        mLoadMoreContainer = (LoadMoreListViewContainer) view.findViewById(R.id.ptr_list_view_load_more_container);
        mLoadMoreContainer.useDefaultFooter();
        mLoadMoreContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                if(ptrLoadMoreListener!=null) {
                    ptrLoadMoreListener.onLoadMore();
                }
            }
        });

        this.addView(view);
    }

    public void refreshComplete(){
        mPtrFrame.refreshComplete();
    }

    public void loadMoreFinish(boolean emptyResult, boolean hasMore){
        mLoadMoreContainer.loadMoreFinish(emptyResult, hasMore);
    }

    public void autoRefresh(){
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh(true);
            }
        }, 150);
    }

    public void setAdapter(BaseAdapter adapter){
        if(adapter!=null){
            mListView.setAdapter(adapter);
        }
    }

    public void setMode(Mode mode){
        if(mode== Mode.REFRESH){
            mLoadMoreContainer.removeDefalutFooter();
        }else  if(mode==Mode.BOTH){
            return;
        }
    }

    public static enum Mode {
        REFRESH,
//        LOAD_MORE,
        BOTH
    }
}
