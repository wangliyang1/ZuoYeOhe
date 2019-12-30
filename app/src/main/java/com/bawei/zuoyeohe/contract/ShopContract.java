package com.bawei.zuoyeohe.contract;

import com.bawei.zuoyeohe.model.bean.ShopBean;

public interface ShopContract {
    interface IView{
        void onSuccess(ShopBean shopBean);
        void onFailure(Throwable throwable);
    }
    interface IPresenter{
        void onGetData();
    }
    interface IModel{
        void onGetData(IModelCallback iModelCallback);
        interface IModelCallback{
            void onSuccess(ShopBean shopBean);
            void onFailure(Throwable throwable);
        }
    }
}
