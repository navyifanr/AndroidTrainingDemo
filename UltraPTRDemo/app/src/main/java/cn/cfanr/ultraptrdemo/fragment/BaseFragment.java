package cn.cfanr.ultraptrdemo.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    private View layoutView;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView=inflater.inflate(getLayoutResId(), container, false);
        initViews(layoutView);
        return layoutView;
    }

    protected abstract int getLayoutResId();

    public abstract void initViews(View layoutView);

    public <T extends View> T $(View view, @IdRes int resId){
        return (T) view.findViewById(resId);
    }

}
