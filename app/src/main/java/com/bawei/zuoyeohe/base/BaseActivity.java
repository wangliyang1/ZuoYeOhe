package com.bawei.zuoyeohe.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bawei.zuoyeohe.R;

public abstract class BaseActivity<P extends BasePresenter>extends AppCompatActivity {
    public P mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        mPresenter = provitePresenter();
        if (mPresenter!=null){
            mPresenter.attech(this);
        }
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P provitePresenter();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detach();
        }
    }
}
