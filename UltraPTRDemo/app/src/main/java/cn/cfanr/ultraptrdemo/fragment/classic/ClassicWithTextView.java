package cn.cfanr.ultraptrdemo.fragment.classic;

import android.view.View;

import cn.cfanr.ultraptrdemo.R;
import cn.cfanr.ultraptrdemo.fragment.BaseFragment;

public class ClassicWithTextView extends BaseFragment {

    public ClassicWithTextView() {
        // Required empty public constructor
    }

    public static ClassicWithTextView newInstance() {
        ClassicWithTextView fragment = new ClassicWithTextView();
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_classic_with_text_view;
    }

    @Override
    public void initViews(View layoutView) {

    }
}
