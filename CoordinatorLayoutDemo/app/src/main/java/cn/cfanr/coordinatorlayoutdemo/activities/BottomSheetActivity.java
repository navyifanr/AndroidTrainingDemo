package cn.cfanr.coordinatorlayoutdemo.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import cn.cfanr.coordinatorlayoutdemo.R;

public class BottomSheetActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private RelativeLayout design_bottom_sheet,design_bottom_sheet_bar;
    private BottomSheetBehavior behavior;
    private ImageView bottom_sheet_iv;
    private TextView bottom_sheet_tv;

    /**
     * 标识初始化时是否修改了底栏高度
     */
    private boolean isSetBottomSheetHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.bottom_sheet_demo_coordinatorLayout);
        design_bottom_sheet_bar=(RelativeLayout) findViewById(R.id.design_bottom_sheet_bar);
        design_bottom_sheet=(RelativeLayout) findViewById(R.id.design_bottom_sheet);
        bottom_sheet_iv=(ImageView) findViewById(R.id.bottom_sheet_iv);
        bottom_sheet_tv=(TextView) findViewById(R.id.bottom_sheet_tv);

        behavior = BottomSheetBehavior.from(design_bottom_sheet);

        initEvent();
    }

    private void initEvent() {
        //底栏状态改变的监听
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState!=BottomSheetBehavior.STATE_COLLAPSED&&bottom_sheet_tv.getVisibility()==View.VISIBLE){
                    bottom_sheet_tv.setVisibility(View.GONE);
                    bottom_sheet_iv.setVisibility(View.VISIBLE);
                }else if(newState==BottomSheetBehavior.STATE_COLLAPSED&&bottom_sheet_tv.getVisibility()==View.GONE){
                    bottom_sheet_tv.setVisibility(View.VISIBLE);
                    bottom_sheet_iv.setVisibility(View.GONE);
                }
            }
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if(bottomSheet.getTop()<2*design_bottom_sheet_bar.getHeight()){
                    //设置底栏完全展开时，出现的顶部工具栏的动画
                    design_bottom_sheet_bar.setVisibility(View.VISIBLE);
                    design_bottom_sheet_bar.setAlpha(slideOffset);
                    design_bottom_sheet_bar.setTranslationY(bottomSheet.getTop()-2*design_bottom_sheet_bar.getHeight());
                }else{
                    design_bottom_sheet_bar.setVisibility(View.INVISIBLE);
                }
            }
        });

        design_bottom_sheet_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);//点击顶部工具栏 将底栏变为折叠状态
            }
        });

        findViewById(R.id.btn_bottom_sheet_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view);
            }
        });
    }

    public void showDialog(View view){
        final BottomSheetDialog dialog=new BottomSheetDialog(BottomSheetActivity.this);
        View dialogView= LayoutInflater.from(BottomSheetActivity.this)
                .inflate(R.layout.dialog_bottom_sheet,null);
        //不要用ListView，需要嵌套滚动功能，用RecyclerView
//        ListView listView= (ListView) dialogView.findViewById(R.id.lv_dialog_bottom_sheet);

        RecyclerView recyclerView = (RecyclerView) dialogView.findViewById(R.id.recycler_view_dialog_bottom_sheet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> dataList=new ArrayList<>();
        for(int i= 0; i<20; i++){
           dataList.add("item " + i);
        }
        recyclerView.setAdapter(new CommonAdapter<String>(this, android.R.layout.simple_list_item_1, dataList) {
            @Override
            protected void convert(ViewHolder holder, String str, int position) {
                holder.setText(android.R.id.text1, str);
            }
        });

//        ArrayAdapter adapter=new ArrayAdapter(BottomSheetActivity.this,android.R.layout.simple_list_item_1,array);
//        listView.setAdapter(adapter);

        dialog.setContentView(dialogView);
        dialog.show();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //修改SetBottomSheet的高度 留出顶部工具栏的位置
        if(!isSetBottomSheetHeight){
            CoordinatorLayout.LayoutParams linearParams =(CoordinatorLayout.LayoutParams) design_bottom_sheet.getLayoutParams();
            linearParams.height=coordinatorLayout.getHeight()-design_bottom_sheet_bar.getHeight();
            design_bottom_sheet.setLayoutParams(linearParams);
            isSetBottomSheetHeight=true;
        }
    }

}
