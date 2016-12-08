package cn.cfanr.ultraptrdemo.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.cfanr.ultraptrdemo.R;
import cn.cfanr.ultraptrdemo.view.ptr.PtrListView;
import cn.cfanr.ultraptrdemo.view.ptr.PtrLoadMoreListener;

public class MainActivity extends AppCompatActivity {
    List<String> data = new ArrayList<>();
    ArrayAdapter mAdapter;
    PtrListView ptrListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ptrListView= (PtrListView) findViewById(R.id.ptr_list_view);
        ptrListView.setAdapter(mAdapter=new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, data));
        ptrListView.setPtrLoadMoreListener(new PtrLoadMoreListener() {
            @Override
            public void onRefresh() {
                updateData();
            }

            @Override
            public void onLoadMore() {

            }
        });
    }

    private void updateData(){
//        data.clear();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int index=0;index<6;index++){
                    data.add("测试数据  "+index);
                }
                mAdapter.notifyDataSetChanged();
                ptrListView.setRefreshComplete();
            }
        }, 1000);
    }

}

