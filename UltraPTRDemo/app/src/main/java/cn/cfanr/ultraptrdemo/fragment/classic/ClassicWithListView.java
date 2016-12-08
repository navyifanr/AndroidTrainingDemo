package cn.cfanr.ultraptrdemo.fragment.classic;


import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.cfanr.ultraptrdemo.R;
import cn.cfanr.ultraptrdemo.fragment.BaseFragment;
import cn.cfanr.ultraptrdemo.view.loadmore.LoadMoreContainer;
import cn.cfanr.ultraptrdemo.view.loadmore.LoadMoreHandler;
import cn.cfanr.ultraptrdemo.view.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class ClassicWithListView extends BaseFragment {
    private List<String> data = new ArrayList<>();
    private ArrayAdapter mAdapter;
    private ListView ptrListView;
    private PtrClassicFrameLayout ptrFrame;
    private LoadMoreListViewContainer loadMoreContainer;

    private int pageNo=1;
    public ClassicWithListView() {
        // Required empty public constructor
    }

    public static ClassicWithListView newInstance() {
        ClassicWithListView fragment = new ClassicWithListView();
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_custom_with_list_view;
    }

    @Override
    public void initViews(View layoutView) {
        ptrListView= $(layoutView, R.id.ptr_list_view);
        ptrFrame=$(layoutView, R.id.ptr_frame_layout);
        ptrFrame.setLoadingMinTime(1000);
        ptrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, ptrListView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNo=1;
                updateData(pageNo);
            }
        });

        ptrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrame.autoRefresh(true);
            }
        }, 150);


        loadMoreContainer=$(layoutView, R.id.load_more_list_view_container);
        loadMoreContainer.useDefaultFooter();
        loadMoreContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                pageNo++;
                updateData(pageNo);
            }
        });
        ptrListView.setAdapter(mAdapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, data));
    }

    private void updateData(final int pageNo){
        if(pageNo==1) {
            data.clear();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int index=0;index<10;index++){
                    data.add("第"+pageNo+"页 测试数据  "+index);
                }
                ptrFrame.refreshComplete();
                loadMoreContainer.loadMoreFinish(data.isEmpty(), true);
                mAdapter.notifyDataSetChanged();
            }
        }, 500);
    }

}
