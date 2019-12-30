package com.bawei.zuoyeohe.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.zuoyeohe.R;
import com.bawei.zuoyeohe.base.BaseActivity;
import com.bawei.zuoyeohe.contract.ShopContract;
import com.bawei.zuoyeohe.model.bean.ShopBean;
import com.bawei.zuoyeohe.presenter.ShopPresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<ShopPresenter> implements ShopContract.IView {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected void initData() {
        mPresenter.onGetData();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected ShopPresenter provitePresenter() {
        return new ShopPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        CodeUtils.init(this);
    }

    @Override
    public void onSuccess(ShopBean shopBean) {
        List<ShopBean.DataBean> data = shopBean.getData();
        recycler.setLayoutManager(new GridLayoutManager(this,2));
        MyAdapter myAdapter = new MyAdapter(data);
        recycler.setAdapter(myAdapter);
        myAdapter.setOnCkickListener(new MyAdapter.OnCkickListener() {
            @Override
            public void onClick(String s) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFailure(Throwable throwable) {

    }
}
