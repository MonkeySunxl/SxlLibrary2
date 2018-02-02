package com.sxl.sxllibrary.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sxl.sxllibrary.utils.NetWorkUtils;

import butterknife.ButterKnife;

/**
 * @ProjectName wmt
 * @ClassDescribe
 * @Author
 * @Date 2016/12/27 15:08
 * @Copyright 未名天
 */

public abstract class BaseFragment extends Fragment {
    protected final String TAG = "BaseFragment";
    public Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBefore();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    protected void initBefore() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initView(view);
        initData();
    }
    /**
     *  判断是否连接
     */
    public boolean checkNetWork() {
        if (NetWorkUtils.isConnected(context)) {
            return true;
        }else {
            return false;
        }
    }

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract int getLayoutId();

}
