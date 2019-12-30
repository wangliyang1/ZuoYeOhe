package com.bawei.zuoyeohe.presenter;

import com.bawei.zuoyeohe.base.BasePresenter;
import com.bawei.zuoyeohe.contract.ShopContract;
import com.bawei.zuoyeohe.model.ShopModel;
import com.bawei.zuoyeohe.model.bean.ShopBean;

public class ShopPresenter extends BasePresenter<ShopContract.IView>implements ShopContract.IPresenter {
    public ShopModel shopModel;
    @Override
    protected void initModel() {
        shopModel = new ShopModel();
    }

    @Override
    public void onGetData() {
        shopModel.onGetData(new ShopContract.IModel.IModelCallback() {
            @Override
            public void onSuccess(ShopBean shopBean) {
                view.onSuccess(shopBean);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.onFailure(throwable);
            }
        });
    }
}
