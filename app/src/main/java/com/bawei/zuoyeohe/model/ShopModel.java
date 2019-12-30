package com.bawei.zuoyeohe.model;

import com.bawei.zuoyeohe.contract.ShopContract;
import com.bawei.zuoyeohe.model.bean.ShopBean;
import com.bawei.zuoyeohe.util.NetUtil;
import com.google.gson.Gson;

public class ShopModel implements ShopContract.IModel {
    @Override
    public void onGetData(IModelCallback iModelCallback) {
       NetUtil.getInstance().getJsonGet("http://blog.zhaoliang5156.cn/api/shop/fulishe1.json", new NetUtil.MyCallback() {
           @Override
           public void onGetJson(String json) {
               ShopBean shopBean = new Gson().fromJson(json, ShopBean.class);
               iModelCallback.onSuccess(shopBean);
           }

           @Override
           public void onError(Throwable throwable) {
               iModelCallback.onFailure(throwable);
           }
       });
    }
}
