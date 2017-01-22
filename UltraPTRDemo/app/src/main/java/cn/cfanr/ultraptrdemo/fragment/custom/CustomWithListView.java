package cn.cfanr.ultraptrdemo.fragment.custom;

import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.cfanr.ultraptrdemo.R;
import cn.cfanr.ultraptrdemo.fragment.BaseFragment;
import cn.cfanr.ultraptrdemo.view.ptr.PtrListView;
import cn.cfanr.ultraptrdemo.view.ptr.PtrLoadMoreListener;

public class CustomWithListView extends BaseFragment {
    private PtrListView mListView;

    private List<String> data = new ArrayList<>();
    private ArrayAdapter mAdapter;
    private int pageNo=1;

    public CustomWithListView() {
        // Required empty public constructor
    }

    public static CustomWithListView newInstance() {
        CustomWithListView fragment = new CustomWithListView();
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_custom_with_list_view;
    }

    @Override
    public void initViews(View layoutView) {
        mListView=$(layoutView, R.id.ptr_classic_list_view);

        mAdapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_expandable_list_item_1, data);
        mListView.setAdapter(mAdapter);
        mListView.autoRefresh();
//        mListView.setMode(PtrListView.Mode.REFRESH);
        mListView.setPtrLoadMoreListener(new PtrLoadMoreListener() {
            @Override
            public void onRefresh() {
                pageNo=1;
                updateData(pageNo);
            }

            @Override
            public void onLoadMore() {
                pageNo++;
                updateData(pageNo);
            }
        });
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
                mListView.refreshComplete();
                mListView.loadMoreFinish(data.isEmpty(), true);
                mAdapter.notifyDataSetChanged();
            }
        }, 500);
    }

}
